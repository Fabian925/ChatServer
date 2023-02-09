package net.tfobz.synchronization.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;

public class ChatClientThread extends Thread {
	private BufferedReader in = null;
	private JEditorPane editorPane;
	private int profilbild = -1;
	private String icon;

	public ChatClientThread(BufferedReader in, JEditorPane editorPane, int profilbild) {
		this.in = in;
		this.editorPane = editorPane;
		this.profilbild = profilbild;
	}

	@Override
	public void run() {
		if(profilbild == 1) {
			icon = "src\\profilbild1.png";
		}
		try {
			while (true) {
				String line = in.readLine();
				editorPane.setText("<HTML><BODY><font size=+3>"
						+ editorPane.getText().substring(editorPane.getText().indexOf("<font size=+3>") + 1,
								editorPane.getText().indexOf("</font>") +1)
						+"<img src = " + icon + ">"+ line + "</font></BODY></HTML>");
			}
		} catch (SocketException e) {
			System.out.println("Connection to ChatServer lost, ignore exception");
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
