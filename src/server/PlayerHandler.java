package server;

import javax.net.ssl.SSLSocket;

import player.PlayerID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class PlayerHandler implements Runnable {

	PlayerID player;
    SSLSocket socket;
    
    static PrintStream out;
    BufferedReader in;

    public PlayerHandler(SSLSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    	
    	try {
			out = new PrintStream(socket.getOutputStream());
	    	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	logUser();
    	showMenu();


    }
    
  
    private void showMenu() {
    	try {
    		
    		while(true) {
				out = new PrintStream(socket.getOutputStream());
		        sendRequest("MENU");
		        
		        String option = in.readLine();
		        
		        switch(option) {
		        case "1":
		        	sendRequest("SHOW_ROOMS");
		        	break;
		        case "2":
		        	sendRequest("NEW_ROOM");
		        	
		        	String name = in.readLine();
		        	String password = in.readLine();
		        	
		        	Server.addRoom(name, password,player);
		        	
		        	break;
		        default:
		        	break;
		        	
		        }	 
    		}
    		   		        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private void logUser() {
	   try {
           
           sendRequest("WELCOME");
           
           String username = in.readLine();
           
           player = new PlayerID(username,socket);
           
           Server.addPlayer(player);
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
