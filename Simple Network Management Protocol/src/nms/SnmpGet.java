package nms;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class SnmpGet {

	public static final int GET = 3;
	private SnmpUtil _util = null;

	public SnmpGet(String host, String oid) {
		_util = new SnmpUtil(host, oid, false, 0);
		_util.setOperation(GET);
	}

	public SnmpGet(String host, String oid, String user, String authProtocol, String authPasshrase, String privProtocol,
			String privPassphrase) {
		_util = new SnmpUtil(host, oid, user, authProtocol, authPasshrase, privProtocol, privPassphrase, false, 0);
		_util.setOperation(GET);
	}

	public String doGet() {
		return _util.sendAndProcessResponse();
	}
}