package net.tfobz.synchronization.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatServerThread extends Thread
{
	private Socket client = null;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	public ChatServerThread(Socket client) throws IOException {
		this.client = client;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintStream(client.getOutputStream());
	}
	
	@Override
	public void run() {
		String name = null;
		try {
			synchronized (in) {
				name = in.readLine();
			}
			synchronized (ChatServer.outputStreams) {
				
				if(ChatServer.outputStreams.containsKey(name)) {
					throw new NameDoppeltException("Der Name ist bereits vorhanden");
				}
				ChatServer.outputStreams.put(name, out);				
			}
			synchronized (ChatServer.outputStreams) {	
				System.out.println(name + " signed in. " + ChatServer.outputStreams.size() + " users");
				for (PrintStream outs: ChatServer.outputStreams.values())
					outs.println("<b>" + name+ "</b>" + " signed in" + "<br>");
			}
			
			while (true) {
				String line = null;
				synchronized (in){
					line = in.readLine();
				}
				
				if (line == null)
					break;
				synchronized (ChatServer.outputStreams) {
					for (PrintStream outs: ChatServer.outputStreams.values())
						outs.println("<b>" + name+ "</b>" + ": " + line + "<br>");					
				}
			}
			synchronized (ChatServer.outputStreams) {
				synchronized (out) {
					ChatServer.outputStreams.remove(name, out);
				}
				System.out.println(name + " signed out. " + ChatServer.outputStreams.size() + " users");
				for (PrintStream outs: ChatServer.outputStreams.values())
					outs.println("<b>" + name+ "</b>" + " signed out" + "<br>");
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			if (out != null)
				synchronized (ChatServer.outputStreams) {
					synchronized (out) {
						ChatServer.outputStreams.remove(name, out);
					}
				}
		} catch (NameDoppeltException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			//TODO ausgabe wenn Name doppelt sind schreiben wir das da hinein
			try {
				client.getOutputStream().write(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try { client.close(); } catch (Exception e1) { ; }
		}
	}
}
