package net.tfobz.synchronization.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JEditorPane;

public class ChatClientThread extends Thread
{
	private BufferedReader in = null;
	private JEditorPane editorPane;
	
	public ChatClientThread(BufferedReader in, JEditorPane editorPane) {
		this.in = in;
		this.editorPane = editorPane;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String line = in.readLine();
				editorPane.setText(editorPane.getText()+ '\n' + line);
			}
		} catch (SocketException e) {
			System.out.println("Connection to ChatServer lost, ignore exception");
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
