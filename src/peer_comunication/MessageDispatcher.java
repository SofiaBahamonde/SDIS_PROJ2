package peer_comunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import javax.crypto.SecretKey;

import utils.Utils;

public class MessageDispatcher implements Runnable{

	
	static MulticastSocket mc_socket;

	InetAddress mc_address;
	int mc_port;
	SecretKey secret_key;

	private int player_id;
	
	
	public MessageDispatcher(int mc_port,String mc_address, SecretKey secret_key, int player_id) {
		
		this.mc_port=mc_port;
		this.secret_key = secret_key;
		this.player_id = player_id;
		
		
		try {
			this.mc_address=InetAddress.getByName(mc_address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

		connect_to_multicast_socket();
}
	
	
	public void connect_to_multicast_socket() {
		try {
			mc_socket = new MulticastSocket(mc_port);

			mc_socket.setTimeToLive(1);
			mc_socket.joinGroup(mc_address);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
}
	@Override
	public void run() {
		byte[] buffer= new byte[Utils.PACKET_SIZE];

		
		while(true) {
			DatagramPacket mc_packet = new DatagramPacket(buffer, buffer.length);
			try {
				mc_socket.receive(mc_packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			new Thread(new PacketHandler(mc_packet,secret_key,player_id)).start();
} 
		
	}


	public void sendMessage(String message,String content,int senderID) {
		
		
		
		byte[] packet = null;
		
		switch(message) {
		case "NEWPLAYER":
			packet = Message.NEWPLAYER(content,senderID,secret_key);
			break;
			
		case "INITIALCARDS":
			packet = Message.INITIALCARDS(content, senderID, secret_key);
			break;
		
		case "BLACKCARD":
			packet = Message.BLACKCARD(content, senderID, secret_key);
		default:
			break;
		}
		
		if(packet!=null) {
			DatagramPacket dpacket=new DatagramPacket(packet,packet.length,mc_address,mc_port);
			try {
				mc_socket.send(dpacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
