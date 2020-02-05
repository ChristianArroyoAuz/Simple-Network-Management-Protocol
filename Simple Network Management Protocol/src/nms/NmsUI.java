package nms;

import java.io.PrintStream;
import java.util.ArrayList;

/* @author: Christian --> SNMP-MD5-SHA-DES-AES*/
public class NmsUI extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Users> _Usuarios = new ArrayList<Users>();
	public HiloEscucha r;
	public Thread hilo = null;

	public NmsUI() {
		initComponents();
		btnAnadirUsuario.setEnabled(false);
		btnEnviar.setEnabled(false);
		btnRecibirTraps.setEnabled(false);
		btnReiniciar.setEnabled(false);
	}

	private void initComponents() {
		btngrouppdus = new javax.swing.ButtonGroup();
		btngroupNivel = new javax.swing.ButtonGroup();
		btngroupAutenticacion = new javax.swing.ButtonGroup();
		btngroupPrivacidad = new javax.swing.ButtonGroup();
		btngroupTipoDato = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		txtNombreUsuario = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		txtHost = new javax.swing.JTextField();
		txtOID = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtaRespuestas = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtaTraps = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		btnEnviar = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		txtLlaveAutenticacion = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		txtLlavePrivacidad = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		txtValor = new javax.swing.JTextField();
		btnRecibirTraps = new javax.swing.JButton();
		btnAnadirUsuario = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		txtUsuarios = new javax.swing.JTextArea();
		jLabel9 = new javax.swing.JLabel();
		btnReiniciar = new javax.swing.JButton();
		rdbOctectString = new javax.swing.JRadioButton();
		rdbInteger32 = new javax.swing.JRadioButton();
		rdbUnsignedInteger32 = new javax.swing.JRadioButton();
		rdbNull = new javax.swing.JRadioButton();
		rdbOID = new javax.swing.JRadioButton();
		rdbIP = new javax.swing.JRadioButton();
		rdbTimeTicks = new javax.swing.JRadioButton();
		rdbNoauthNopriv = new javax.swing.JRadioButton();
		rdbAuthNopriv = new javax.swing.JRadioButton();
		rdbAuthPriv = new javax.swing.JRadioButton();
		rdbMD5 = new javax.swing.JRadioButton();
		rdbSHA = new javax.swing.JRadioButton();
		rdbDES = new javax.swing.JRadioButton();
		rdbAES = new javax.swing.JRadioButton();
		rdbTrap = new javax.swing.JRadioButton();
		rdbgetnext = new javax.swing.JRadioButton();
		rdbset = new javax.swing.JRadioButton();
		rdbget = new javax.swing.JRadioButton();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		jLabel1.setText("Usuario:");
		jLabel2.setText("Host (IP):");
		jLabel3.setText("OID(#):");
		txtaRespuestas.setColumns(20);
		txtaRespuestas.setRows(5);
		jScrollPane1.setViewportView(txtaRespuestas);
		txtaTraps.setColumns(20);
		txtaTraps.setRows(5);
		jScrollPane2.setViewportView(txtaTraps);
		jLabel4.setText("Respuestas:");
		jLabel5.setText("Acciones y Traps Rx");
		btnEnviar.setText("Enviar PDU");
		btnEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnEnviarMouseClicked(evt);
			}
		});
		jLabel6.setText("Llave de Aut: ");
		jLabel7.setText("Llave de Priv");
		jLabel8.setText("Valor:");
		btnRecibirTraps.setText("Recibir Traps");
		btnRecibirTraps.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnRecibirTrapsMouseClicked(evt);
			}
		});
		btnRecibirTraps.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRecibirTrapsActionPerformed(evt);
			}
		});
		btnAnadirUsuario.setText("Añadir Usuario");
		btnAnadirUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnAnadirUsuarioMouseClicked(evt);
			}
		});
		txtUsuarios.setColumns(20);
		txtUsuarios.setRows(5);
		jScrollPane3.setViewportView(txtUsuarios);
		jLabel9.setText("Usuarios registrados para Rx Traps:");
		btnReiniciar.setText("Reiniciar TRAPS");
		btnReiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnReiniciarMouseClicked(evt);
			}
		});
		btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReiniciarActionPerformed(evt);
			}
		});
		btngroupTipoDato.add(rdbOctectString);
		rdbOctectString.setText("OctectString");
		rdbOctectString.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdbOctectStringActionPerformed(evt);
			}
		});
		btngroupTipoDato.add(rdbInteger32);
		rdbInteger32.setText("Integer32");
		btngroupTipoDato.add(rdbUnsignedInteger32);
		rdbUnsignedInteger32.setText("UnsignedInteger32");
		btngroupTipoDato.add(rdbNull);
		rdbNull.setText("Null");
		btngroupTipoDato.add(rdbOID);
		rdbOID.setText("OID");
		btngroupTipoDato.add(rdbIP);
		rdbIP.setText("IP");
		btngroupTipoDato.add(rdbTimeTicks);
		rdbTimeTicks.setText("TimeTicks");
		btngroupNivel.add(rdbNoauthNopriv);
		rdbNoauthNopriv.setText("NoauthNopriv");
		rdbNoauthNopriv.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				rdbNoauthNoprivStateChanged(evt);
			}
		});
		btngroupNivel.add(rdbAuthNopriv);
		rdbAuthNopriv.setText("AuthNopriv");
		rdbAuthNopriv.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				rdbAuthNoprivStateChanged(evt);
			}
		});
		btngroupNivel.add(rdbAuthPriv);
		rdbAuthPriv.setText("AuthPriv");
		rdbAuthPriv.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				rdbAuthPrivStateChanged(evt);
			}
		});
		btngroupAutenticacion.add(rdbMD5);
		rdbMD5.setText("MD5");
		btngroupAutenticacion.add(rdbSHA);
		rdbSHA.setText("SHA");
		btngroupPrivacidad.add(rdbDES);
		rdbDES.setText("DES");
		btngroupPrivacidad.add(rdbAES);
		rdbAES.setText("AES");
		btngrouppdus.add(rdbTrap);
		rdbTrap.setText("TRAP");
		rdbTrap.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				rdbTrapMouseClicked(evt);
			}
		});
		rdbTrap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdbTrapActionPerformed(evt);
			}
		});
		btngrouppdus.add(rdbgetnext);
		rdbgetnext.setText("GET NEXT");
		rdbgetnext.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				rdbgetnextStateChanged(evt);
			}
		});
		btngrouppdus.add(rdbset);
		rdbset.setText("SET");
		rdbset.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				rdbsetStateChanged(evt);
			}
		});
		btngrouppdus.add(rdbget);
		rdbget.setText("GET");
		rdbget.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				rdbgetStateChanged(evt);
			}
		});
		jLabel10.setText("Nivel de Seguridad");
		jLabel11.setText("Tipo de Dato");
		jLabel12.setText("Autenticacion");
		jLabel13.setText("Encriptacion");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel9)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel5).addComponent(jScrollPane1).addComponent(jScrollPane2)
										.addComponent(
												jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 394,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jLabel4))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(11, 11,
														11).addComponent(btnEnviar,
																javax.swing.GroupLayout.PREFERRED_SIZE, 108,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(215, 215, 215)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		false)
																.addComponent(btnRecibirTraps,
																		javax.swing.GroupLayout.Alignment.LEADING,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(btnReiniciar,
																		javax.swing.GroupLayout.Alignment.LEADING,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(btnAnadirUsuario,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 107,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup().addComponent(rdbget)
																.addGap(26, 26, 26).addComponent(rdbset)
																.addGap(18, 18, 18).addComponent(rdbgetnext)
																.addGap(10, 10, 10).addComponent(rdbTrap)
																.addGap(153, 153, 153)))
										.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(txtNombreUsuario,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 141,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(layout.createSequentialGroup().addGap(8, 8, 8)
																		.addComponent(jLabel1)))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel2).addComponent(txtHost,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				128,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(txtOID, javax.swing.GroupLayout.PREFERRED_SIZE,
																123, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(layout.createSequentialGroup().addGap(8, 8, 8)
																		.addComponent(jLabel6))
																.addComponent(
																		txtLlaveAutenticacion,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 123,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel7)
																		.addComponent(txtLlavePrivacidad,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				123,
																				javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel8).addComponent(txtValor,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 142,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(layout
														.createSequentialGroup().addGap(10, 10, 10)
														.addComponent(jLabel3)))
												.addGap(12, 12, 12)))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(rdbNoauthNopriv)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(rdbAuthNopriv).addGap(18, 18, 18)
														.addComponent(rdbAuthPriv))
												.addGroup(layout
														.createSequentialGroup().addGap(9, 9, 9)
														.addComponent(jLabel10)))
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(rdbOctectString)
														.addGap(18, 18, 18).addComponent(rdbInteger32)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(rdbOID)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(rdbTimeTicks))
												.addGroup(layout.createSequentialGroup()
														.addComponent(rdbUnsignedInteger32)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(rdbNull).addGap(18, 18, 18).addComponent(rdbIP))
												.addGroup(layout.createSequentialGroup().addComponent(rdbMD5)
														.addGap(18, 18, 18).addComponent(rdbSHA).addGap(26, 26, 26)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(rdbDES)
																.addGroup(layout.createSequentialGroup()
																		.addGap(79, 79, 79).addComponent(rdbAES))))
												.addComponent(jLabel11)
												.addGroup(layout.createSequentialGroup().addComponent(jLabel12)
														.addGap(124, 124, 124).addComponent(jLabel13)))
												.addGap(0, 122, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(16, 16, 16)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout
						.createSequentialGroup().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel4)
								.addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel5)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel9)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jLabel2)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel3)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtOID, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7).addComponent(jLabel6))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtLlavePrivacidad,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtLlaveAutenticacion,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addComponent(jLabel8)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(21, 21, 21).addComponent(jLabel10)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(rdbNoauthNopriv).addComponent(rdbAuthNopriv)
												.addComponent(rdbAuthPriv))))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel11).addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnAnadirUsuario).addComponent(btnEnviar))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(rdbTrap).addComponent(rdbgetnext)
																		.addComponent(rdbset).addComponent(rdbget))
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(layout.createSequentialGroup()
																		.addGap(27, 27, 27)
																		.addGroup(layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(rdbUnsignedInteger32)
																				.addComponent(rdbNull)
																				.addComponent(rdbIP)))
																.addComponent(rdbOctectString)
																.addGroup(layout.createSequentialGroup().addGap(3, 3, 3)
																		.addGroup(layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(rdbInteger32)
																				.addComponent(rdbOID)
																				.addComponent(rdbTimeTicks)))))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(jLabel12).addComponent(jLabel13))
														.addGap(18, 18, 18)))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(rdbDES).addComponent(rdbMD5).addComponent(rdbSHA))
												.addComponent(rdbAES, javax.swing.GroupLayout.Alignment.TRAILING))
										.addGroup(layout.createSequentialGroup().addComponent(btnRecibirTraps)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnReiniciar)))))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jLabel6.getAccessibleContext().setAccessibleName("Llave de Aut ");
		pack();
	}

	private void btnEnviarMouseClicked(java.awt.event.MouseEvent evt) {
		if (rdbget.isSelected()) {
			if (rdbNoauthNopriv.isSelected()) {
				SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "", "", "",
						"");
				txtaRespuestas.append(get.doGet() + "\n");
			} else if (rdbAuthNopriv.isSelected()) {
				if (rdbMD5.isSelected()) {
					SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "MD5",
							txtLlaveAutenticacion.getText(), "", "");
					txtaRespuestas.append(get.doGet() + "\n");
				} else if (rdbSHA.isSelected()) {
					SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "SHA",
							txtLlaveAutenticacion.getText(), "", "");
					txtaRespuestas.append(get.doGet() + "\n");
				}
			} else if (rdbAuthPriv.isSelected()) {
				if (rdbMD5.isSelected() && rdbDES.isSelected()) {
					SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "MD5",
							txtLlaveAutenticacion.getText(), "DES", txtLlavePrivacidad.getText());
					txtaRespuestas.append(get.doGet() + "\n");
				} else if (rdbSHA.isSelected() && rdbDES.isSelected()) {
					SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "SHA",
							txtLlaveAutenticacion.getText(), "DES", txtLlavePrivacidad.getText());
					txtaRespuestas.append(get.doGet() + "\n");
				} else if (rdbMD5.isSelected() && rdbAES.isSelected()) {
					SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "MD5",
							txtLlaveAutenticacion.getText(), "AES", txtLlavePrivacidad.getText());
					txtaRespuestas.append(get.doGet() + "\n");
				} else if (rdbSHA.isSelected() && rdbAES.isSelected()) {
					SnmpGet get = new SnmpGet(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(), "SHA",
							txtLlaveAutenticacion.getText(), "AES", txtLlavePrivacidad.getText());
					txtaRespuestas.append(get.doGet() + "\n");
				}
			}
		} else if (rdbset.isSelected()) {
			if (rdbNoauthNopriv.isSelected()) {
				if (rdbOctectString.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				} else if (rdbInteger32.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				} else if (rdbUnsignedInteger32.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				} else if (rdbNull.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				} else if (rdbOID.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				} else if (rdbTimeTicks.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				} else if (rdbIP.isSelected()) {
					SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
							txtNombreUsuario.getText(), "", "", "", "");
					txtaRespuestas.append(set.doSet() + "\n");
				}
			} else if (rdbAuthNopriv.isSelected()) {
				if (rdbMD5.isSelected()) {
					if (rdbOctectString.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbUnsignedInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbNull.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbOID.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbTimeTicks.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbIP.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					}
				} else if (rdbSHA.isSelected()) {
					if (rdbOctectString.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbUnsignedInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbNull.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbOID.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbTimeTicks.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbIP.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
						txtaRespuestas.append(set.doSet() + "\n");
					}
				}
			} else if (rdbAuthPriv.isSelected()) {
				if (rdbMD5.isSelected() && rdbDES.isSelected()) {
					if (rdbOctectString.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbUnsignedInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbNull.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbOID.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbTimeTicks.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbIP.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					}
				} else if (rdbSHA.isSelected() && rdbDES.isSelected()) {
					if (rdbOctectString.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbUnsignedInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbNull.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbOID.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbTimeTicks.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbIP.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					}
				} else if (rdbMD5.isSelected() && rdbAES.isSelected()) {
					if (rdbOctectString.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbUnsignedInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbNull.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbOID.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbTimeTicks.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbIP.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
								txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					}
				} else if (rdbSHA.isSelected() && rdbAES.isSelected()) {
					if (rdbOctectString.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={s}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={i}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbUnsignedInteger32.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={u}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbNull.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={n}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbOID.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={o}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbTimeTicks.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={t}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					} else if (rdbIP.isSelected()) {
						SnmpSet set = new SnmpSet(txtHost.getText(), txtOID.getText() + "={a}" + txtValor.getText(),
								txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
								txtLlavePrivacidad.getText());
						txtaRespuestas.append(set.doSet() + "\n");
					}
				}
			}
		} else if (rdbgetnext.isSelected()) {
			if (rdbNoauthNopriv.isSelected()) {
				SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(), txtNombreUsuario.getText(),
						"", "", "", "");
				txtaRespuestas.append(getnext.doGetNext() + "\n");
			} else if (rdbAuthNopriv.isSelected()) {
				if (rdbMD5.isSelected()) {
					SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(),
							txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
					txtaRespuestas.append(getnext.doGetNext() + "\n");
				} else if (rdbSHA.isSelected()) {
					SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(),
							txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
					txtaRespuestas.append(getnext.doGetNext() + "\n");
				}
			} else if (rdbAuthPriv.isSelected()) {
				if (rdbMD5.isSelected() && rdbDES.isSelected()) {
					SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(),
							txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
							txtLlavePrivacidad.getText());
					txtaRespuestas.append(getnext.doGetNext() + "\n");
				} else if (rdbSHA.isSelected() && rdbDES.isSelected()) {
					SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(),
							txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
							txtLlavePrivacidad.getText());
					txtaRespuestas.append(getnext.doGetNext() + "\n");
				} else if (rdbMD5.isSelected() && rdbAES.isSelected()) {
					SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(),
							txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
							txtLlavePrivacidad.getText());
					txtaRespuestas.append(getnext.doGetNext() + "\n");
				} else if (rdbSHA.isSelected() && rdbAES.isSelected()) {
					SnmpGetNext getnext = new SnmpGetNext(txtHost.getText(), txtOID.getText(),
							txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
							txtLlavePrivacidad.getText());
					txtaRespuestas.append(getnext.doGetNext() + "\n");
				}
			}
		}

	}

	private void rdbNoauthNoprivStateChanged(javax.swing.event.ChangeEvent evt) {
		if (rdbNoauthNopriv.isSelected()) {
			rdbAES.setEnabled(false);
			rdbDES.setEnabled(false);
			rdbMD5.setEnabled(false);
			rdbSHA.setEnabled(false);
			txtLlaveAutenticacion.setEnabled(false);
			txtLlavePrivacidad.setEnabled(false);
		}
	}

	private void rdbAuthNoprivStateChanged(javax.swing.event.ChangeEvent evt) {
		if (rdbAuthNopriv.isSelected()) {
			rdbAES.setEnabled(false);
			rdbDES.setEnabled(false);
			rdbMD5.setEnabled(true);
			rdbSHA.setEnabled(true);
			txtLlaveAutenticacion.setEnabled(true);
			txtLlavePrivacidad.setEnabled(false);
		}
	}

	private void rdbAuthPrivStateChanged(javax.swing.event.ChangeEvent evt) {
		if (rdbAuthPriv.isSelected()) {
			rdbAES.setEnabled(true);
			rdbDES.setEnabled(true);
			rdbMD5.setEnabled(true);
			rdbSHA.setEnabled(true);
			txtLlaveAutenticacion.setEnabled(true);
			txtLlavePrivacidad.setEnabled(true);
		}
	}

	private void btnRecibirTrapsMouseClicked(java.awt.event.MouseEvent evt) {
		r = new HiloEscucha(_Usuarios);
		PrintStream out = new PrintStream(new TextAreaOutputStream(txtaTraps));
		System.setOut(out);
		btnRecibirTraps.setEnabled(false);
		btnAnadirUsuario.setEnabled(false);
		rdbTrap.setEnabled(false);
		txtUsuarios.append("Si se desea adicionar usuarios es necesario reiniciar la recepcion de TRAPS" + "\n");
		btnReiniciar.setEnabled(true);
		hilo = new Thread(r);
		hilo.start();
	}

	private void btnAnadirUsuarioMouseClicked(java.awt.event.MouseEvent evt) {
		Users usuario;
		if (rdbTrap.isSelected()) {
			if (rdbNoauthNopriv.isSelected()) {
				usuario = new Users(txtNombreUsuario.getText(), "", "", "", "");
				txtUsuarios.append(txtNombreUsuario.getText() + "\n");
				_Usuarios.add(usuario);
			} else if (rdbAuthNopriv.isSelected()) {
				if (rdbMD5.isSelected()) {
					usuario = new Users(txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "", "");
					txtUsuarios.append(txtNombreUsuario.getText() + " MD5 " + "\n");
					_Usuarios.add(usuario);
				} else if (rdbSHA.isSelected()) {
					usuario = new Users(txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "", "");
					txtUsuarios.append(txtNombreUsuario.getText() + " SHA " + "\n");
					_Usuarios.add(usuario);
				}
			} else if (rdbAuthPriv.isSelected()) {
				if (rdbMD5.isSelected() && rdbDES.isSelected()) {
					usuario = new Users(txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "DES",
							txtLlavePrivacidad.getText());
					txtUsuarios.append(txtNombreUsuario.getText() + " MD5 " + " DES " + "\n");
					_Usuarios.add(usuario);
				} else if (rdbSHA.isSelected() && rdbDES.isSelected()) {
					usuario = new Users(txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "DES",
							txtLlavePrivacidad.getText());
					txtUsuarios.append(txtNombreUsuario.getText() + " SHA " + " DES " + "\n");
					_Usuarios.add(usuario);
				} else if (rdbMD5.isSelected() && rdbAES.isSelected()) {
					usuario = new Users(txtNombreUsuario.getText(), "MD5", txtLlaveAutenticacion.getText(), "AES",
							txtLlavePrivacidad.getText());
					txtUsuarios.append(txtNombreUsuario.getText() + " MD5 " + " AES " + "\n");
					_Usuarios.add(usuario);
				} else if (rdbSHA.isSelected() && rdbAES.isSelected()) {
					usuario = new Users(txtNombreUsuario.getText(), "SHA", txtLlaveAutenticacion.getText(), "AES",
							txtLlavePrivacidad.getText());
					txtUsuarios.append(txtNombreUsuario.getText() + " SHA " + " DES " + "\n");
					_Usuarios.add(usuario);
				}
			}
			txtNombreUsuario.setText("");
			txtHost.setText("");
			txtOID.setText("");
			txtValor.setText("");
			txtLlaveAutenticacion.setText("");
			txtLlavePrivacidad.setText("");
		}
	}

	private void btnReiniciarMouseClicked(java.awt.event.MouseEvent evt) {
		btnRecibirTraps.setEnabled(true);
		btnAnadirUsuario.setEnabled(true);
		rdbTrap.setEnabled(true);
		txtUsuarios.setText("");
		btnReiniciar.setEnabled(false);
		_Usuarios.clear();
		r.Detener();
		hilo.interrupt();
	}

	private void btnRecibirTrapsActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void rdbTrapActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void rdbTrapMouseClicked(java.awt.event.MouseEvent evt) {
		if (rdbTrap.isSelected()) {
			btnAnadirUsuario.setEnabled(true);
			btnRecibirTraps.setEnabled(true);
			btnReiniciar.setEnabled(false);
			txtHost.setEnabled(false);
			btnAnadirUsuario.setEnabled(true);
			btnEnviar.setEnabled(false);
			txtValor.setEnabled(false);
			txtOID.setEnabled(false);
			rdbOctectString.setEnabled(false);
			rdbInteger32.setEnabled(false);
			rdbUnsignedInteger32.setEnabled(false);
			rdbNull.setEnabled(false);
			rdbOID.setEnabled(false);
			rdbTimeTicks.setEnabled(false);
			rdbIP.setEnabled(false);
		}
	}

	private void rdbgetnextStateChanged(javax.swing.event.ChangeEvent evt) {
		if (rdbgetnext.isSelected()) {
			btnAnadirUsuario.setEnabled(false);
			txtHost.setEnabled(true);
			btnEnviar.setEnabled(true);
			txtOID.setEnabled(true);
			btnRecibirTraps.setEnabled(false);
			btnAnadirUsuario.setEnabled(false);
			txtValor.setEnabled(false);
			rdbOctectString.setEnabled(false);
			rdbInteger32.setEnabled(false);
			rdbUnsignedInteger32.setEnabled(false);
			rdbNull.setEnabled(false);
			rdbOID.setEnabled(false);
			rdbTimeTicks.setEnabled(false);
			rdbIP.setEnabled(false);
		}
	}

	private void rdbsetStateChanged(javax.swing.event.ChangeEvent evt) {
		if (rdbset.isSelected()) {
			btnAnadirUsuario.setEnabled(false);
			btnEnviar.setEnabled(true);
			txtHost.setEnabled(true);
			txtOID.setEnabled(true);
			btnRecibirTraps.setEnabled(false);
			btnAnadirUsuario.setEnabled(false);
			txtValor.setEnabled(true);
			rdbOctectString.setEnabled(true);
			rdbInteger32.setEnabled(true);
			rdbUnsignedInteger32.setEnabled(true);
			rdbNull.setEnabled(true);
			rdbOID.setEnabled(true);
			rdbTimeTicks.setEnabled(true);
			rdbIP.setEnabled(true);
		}
	}

	private void rdbgetStateChanged(javax.swing.event.ChangeEvent evt) {
		if (rdbget.isSelected()) {
			btnAnadirUsuario.setEnabled(false);
			txtHost.setEnabled(true);
			btnEnviar.setEnabled(true);
			btnAnadirUsuario.setEnabled(false);
			txtOID.setEnabled(true);
			btnRecibirTraps.setEnabled(false);
			txtValor.setEnabled(false);
			rdbOctectString.setEnabled(false);
			rdbInteger32.setEnabled(false);
			rdbUnsignedInteger32.setEnabled(false);
			rdbNull.setEnabled(false);
			rdbOID.setEnabled(false);
			rdbTimeTicks.setEnabled(false);
			rdbIP.setEnabled(false);
		}
	}

	private void rdbOctectStringActionPerformed(java.awt.event.ActionEvent evt) {
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NmsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NmsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NmsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NmsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NmsUI().setVisible(true);
			}
		});
	}

	private javax.swing.JButton btnAnadirUsuario;
	private javax.swing.JButton btnEnviar;
	private javax.swing.JButton btnRecibirTraps;
	private javax.swing.JButton btnReiniciar;
	private javax.swing.ButtonGroup btngroupAutenticacion;
	private javax.swing.ButtonGroup btngroupNivel;
	private javax.swing.ButtonGroup btngroupPrivacidad;
	private javax.swing.ButtonGroup btngroupTipoDato;
	public static javax.swing.ButtonGroup btngrouppdus;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JRadioButton rdbAES;
	private javax.swing.JRadioButton rdbAuthNopriv;
	private javax.swing.JRadioButton rdbAuthPriv;
	private javax.swing.JRadioButton rdbDES;
	private javax.swing.JRadioButton rdbIP;
	private javax.swing.JRadioButton rdbInteger32;
	private javax.swing.JRadioButton rdbMD5;
	private javax.swing.JRadioButton rdbNoauthNopriv;
	private javax.swing.JRadioButton rdbNull;
	private javax.swing.JRadioButton rdbOID;
	private javax.swing.JRadioButton rdbOctectString;
	private javax.swing.JRadioButton rdbSHA;
	private javax.swing.JRadioButton rdbTimeTicks;
	private javax.swing.JRadioButton rdbTrap;
	private javax.swing.JRadioButton rdbUnsignedInteger32;
	private javax.swing.JRadioButton rdbget;
	private javax.swing.JRadioButton rdbgetnext;
	private javax.swing.JRadioButton rdbset;
	private javax.swing.JTextField txtHost;
	private javax.swing.JTextField txtLlaveAutenticacion;
	private javax.swing.JTextField txtLlavePrivacidad;
	private javax.swing.JTextField txtNombreUsuario;
	private javax.swing.JTextField txtOID;
	private javax.swing.JTextArea txtUsuarios;
	private javax.swing.JTextField txtValor;
	private javax.swing.JTextArea txtaRespuestas;
	private javax.swing.JTextArea txtaTraps;
}