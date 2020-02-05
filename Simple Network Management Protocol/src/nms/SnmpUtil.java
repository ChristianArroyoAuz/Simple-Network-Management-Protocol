package nms;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
import org.snmp4j.log.LogFactory;
import org.snmp4j.log.Log4jLogFactory;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.*;
import org.snmp4j.security.*;
import org.snmp4j.asn1.BER;
import java.io.IOException;
import org.apache.log4j.*;
import org.snmp4j.util.*;
import java.util.Vector;
import org.snmp4j.smi.*;
import org.snmp4j.mp.*;
import org.snmp4j.*;

public class SnmpUtil extends Thread implements PDUFactory, CommandResponder {
	public static final int DEFAULT = 0;
	public static final int WALK = 1;
	public static final int SET = 2;
	public static final int GET = 3;
	public static final int GETNEXT = 4;
	private Target _target;
	private Address _address;
	private OID _authProtocol;
	private OID _privProtocol;
	private OctetString _privPassphrase;
	private OctetString _authPassphrase;
	private OctetString _community = new OctetString("public");
	private OctetString _contextEngineID;
	private OctetString _contextName = new OctetString();
	private OctetString _securityName = new OctetString();
	public static Snmp _snmp = null;
	private int _numThreads = 1;
	private int _port = 0;
	private ThreadPool _threadPool = null;
	private boolean _isReceiver = false;
	private OctetString _authoritativeEngineID = new OctetString("1234567");
	public TransportMapping _transport = null;
	private TimeTicks _sysUpTime = new TimeTicks(0);
	private OID _trapOID = new OID("1.3.6.1.4.1.2789.2005");
	private int _version = 0;
	private int _retries = 1;
	private int _timeout = 1000;
	private int _pduType = 0;
	private Vector<VariableBinding> _vbs = new Vector<VariableBinding>();
	protected int _operation = DEFAULT;
	static {
		if (System.getProperty("log4j.configuration") == null) {
			BasicConfigurator.configure();
		}
	}

	public SnmpUtil(String host, String varbind, boolean receiver, int type) {
		_version = SnmpConstants.version2c;
		_isReceiver = receiver;
		if (type == 2) {
			_pduType = PDU.INFORM;
		} else if (type == 1) {
			_pduType = PDU.TRAP;
		}
		setPort();
		if (!_isReceiver) {
			init(host, varbind);
		} else {
			initReceiver(host);
		}
	}

	public SnmpUtil(String host, String varbind, String user, String authProtocol, String authPasshrase,
			String privProtocol, String privPassphrase, boolean receiver, int type) {
		_version = SnmpConstants.version3;
		_isReceiver = receiver;
		_privPassphrase = new OctetString(privPassphrase);
		_authPassphrase = new OctetString(authPasshrase);
		_securityName = new OctetString(user);
		if (authProtocol.equals("MD5")) {
			_authProtocol = AuthMD5.ID;
		} else if (authProtocol.equals("SHA")) {
			_authProtocol = AuthSHA.ID;
		} else if (authProtocol.equals("")) {
			_authPassphrase = null;
		}
		if (privProtocol.equals("DES")) {
			_privProtocol = PrivDES.ID;
		} else if ((privProtocol.equals("AES128")) || (privProtocol.equals("AES"))) {
			_privProtocol = PrivAES128.ID;
		} else if (privProtocol.equals("AES192")) {
			_privProtocol = PrivAES192.ID;
		} else if (privProtocol.equals("AES256")) {
			_privProtocol = PrivAES256.ID;
		} else if (privProtocol.equals("")) {
			_privPassphrase = null;
		}
		if (type == 2) {
			_pduType = PDU.INFORM;
		} else if (type == 1) {
			_pduType = PDU.TRAP;
		}
		setPort();
		if (!_isReceiver) {
			init(host, varbind);
		} else {
			initReceiver(host);
		}
	}

	public void setVersion(int version) {
		_version = version;
	}

	public void setOperation(int operation) {
		_operation = operation;
		if (_operation == WALK) {
			_pduType = PDU.GETNEXT;
		}
		if (_operation == SET) {
			_pduType = PDU.SET;
		}
		if (_operation == GET) {
			_pduType = PDU.GET;
		}
		if (_operation == GETNEXT) {
			_pduType = PDU.GETNEXT;
		}
	}

	public int getOperation() {
		return _operation;
	}

	public int getPduType() {
		return _pduType;
	}

	public void setPort() {
		if (_pduType == PDU.INFORM || _pduType == PDU.TRAP || _isReceiver) {
			_port = 162;
		} else {
			_port = 161;
		}
	}

	public void init(String host, String varbind) {
		_vbs = getVariableBinding(varbind);
		if (_pduType == PDU.INFORM || _pduType == PDU.TRAP) {
			checkTrapVariables(_vbs);
		}
		_address = new UdpAddress(host + "/" + _port);
		LogFactory.setLogFactory(new Log4jLogFactory());
		BER.setCheckSequenceLength(false);
	}

	public Vector<VariableBinding> getVariableBindings() {
		return _vbs;
	}

	public void addUsmUser(Snmp snmp) {
		snmp.getUSM().addUser(_securityName,
				new UsmUser(_securityName, _authProtocol, _authPassphrase, _privProtocol, _privPassphrase));
	}

	private Snmp createSnmpSession() throws IOException {
		AbstractTransportMapping transport;
		if (_address instanceof TcpAddress) {
			transport = new DefaultTcpTransportMapping();
		} else {
			transport = new DefaultUdpTransportMapping();
		}
		transport.setAsyncMsgProcessingSupported(false);
		Snmp snmp = new Snmp(transport);
		if (_version == SnmpConstants.version3) {
			USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
			SecurityModels.getInstance().addSecurityModel(usm);
			addUsmUser(snmp);
		}
		return snmp;
	}

	private Target createTarget() {
		if (_version == SnmpConstants.version3) {
			UserTarget target = new UserTarget();
			if (_authPassphrase != null) {
				if (_privPassphrase != null) {
					target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
				} else {
					target.setSecurityLevel(SecurityLevel.AUTH_NOPRIV);
				}
			} else {
				target.setSecurityLevel(SecurityLevel.NOAUTH_NOPRIV);
			}
			target.setSecurityName(_securityName);
			return target;
		} else {
			CommunityTarget target = new CommunityTarget();
			target.setCommunity(_community);
			return target;
		}
	}

	public PDU send() throws IOException {
		_snmp = createSnmpSession();
		this._target = createTarget();
		_target.setVersion(_version);
		_target.setAddress(_address);
		_target.setRetries(_retries);
		_target.setTimeout(_timeout);
		_snmp.listen();
		PDU request = createPDU(_target);
		for (int i = 0; i < _vbs.size(); i++) {
			request.add((VariableBinding) _vbs.get(i));
		}
		PDU response = null;
		if (_operation == WALK) {
			response = walk(_snmp, request, _target);
		} else {
			ResponseEvent responseEvent;
			long startTime = System.currentTimeMillis();
			responseEvent = _snmp.send(request, _target);
			if (responseEvent != null) {
				response = responseEvent.getResponse();
				System.out.println("Received response after " + (System.currentTimeMillis() - startTime) + " millis");
			}
		}
		_snmp.close();
		return response;
	}

	protected static void printVariableBindings(PDU response) {
		for (int i = 0; i < response.size(); i++) {
			VariableBinding vb = response.get(i);
			System.out.println(vb.toString());
		}
	}

	protected static void printReport(PDU response) {
		if (response.size() < 1) {
			System.out.println("REPORT PDU does not contain a variable binding.");
			return;
		}
		VariableBinding vb = response.get(0);
		OID oid = vb.getOid();
		if (SnmpConstants.usmStatsUnsupportedSecLevels.equals(oid)) {
			System.out.print("REPORT: Unsupported Security Level.");
		} else if (SnmpConstants.usmStatsNotInTimeWindows.equals(oid)) {
			System.out.print("REPORT: Message not within time window.");
		} else if (SnmpConstants.usmStatsUnknownUserNames.equals(oid)) {
			System.out.print("REPORT: Unknown user name.");
		} else if (SnmpConstants.usmStatsUnknownEngineIDs.equals(oid)) {
			System.out.print("REPORT: Unknown engine id.");
		} else if (SnmpConstants.usmStatsWrongDigests.equals(oid)) {
			System.out.print("REPORT: Wrong digest.");
		} else if (SnmpConstants.usmStatsDecryptionErrors.equals(oid)) {
			System.out.print("REPORT: Decryption error.");
		} else if (SnmpConstants.snmpUnknownSecurityModels.equals(oid)) {
			System.out.print("REPORT: Unknown security model.");
		} else if (SnmpConstants.snmpInvalidMsgs.equals(oid)) {
			System.out.print("REPORT: Invalid message.");
		} else if (SnmpConstants.snmpUnknownPDUHandlers.equals(oid)) {
			System.out.print("REPORT: Unknown PDU handler.");
		} else if (SnmpConstants.snmpUnavailableContexts.equals(oid)) {
			System.out.print("REPORT: Unavailable context.");
		} else if (SnmpConstants.snmpUnknownContexts.equals(oid)) {
			System.out.print("REPORT: Unknown context.");
		} else {
			System.out.print("REPORT contains unknown OID (" + oid.toString() + ").");
		}

		System.out.println(" Current counter value is " + vb.getVariable().toString() + ".");
	}

	public PDU createPDU(Target target) {
		PDU request;
		if (_target.getVersion() == SnmpConstants.version3) {
			request = new ScopedPDU();
			ScopedPDU scopedPDU = (ScopedPDU) request;
			if (_contextEngineID != null) {
				scopedPDU.setContextEngineID(_contextEngineID);
			}
			if (_contextName != null) {
				scopedPDU.setContextName(_contextName);
			}
		} else {
			request = new PDU();
		}
		request.setType(_pduType);
		return request;
	}

	private Vector<VariableBinding> getVariableBinding(String varbind) {
		Vector<VariableBinding> v = new Vector<VariableBinding>(varbind.length());
		String oid = null;
		char type = 'i';
		String value = null;
		int equal = varbind.indexOf("={");
		if (equal > 0) {
			oid = varbind.substring(0, equal);
			type = varbind.charAt(equal + 2);
			value = varbind.substring(varbind.indexOf('}') + 1);
		} else {
			v.add(new VariableBinding(new OID(varbind)));
			return v;
		}
		VariableBinding vb = new VariableBinding(new OID(oid));
		if (value != null) {
			Variable variable;
			switch (type) {
			case 'i':
				variable = new Integer32(Integer.parseInt(value));
				break;
			case 'u':
				variable = new UnsignedInteger32(Long.parseLong(value));
				break;
			case 's':
				variable = new OctetString(value);
				break;
			case 'x':
				variable = OctetString.fromString(value, ':', 16);
				break;
			case 'd':
				variable = OctetString.fromString(value, '.', 10);
				break;
			case 'b':
				variable = OctetString.fromString(value, ' ', 2);
				break;
			case 'n':
				variable = new Null();
				break;
			case 'o':
				variable = new OID(value);
				break;
			case 't':
				variable = new TimeTicks(Long.parseLong(value));
				break;
			case 'a':
				variable = new IpAddress(value);
				break;
			default:
				throw new IllegalArgumentException("Variable type " + type + " not supported");
			}
			vb.setVariable(variable);
		}
		v.add(vb);
		return v;
	}

	private static PDU walk(Snmp snmp, PDU request, Target target) throws IOException {
		request.setNonRepeaters(0);
		OID rootOID = request.get(0).getOid();
		PDU response = null;
		int objects = 0;
		int requests = 0;
		long startTime = System.currentTimeMillis();
		do {
			requests++;
			ResponseEvent responseEvent = _snmp.send(request, target);
			response = responseEvent.getResponse();
			if (response != null) {
				objects += response.size();
			}
		} while (!processWalk(response, request, rootOID));
		System.out.println();
		System.out.println("Total requests sent:    " + requests);
		System.out.println("Total objects received: " + objects);
		System.out.println("Total walk time:        " + (System.currentTimeMillis() - startTime) + " milliseconds");
		return response;
	}

	private static boolean processWalk(PDU response, PDU request, OID rootOID) {
		if ((response == null) || (response.getErrorStatus() != 0) || (response.getType() == PDU.REPORT)) {
			return true;
		}
		boolean finished = false;
		OID lastOID = request.get(0).getOid();
		for (int i = 0; (!finished) && (i < response.size()); i++) {
			VariableBinding vb = response.get(i);
			if ((vb.getOid() == null) || (vb.getOid().size() < rootOID.size())
					|| (rootOID.leftMostCompare(rootOID.size(), vb.getOid()) != 0)) {
				finished = true;
			} else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
				System.out.println(vb.toString());
				finished = true;
			} else if (vb.getOid().compareTo(lastOID) <= 0) {
				System.out.println("Variable received is not lexicographic successor of requested one:");
				System.out.println(vb.toString() + " <= " + lastOID);
				finished = true;
			} else {
				System.out.println(vb.toString());
				lastOID = vb.getOid();
			}
		}
		if (response.size() == 0) {
			finished = true;
		}
		if (!finished) {
			VariableBinding next = response.get(response.size() - 1);
			next.setVariable(new Null());
			request.set(0, next);
			request.setRequestID(new Integer32(0));
		}
		return finished;
	}

	public void initReceiver(String host) {
		Address address = new UdpAddress(host + "/" + _port);
		try {
			_transport = new DefaultUdpTransportMapping((UdpAddress) address);
		} catch (IOException ioex) {
			System.out.println("Unable to bind to local IP and port: " + ioex);
			System.exit(-1);
		}
		_threadPool = ThreadPool.create(this.getClass().getName(), _numThreads);

		MessageDispatcher mtDispatcher = new MultiThreadedMessageDispatcher(_threadPool, new MessageDispatcherImpl());
		mtDispatcher.addMessageProcessingModel(new MPv1());
		mtDispatcher.addMessageProcessingModel(new MPv2c());
		SecurityProtocols.getInstance().addDefaultProtocols();
		_snmp = new Snmp(mtDispatcher, _transport);
		if (_snmp != null) {
			_snmp.addCommandResponder(this);
		} else {
			System.out.println("Unable to create Target object");
			System.exit(-1);
		}
		if (_version == SnmpConstants.version3) {
			mtDispatcher.addMessageProcessingModel(new MPv3());
			_snmp.getMessageProcessingModel(MessageProcessingModel.MPv3);
			USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
			SecurityModels.getInstance().addSecurityModel(usm);
			if (_authoritativeEngineID != null) {
				_snmp.setLocalEngine(_authoritativeEngineID.getValue(), 0, 0);
			}
			this.addUsmUser(_snmp);
		}
	}

	public synchronized void listen() {
		try {
			_transport.listen();
		} catch (IOException ioex) {
			System.out.println("Unable to listen: " + ioex);
			System.exit(-1);
		}
		System.out.println("Waiting for traps..");
		try {
			this.wait();
		} catch (InterruptedException ex) {
			System.out.println("Interrupted while waiting for traps: " + ex);
		}
	}

	public synchronized void processPdu(CommandResponderEvent e) {
		PDU command = e.getPDU();
		if (command != null) {
			System.out.println(command.toString());
			if ((command.getType() != PDU.TRAP) && (command.getType() != PDU.V1TRAP)
					&& (command.getType() != PDU.REPORT) && (command.getType() != PDU.RESPONSE)) {
				command.setErrorIndex(0);
				command.setErrorStatus(0);
				command.setType(PDU.RESPONSE);
				StatusInformation statusInformation = new StatusInformation();
				StateReference ref = e.getStateReference();
				try {
					e.getMessageDispatcher().returnResponsePdu(e.getMessageProcessingModel(), e.getSecurityModel(),
							e.getSecurityName(), e.getSecurityLevel(), command, e.getMaxSizeResponsePDU(), ref,
							statusInformation);
				} catch (MessageException ex) {
					System.err.println("Error while sending response: " + ex.getMessage());
				}
			}
		}
	}

	public String sendAndProcessResponse() {
		try {
			PDU response = this.send();
			if ((getPduType() == PDU.TRAP) || (getPduType() == PDU.REPORT) || (getPduType() == PDU.V1TRAP)
					|| (getPduType() == PDU.RESPONSE)) {
				System.out.println(PDU.getTypeString(getPduType()) + " sent successfully");
				return (PDU.getTypeString(getPduType()) + " sent successfully");
			} else if (response == null) {
				System.out.println("Request timed out.");
				return ("Request timed out.");
			} else if (response.getType() == PDU.REPORT) {
				printReport(response);
			} else if (getOperation() == WALK) {
				System.out.println("End of walked subtree '" + ((VariableBinding) getVariableBindings().get(0)).getOid()
						+ "' reached at:");
				printVariableBindings(response);
			} else {
				String respuesta;
				System.out.println("Received something strange: requestID=" + response.getRequestID() + ", errorIndex="
						+ response.getErrorIndex() + ", " + "errorStatus=" + response.getErrorStatusText() + "("
						+ response.getErrorStatus() + ")");
				respuesta = "requestID=" + response.getRequestID() + ", errorIndex=" + response.getErrorIndex() + ", "
						+ "errorStatus=" + response.getErrorStatusText() + "(" + response.getErrorStatus() + ")\n";
				printVariableBindings(response);

				for (int i = 0; i < response.size(); i++) {
					VariableBinding vb = response.get(i);
					System.out.println(vb.toString());
					respuesta = respuesta + vb.toString() + "\n";
				}
				return (respuesta);
			}
		} catch (IOException ex) {
			System.err.println("Error while trying to send request: " + ex.getMessage());
			ex.printStackTrace();
			return ("Error while trying to send request: " + ex.getMessage());
		}
		return "";
	}

	private void checkTrapVariables(Vector<VariableBinding> vbs) {
		if ((_pduType == PDU.INFORM) || (_pduType == PDU.TRAP)) {
			if ((vbs.size() == 0) || ((vbs.size() > 1)
					&& (!((VariableBinding) vbs.get(0)).getOid().equals(SnmpConstants.sysUpTime)))) {
				vbs.add(0, new VariableBinding(SnmpConstants.sysUpTime, _sysUpTime));
			}
			if ((vbs.size() == 1) || ((vbs.size() > 2)
					&& (!((VariableBinding) vbs.get(1)).getOid().equals(SnmpConstants.snmpTrapOID)))) {
				vbs.add(1, new VariableBinding(SnmpConstants.snmpTrapOID, _trapOID));
			}
		}
	}
}