package nms;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class SnmpGetNext {
	public static final int GETNEXT = 4;
	private SnmpUtil _util = null;

	public SnmpGetNext(String host, String oid) {
		_util = new SnmpUtil(host, oid, false, 0);
		_util.setOperation(GETNEXT);
	}

	public SnmpGetNext(String host, String oid, String user, String authProtocol, String authPasshrase,
			String privProtocol, String privPassphrase) {
		_util = new SnmpUtil(host, oid, user, authProtocol, authPasshrase, privProtocol, privPassphrase, true, 0);
		_util.setOperation(GETNEXT);
	}

	public String doGetNext() {
		return _util.sendAndProcessResponse();
	}
}