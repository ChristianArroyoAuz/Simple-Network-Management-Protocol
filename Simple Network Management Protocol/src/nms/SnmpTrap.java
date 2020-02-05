package nms;

import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.PrivAES192;
import org.snmp4j.security.PrivAES256;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.UsmUser;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.AuthSHA;
import org.snmp4j.smi.OctetString;
import java.io.IOException;
import org.snmp4j.smi.OID;
import org.snmp4j.Snmp;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class SnmpTrap {
	private SnmpUtil _util = null;
	private OctetString _securityName = new OctetString();
	private OID _authProtocol;
	private OID _privProtocol;
	private OctetString _privPassphrase;
	private OctetString _authPassphrase;

	public SnmpTrap(String host, String oid, String user, String authProtocol, String authPasshrase,
			String privProtocol, String privPassphrase) {
		_util = new SnmpUtil(host, oid, user, authProtocol, authPasshrase, privProtocol, privPassphrase, true, 0);
	}

	public void AnadirUsuario(String user, String authProtocol, String authPasshrase, String privProtocol,
			String privPassphrase) {
		Snmp snmp = SnmpUtil._snmp;
		_util.addUsmUser(snmp);
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
		snmp.getUSM().addUser(_securityName,
				new UsmUser(_securityName, _authProtocol, _authPassphrase, _privProtocol, _privPassphrase));
	}

	public void doSnmpTraplisten() {
		_util.listen();
	}

	public void Parar() {
		try {
			_util._transport.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}