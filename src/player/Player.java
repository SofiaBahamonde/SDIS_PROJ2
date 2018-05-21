package player;

import javax.crypto.SecretKey;
import javax.net.ssl.*;


import peer_comunication.MessageDispatcher;
import ui.GameUI;
import ui.ServerUI;
import utils.SecretKeyGenerator;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.ArrayList;



public class Player {

    static PrintStream out;
    static BufferedReader in;
    
    private static MessageDispatcher dispatcher;
    private static SecretKey secret_key;
    
    private static int player_id = -1;
    private static String username;
    private static boolean owner = false;
    
    private static ArrayList<String> white_cards = new ArrayList<String>();
    private static String black_card = "";
    private static boolean jury = false;
    //private int score;
    
    public static MessageDispatcher getDispatcher() {
		return dispatcher;
	}

	public static void main(String[] args) throws Exception {
		String host = Utils.HOST;
		int port = Utils.PORT;

		if (args.length > 2) {
			System.out.println("BAD USAGE- The arguments are: Server Ip and Server Port");
		} else if(args.length==2){
			host = args[0];
			port = Integer.parseInt(args[1]);
		}

		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) sf.createSocket(InetAddress.getByName(host), port);
		socket.setEnabledCipherSuites(sf.getSupportedCipherSuites());

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

    public static int getPlayer_id() {
		return player_id;
	}

	private static void paseRequest(String request) throws IOException {
    	String data;
    	
        switch (request){
            case "WELCOME":
                username = ServerUI.welcome();
                sendResponse(username);
                player_id = Integer.parseInt(in.readLine());
                break;
                
            case "MENU":
            	data = ServerUI.menu();
            	sendResponse(data);
            	break;
            	
            case "NEW_ROOM":
            	owner = true;
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
                                
                String port = in.readLine();
                String mcast_addr = in.readLine();
                secret_key = SecretKeyGenerator.decodeKeyFromString(in.readLine());
                
                dispatcher = new MessageDispatcher(Integer.parseInt(port),mcast_addr,secret_key,player_id);
                new Thread(dispatcher).start();
                
                dispatcher.sendMessage("NEWPLAYER",username, player_id);
                
                if(owner) {
	                data = ServerUI.startGame();
	                sendResponse(data);
                }
                
            	break;
            	
            default:
                break;
        }
    }

    private static void sendResponse(String response){
        out.println(response);
        out.flush();
    }

	public static void addWhiteCard(String card) {
		white_cards.add(card);
		
	}

	public static void showWhiteCards() {
		GameUI.showWhiteCards(white_cards);
	}

	public static void setBlackCard(String card) {
		black_card = card;
	}

	public static void isJury(boolean b) {
		jury= b;
	}

	public static void startRound() {
		if(jury) {
			System.out.println("Waiting for Players answers");
		}else
			GameUI.makePlay(black_card, white_cards);
		
	}
}
