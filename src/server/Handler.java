package server;

import javax.net.ssl.SSLSocket;

import player.PlayerID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Handler implements Runnable {

    SSLSocket socket;
    
    static PrintStream out;
    BufferedReader in;

    public Handler(SSLSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    	
    	logUser();
    	showMenu();


    }
    
  

    private void showMenu() {
    	try {
			out = new PrintStream(socket.getOutputStream());
	        sendRequest("MENU");
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private void logUser() {
	   try {
           out = new PrintStream(socket.getOutputStream());
           sendRequest("WELCOME");
           
           String username;
           
           in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           username = in.readLine();
           
           
           Server.addPlayer(new PlayerID(username, socket));
           System.out.println(username + " has joined the server.");
           


       } catch (IOException e) {
           e.printStackTrace();
       }
		
	}

	private static void sendRequest(String request){
        out.println(request);
        out.flush();
    }
}
