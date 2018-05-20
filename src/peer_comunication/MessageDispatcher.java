package peer_comunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MessageDispatcher implements Runnable{

	
	public static MulticastSocket mc_socket;

	public InetAddress mc_address;
	public int mc_port;
	public static final int PACKET_SIZE=2048;
	
	public MessageDispatcher(String mc_port,String mc_address) {
		
		this.mc_port=Integer.parseInt(mc_port);
		
		try {
			this.mc_address=InetAddress.getByName(mc_address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
		connect_to_multicast_socket();
		while(true) {
			DatagramPacket mc_packet = new DatagramPacket(buffer, buffer.length);
			try {
				mc_socket.receive(mc_packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("received packet");
			new Thread(new PacketHandler(mc_packet)).start();
} 
		
	}


	public void sendMessage(byte[] packet) {
		DatagramPacket dpacket=new DatagramPacket(packet,packet.length,mc_address,mc_port);
		try {
			mc_socket.send(dpacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
