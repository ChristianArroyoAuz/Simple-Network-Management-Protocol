package nms;

import java.io.OutputStream;
import javax.swing.JTextArea;
import java.io.IOException;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class TextAreaOutputStream extends OutputStream {

	private JTextArea textControl;

	public TextAreaOutputStream(JTextArea control) {
		textControl = control;
	}

	public void write(int b) throws IOException {
		textControl.append(String.valueOf((char) b));
	}
}