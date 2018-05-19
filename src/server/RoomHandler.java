package server;

import javax.net.ssl.SSLSocket;

import player.PlayerID;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class RoomHandler implements Runnable {

	PlayerID player;
    SSLSocket socket;
    
    static PrintStream out;
    BufferedReader in;

    public RoomHandler(SSLSocket socket) {
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

    	
    	log();
    	menu();


    }
    
    private void room() {
    	
    }
    
  
    private void menu() {
    	try {
    		
    		while(true) {
				out = new PrintStream(socket.getOutputStream());
		        sendRequest("MENU");
		        
		        String option = in.readLine();
		        
		        switch(option) {
		        case "1":
		        	sendRequest("SHOW_ROOMS");
		        	sendRequest(Utils.toStringArray(Server.getRooms()));
		        	sendRequest("STOP_SHOW");

		        	break;
		        
		        case "2":
		        	sendRequest("ENTER_ROOM");
		        	String room_name2 = in.readLine();
		        	
		        	Room room = Server.getRoom(room_name2);
		        	
		        	if(room !=null) {
		        		if(room.isPrivate())
		        			sendRequest("PW");
		        			String password2 = in.readLine();
		        			
		        			if(!room.getPassword().equals(password2)) {
		        				sendRequest("EEROR: INVALID_PW");
		        				break;
		        			}
		        	}else {
		        		sendRequest("ERROR: INVALID_ROOM");
		        		break;
		        	}
		        	
		        	room.addPlayer(player);
		        	System.out.println("Player has joined " + room_name2 + " - " + player.toString());
		        	
		        	sendRequest("SUCCESS");
		        	room();
		        	
		        	break;
		        
		        case "3":
		        	sendRequest("NEW_ROOM");
		        	
		        	String room_name1 = in.readLine();
		        	String password1 = in.readLine();
		        	
		        	Server.addRoom(room_name1, password1,player);
		        	
		        	room();
		        	
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

	private void log() {
	   try {
           
           sendRequest("WELCOME");
           
           String username = in.readLine();
           
           player = new PlayerID(username,socket);
           
           Server.addPlayer(player);
           

       } catch (IOException e) {
           e.printStackTrace();
       }
		
	}

	private static void sendRequest(String request){
        out.println(request);
        out.flush();
    }
}
