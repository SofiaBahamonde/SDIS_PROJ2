package player;

import javax.crypto.SecretKey;
import javax.net.ssl.*;

import game.White_Card;
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
    private SecretKey secretKey;

    public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket socket = (SSLSocket) sf.createSocket(Utils.HOST, Utils.PORT);
    socket.setEnabledCipherSuites(sf.getSupportedCipherSuites());

    Player c = new Player();

    c.out = new PrintStream(socket.getOutputStream());
    c.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String request = null;
    while (((request = in.readLine()) != null)) {
        paseRequest(request);
    }

    in.close();
    out.close();
    socket.close();
  }

    private static void paseRequest(String request) throws IOException {
        switch (request){
            case "WELCOME":
                String username = ServerUI.welcome();
                sendResponse(username);
                break;
                
            case "MENU":
            	String option = ServerUI.menu();
            	sendResponse(option);
            	break;
            	
            case "NEW_ROOM":
            	String room_name1 = ServerUI.newRoom();
            	sendResponse(room_name1);
            	String room_pw = ServerUI.password();
            	sendResponse(room_pw);
            	break;
            	
            case "SHOW_ROOMS":
            	ServerUI.showRooms();
            	String data = in.readLine();
                while (!data.equals("STOP_SHOW")){
                	System.out.println(data);
                	data = in.readLine();
                }
            	break;
            case "ENTER_ROOM":
            	String room_name2 = ServerUI.enterRoom();
            	sendResponse(room_name2);
            
            	if(in.readLine().equals("PW")) {
            		String password2 = ServerUI.password();
            		sendResponse(password2);
            	}
            	
            	String result = in.readLine();
            	System.out.println(result);
            	
            default:
                break;
        }
    }

    private static void sendResponse(String response){
        out.println(response);
        out.flush();
    }
}
