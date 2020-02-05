package nms;

import java.util.ArrayList;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class HiloEscucha implements Runnable {
	private ArrayList<Users> _Usuarios = new ArrayList<Users>();
	SnmpTrap trap;

	public HiloEscucha(ArrayList<Users> Usuarios) {
		this._Usuarios = Usuarios;
	}

	public void run() {
		System.out.println("Hilo de traps creado");
		trap = new SnmpTrap("127.0.0.1", "", "", "", "", "", "");
		for (int i = 0; i < _Usuarios.size(); i++) {
			Users user = _Usuarios.get(i);
			trap.AnadirUsuario(user._user, user._authProtocol, user._authPasshrase, user._privProtocol,
					user._privPassphrase);
		}
		trap.AnadirUsuario("Christian", "", "", "", "");
		trap.AnadirUsuario("Christian2", "MD5", "llaveadmin", "", "");
		trap.AnadirUsuario("Christian3", "SHA", "llaveadmin", "", "");
		trap.AnadirUsuario("Christian4", "MD5", "llaveadmin", "DES", "llaveadmin");
		trap.AnadirUsuario("Christian3", "SHA", "llaveadmin", "AES", "llaveadmin");
		trap.doSnmpTraplisten();
	}

	public void Detener() {
		trap.Parar();
	}
}