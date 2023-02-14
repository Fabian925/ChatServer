package net.tfobz.synchronization.chat.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextField;
import javax.swing.text.html.HTMLEditorKit;

import net.tfobz.synchronization.chat.server.ChatServer;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class GUI {

	public static final int PORT = 65535;
	Socket client = null;
	BufferedReader in = null;
	PrintStream out = null;

	private JFrame frmChat;
	private JTextField textField;
	private JLabel lblName;
	private JButton btnAbmelden;
	private JButton btnProfilbild;
	private JTextField textField_1;
	private JButton btnEnter;
	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	private int profilbild = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		frmChat = new JFrame();
		frmChat.setTitle("ChatClientGUI");
		frmChat.setBounds(100, 100, 1000, 600);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(10, 40, 70, 30);
		frmChat.getContentPane().add(lblName);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(80, 40, 500, 30);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n')
					btnAbmelden.doClick();
			}
		});
		frmChat.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(15, 500, 890, 30);
		textField_1.setColumns(10);
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n')
					btnEnter.doClick();
			}
		});
		frmChat.getContentPane().add(textField_1);

		btnAbmelden = new JButton("Anmelden");
		btnAbmelden.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAbmelden.setBounds(815, 40, 155, 30);
		btnAbmelden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnAbmelden.getText().equals("Anmelden")) {
					String name = textField.getText();
					if (!name.equals("")) {
						// Profilbild?
						if (profilbild == -1) {
							JOptionPane.showMessageDialog(GUI.this.frmChat,
									"Bitte wählen Sie zuerst ein Profilbild aus.", "Kein Profilbild",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								client = new Socket("localhost", PORT);
								in = new BufferedReader(new InputStreamReader(client.getInputStream()));
								out = new PrintStream(client.getOutputStream());
								// Profilbild und Name schicken
								out.println(profilbild + ";" + name);

								// Name doppelt?
								if (client.getInputStream().read() == 1) {
									JOptionPane.showMessageDialog(GUI.this.frmChat,
											"Der Name ist bereits vorhanden. "
													+ "\nBitte verwenden Sie einen anderen Namen",
											"Doppelter Name", JOptionPane.ERROR_MESSAGE);
								} else {
									btnAbmelden.setText("Abmelden");
									btnProfilbild.setEnabled(false);
									textField.setEnabled(false);
									new ChatClientThread(in, editorPane).start();
								}

							} catch (IOException e1) {
								System.out.println(e1.getClass().getName() + ": " + e1.getMessage());
							}
						}
					}
				} else {
					btnAbmelden.setText("Anmelden");
					btnProfilbild.setEnabled(true);
					textField.setEnabled(true);
					try {
						client.close();
					} catch (Exception e1) {
						;
					}
				}
			}
		});
		frmChat.getContentPane().add(btnAbmelden);

		btnProfilbild = new JButton("Profilbild wechseln");
		btnProfilbild.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnProfilbild.setBounds(590, 40, 220, 30);
		btnProfilbild.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BilderAuswahlGUI gui = new BilderAuswahlGUI();
				gui.setModal(true);
				gui.setVisible(true);
				profilbild = gui.getAusgesuchtesProfilbild();
			}
		});
		frmChat.getContentPane().add(btnProfilbild);

		btnEnter = new JButton("");
		btnEnter.setIcon(new ImageIcon(
				GUI.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/enter-icon.png")));
		btnEnter.setBounds(915, 500, 50, 30);
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println(textField_1.getText());
				textField_1.setText("");
			}
		});
		frmChat.getContentPane().add(btnEnter);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 90, 950, 400);
		frmChat.getContentPane().add(scrollPane);

		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		editorPane.setContentType("text/html");
		editorPane.setText( "<HTML><BODY><font size=+2></font></img></BODY></HTML>");
		editorPane.setEditable(false);
	}
}
