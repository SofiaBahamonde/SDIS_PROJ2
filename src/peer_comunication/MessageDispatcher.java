package peer_comunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import javax.crypto.SecretKey;

public class MessageDispatcher implements Runnable{

	
	public static MulticastSocket mc_socket;

	public InetAddress mc_address;
	public int mc_port;
	public SecretKey secret_key;
	public static final int PACKET_SIZE=2048;
	
	public MessageDispatcher(int mc_port,String mc_address, SecretKey secret_key) {
		
		this.mc_port=mc_port;
		this.secret_key = secret_key;
		
		
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
		byte[] buffer= new byte[PACKET_SIZE];

		
		while(true) {
			DatagramPacket mc_packet = new DatagramPacket(buffer, buffer.length);
			try {
				mc_socket.receive(mc_packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			new Thread(new PacketHandler(mc_packet,secret_key)).start();
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
		
		DatagramPacket dpacket=new DatagramPacket(packet,packet.length,mc_address,mc_port);
		try {
			mc_socket.send(dpacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
