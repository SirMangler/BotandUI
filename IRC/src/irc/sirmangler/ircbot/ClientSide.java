package irc.sirmangler.ircbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ClientSide implements Runnable{
	static BufferedWriter writer;
	static BufferedReader reader;
	
	static BotMenu bot;
	
	static String ip = "irc.twitch.tv";
	static int port = 6667;
	static String user = "somebot";
	static String password = ""; // removed
	static String channel = "#sirmangler";
	static boolean loginwitnck = true;
	
	static boolean running = true;
	static Thread thread;
	
	public static boolean startSock() {
		thread = new Thread(new ClientSide());
		running = true;
		thread.start();
		return true;
	}
	
	 public static void main(String[] args) throws IOException, InterruptedException {
		 try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dialog.dialog();
		Thread.sleep(500);
		startSock();
	 }
	 
	 public static boolean writeline(String line) {	 
		 try {
			 writer.write(line+"\n\r");
			 if(line.toUpperCase().startsWith("PONG") && Dialog.chatmode==true) { } 
			 else if(line.toUpperCase().startsWith("PRIVMSG ")) {
				 Dialog.area.append(" [<] "+line+"\n");
			 } else { Dialog.area.append(" < "+line+"\n"); }
			 System.out.println(" < "+line);
			 writer.flush();
			 Dialog.area.setCaretPosition(Dialog.area.getDocument().getLength());	 
			 if(line.toUpperCase().startsWith("JOIN ")) {
				 String[] a = line.split(" "); 
				 channel = a[1];
			 }
			 

			 return true;
		} catch (IOException e) {
			return false;
		}	 
	 }
	 
	 public static boolean writechat(String line) {
		 try {
			 writer.write("PRIVMSG "+channel+" "+line+"\n\r");
			 Dialog.area.append(" [<] "+line+"\n");
			 System.out.println(" < "+"PRIVMSG "+channel+" "+line);
			 writer.flush();
			 
			 Dialog.area.setCaretPosition(Dialog.area.getDocument().getLength());
			 return true;
		} catch (IOException e) {
			return false;
		}	 
	 }


	 
	@Override
	public void run() {
		try {
		Socket socket = new Socket(ip, port);
       	writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream( )));
        reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream( )));
        
        System.out.println("Started");
        
        writeline("PASS "+password);
        
        if(loginwitnck==true) writeline("NICK "+user);
        else writeline("USER "+user);
        
        String line = null;
        while ((line = reader.readLine( )) != null && running == true) {
            System.out.println(" >" + line); 
        	if(Dialog.chatmode == true) {
        		if(line.contains(".tmi.twitch.tv PRIVMSG #")) {
        			String[] a = line.split("!");
        			String[] b = line.split(":");
        			Dialog.area.append(" [>] <"+a[0].replace(":", "")+"> "+b[2]+"\n");
        		}
        	}
        	else {
                Dialog.area.append(" > "+line+"\n");
        	}
            
			
            if(line.contains(" "+user.toLowerCase()+" :>")) {
     	       writeline("JOIN "+channel);
            }
            
        	if(line.startsWith("PING")) {
        		writeline("PONG");
        	}
        }
        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}