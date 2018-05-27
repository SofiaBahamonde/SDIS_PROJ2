package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import utils.AESEncrypter;
import utils.Utils;

public class MessageDispatcher implements Runnable{

	
	static MulticastSocket mc_socket;

	InetAddress mc_address;
	int mc_port;
	SecretKey secret_key;

	private int player_id;
	private boolean game_ended;
	
	
	public MessageDispatcher(int mc_port,String mc_address, SecretKey secret_key, int player_id) {
		
		this.mc_port=mc_port;
		this.secret_key = secret_key;
		this.player_id = player_id;
		this.game_ended=false;
		
		
		try {
			this.mc_address=InetAddress.getByName(mc_address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

		connect_to_multicast_socket();
}
	
	public void endGame() {
		this.game_ended=true;
		System.exit(0);
		
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

		
		while(!game_ended) {
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

		byte[] packet = createPacket(message,content,senderID);
		

		
		if(packet!=null) {
			DatagramPacket dpacket=new DatagramPacket(packet,packet.length,mc_address,mc_port);
			try {
				mc_socket.send(dpacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public byte[] createPacket(String function, String content, int sender_id) {
		String header=function+" "+sender_id+Utils.CRLF;
		String message=header + content;

		try {
			AESEncrypter des= new AESEncrypter(secret_key);
			message=des.encrypt(message);
			
			byte[] packet=message.getBytes();

			return packet;
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
