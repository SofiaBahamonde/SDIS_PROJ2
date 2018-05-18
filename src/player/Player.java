package player;

import javax.net.ssl.*;

import ui.ServerUI;
import utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Player {

    static PrintStream out;
    static BufferedReader in;

    public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket socket = (SSLSocket) sf.createSocket(Utils.HOST, Utils.PORT);
    socket.setEnabledCipherSuites(sf.getSupportedCipherSuites());

    Player c = new Player();

    c.out = new PrintStream(socket.getOutputStream());
    c.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String request = null;
    while (((request = in.readLine()) != null)) {
        getRequest(request);
    }

    in.close();
    out.close();
    socket.close();
  }

    private static void getRequest(String request) {
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
            	String room_name = ServerUI.newRoom();
            	sendResponse(room_name);
            	String room_pw = ServerUI.password();
            	sendResponse(room_pw);
            	break;
            	
            case "SHOW_ROOMS":
            	ServerUI.showRooms();
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
