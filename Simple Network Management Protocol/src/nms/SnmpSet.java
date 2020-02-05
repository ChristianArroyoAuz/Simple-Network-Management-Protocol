package nms;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class SnmpSet {
	public static final int SET = 2;
	private SnmpUtil _util = null;

	public SnmpSet(String host, String oid) {
		_util = new SnmpUtil(host, oid, false, 0);
		_util.setOperation(SET);
	}

	public SnmpSet(String host, String oid, String user, String authProtocol, String authPasshrase, String privProtocol,
			String privPassphrase) {
		_util = new SnmpUtil(host, oid, user, authProtocol, authPasshrase, privProtocol, privPassphrase, false, 0);
		_util.setOperation(SET);
	}

	public String doSet() {
		return _util.sendAndProcessResponse();
	}
}