package nms;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class Users {
	public String _user;
	public String _authProtocol;
	public String _authPasshrase;
	public String _privProtocol;
	public String _privPassphrase;

	public Users(String user, String authProtocol, String authPasshrase, String privProtocol, String privPassphrase) {
		this._user = user;
		this._authProtocol = authProtocol;
		this._authPasshrase = authPasshrase;
		this._privProtocol = privProtocol;
		this._privPassphrase = privPassphrase;
	}
}