package net.tfobz.synchronization.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.tfobz.synchronization.chat.server.ChatServer;

public class ChatClientTest extends ChatServer
{

	public static final int PORT = 65535;

	public static void main(String[] args) {
		Socket client = null;
		ExecutorService ex = Executors.newCachedThreadPool();
		for(int i = 0; i < 50; i++) {
			final int I = i;
			ex.execute(
			new Thread() {
				public void run() {
					try {
						Socket client = new Socket(args[1], PORT);

						BufferedReader in = 
								new BufferedReader( new InputStreamReader(client.getInputStream()));
						PrintStream out = new PrintStream(client.getOutputStream());
						BufferedReader consoleIn =
								new BufferedReader(new InputStreamReader(System.in));

						// sending the name of the client to the server

						out.println(I);

						new ChatClientThread(in).start();
						int i = -1;
						int ende = (int) (Math.random() * 10);
						while (true) {
							i++;
							String line = null;
							if (i == ende) {
								break;
							}
							line = "Hallo";

							try {
								TimeUnit.SECONDS.sleep((long)(Math.random() * 5));
							} catch (InterruptedException e) { ; }
							out.println(line);
						}
					} catch (IOException e) {
						System.out.println(e.getClass().getName() + ": " + e.getMessage());
					} finally {
						System.out.println("ou");
						try { client.close(); } catch (Exception e1) { ; }
					}
				}
			});
			
		}
		ex.shutdown();
		while(!ex.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(outputStreams.size());
		Enumeration<PrintStream> e = outputStreams.elements();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}

			
	}

}
