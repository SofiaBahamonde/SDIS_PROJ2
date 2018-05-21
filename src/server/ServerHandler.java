package server;

import javax.net.ssl.SSLSocket;

import player.PlayerInfo;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class ServerHandler implements Runnable {
	
	static int player_counter =0;

	PlayerInfo player;
    SSLSocket socket;
    Room room;
    
    PrintStream out;
    BufferedReader in;

    public ServerHandler(SSLSocket socket) {
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
		sendRequest("ROOM");
		sendRequest(room.toString());
		sendRequest("STOP");
		
		sendRequest(room.getPort());
		sendRequest(room.getAddress());
		sendRequest(room.getKey());
		
		
		
		
	 
    }
    
  
    private void menu() {
    	boolean stop = false;
    	try {
    		
    		while(!stop) {
		        sendRequest("MENU");
		        
		        String option = in.readLine();
		        
		        switch(option) {
		        case "1":
		        	showRooms();
		        	break;
		        
		        case "2":
		        	stop = enterRoom();
		        	break;
		        
		        case "3":
		        	stop = createRoom();
		        	break;

		        default:
		        	break;
		        	
		        }	 
    		}
    		   		        
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	private boolean createRoom() throws IOException {
    	sendRequest("NEW_ROOM");
    	
    	String room_name1 = in.readLine();
    	String password1 = in.readLine();
    	
    	Server.addRoom(room_name1, password1,player);
    	this.room = Server.getRoom(room_name1);
    	
    	room();
    	
    	return true;
		
	}

	private boolean enterRoom() throws IOException {
		sendRequest("ENTER_ROOM");
    	String room_name2 = in.readLine();
    	
    	Room room = Server.getRoom(room_name2);
    	
    	if(room !=null) {
    		if(room.isPrivate()) {
    			sendRequest("PW");
    			String password2 = in.readLine();
    			
    			if(!room.getPassword().equals(password2)) {
    				sendRequest("EEROR: INVALID_PW");
    				return false;
    			}
    		}else
    			sendRequest("");
    	}else {
    		sendRequest("ERROR: INVALID_ROOM");
    		return false;
    	}
    	
    	room.addPlayer(player);
    	System.out.println("Player has joined " + room_name2 + " - " + player.toString());
    	
    	sendRequest("SUCCESS");
    	
    	this.room = room;
    	room();
    	
    	return true;
    	
		
	}

	private void showRooms() {
    	sendRequest("SHOW_ROOMS");
    	sendRequest(Utils.toStringArray(Server.getRooms()));
    	sendRequest("STOP");
		
	}

	private void log() {
	   try {
           
           sendRequest("WELCOME");
           
           String username = in.readLine();
           
           player = new PlayerInfo(username,socket);
           
           Server.addPlayer(player);
           
           sendRequest(Integer.toString(player_counter));
           player_counter++;
           

       } catch (IOException e) {
           e.printStackTrace();
       }
		
	}

	private void sendRequest(String request){
        out.println(request);
        out.flush();
    }
}
