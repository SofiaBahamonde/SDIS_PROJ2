package player;

import javax.crypto.SecretKey;
import javax.net.ssl.*;

import game.White_Card;
import peer_comunication.MessageDispatcher;
import peer_comunication.SecretKeyGenerator;
import ui.ServerUI;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;


public class Player {

    static PrintStream out;
    static BufferedReader in;
    private ArrayList<White_Card> current_cards;
    private int score;
    
    private static MessageDispatcher dispatcher;
    
    private static SecretKey secret_key;
    private static String mcast_addr;
    private static String port;

    public static MessageDispatcher getDispatcher() {
		return dispatcher;
	}

	public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket socket = (SSLSocket) sf.createSocket(Utils.HOST, Utils.PORT);
    socket.setEnabledCipherSuites(sf.getSupportedCipherSuites());

    Player c = new Player();

    out = new PrintStream(socket.getOutputStream());
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String request = null;
    while (((request = in.readLine()) != null)) {
        paseRequest(request);
    }

    in.close();
    out.close();
    socket.close();
  }

    private static void paseRequest(String request) throws IOException {
    	String data;
    	
        switch (request){
            case "WELCOME":
                data = ServerUI.welcome();
                sendResponse(data);
                break;
                
            case "MENU":
            	data = ServerUI.menu();
            	sendResponse(data);
            	break;
            	
            case "NEW_ROOM":
            	data = ServerUI.newRoom();
            	sendResponse(data);
            	
            	data = ServerUI.password();
            	sendResponse(data);
            	break;
            	
            case "SHOW_ROOMS":
            	ServerUI.showRooms();
            	
            	data = in.readLine();
                while (!data.equals("STOP")){
                	System.out.println(data);
                	data = in.readLine();
                }
            	break;
            	
            case "ENTER_ROOM":
            	data = ServerUI.enterRoom();
            	sendResponse(data);
            	
            	data = in.readLine();

            	if(data.equals("PW")) {
            		data = ServerUI.password();
            		sendResponse(data);
            		data = in.readLine();
            	}
            	
            	System.out.println(data);
            	break;
            case "ROOM":
            	ServerUI.showRoom();
            	
            	data = in.readLine();
                while (!data.equals("STOP")){
                	System.out.println(data);
                	data = in.readLine();
                }
                                
                port = in.readLine();
                mcast_addr = in.readLine();
                secret_key = SecretKeyGenerator.decodeKeyFromString(in.readLine());
                
                dispatcher = new MessageDispatcher(port,mcast_addr);
                
                
                System.out.println(port);
                System.out.println(mcast_addr);
                
                data = ServerUI.startGame();
                sendResponse(data);
                
            	break;
            	
            default:
                break;
        }
    }

    private static void sendResponse(String response){
        out.println(response);
        out.flush();
    }
}
