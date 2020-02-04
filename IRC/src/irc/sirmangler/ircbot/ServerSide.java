package irc.sirmangler.ircbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ServerSide 
{
	static ServerSocket serverSocket;
	public static void main(String[] args)
	{
		try {
			serverSocket = new ServerSocket(27075);
		System.out.println("Hosting socket");
		while (true)
		{
			Socket socket = serverSocket.accept();		
			SocketHandler.handle(socket);
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}

	class SocketHandler implements Runnable {

	static Socket so = null;
	public static void handle(Socket s)
	{
		so = s;
		Executors.newSingleThreadExecutor().submit(new SocketHandler());
	}
	
	Socket socket = null;
	@Override
	public void run() {
		socket = so;
		try{
			System.out.println("Connection at " + socket);
			BufferedReader in;
			
			in = new BufferedReader(
			new InputStreamReader(socket.getInputStream()));
				
			BufferedWriter out;
			
			out = new BufferedWriter(
			new OutputStreamWriter(socket.getOutputStream()));
				
			out.write("Hello");
			out.flush();
			
			String msg;
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				Thread.sleep(1000);
				out.write("Hey");
				out.flush();
		} 
			
		} catch (IOException e) {
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void msg(String string)
	{
		System.out.println("Attempt sending");
		String msg = string.replace("msg ", "");
		System.out.println(msg); 
	}
	
}
