package peer_comunication;

import java.net.DatagramPacket;

public class PacketHandler  implements Runnable{


	private DatagramPacket packet;
	private String[] headerToken;
	private String header;
	private byte[] body;

	public PacketHandler(DatagramPacket packet) {
		this.packet = packet;
	}

	public void run() {
		this.headerToken = HeaderExtractor();
		switch (headerToken[0]) {

		case "NEWPLAYER":
			this.body = BodyExtractor();
			NEWPLAYER_handler();
			break;
		}

}

	private void NEWPLAYER_handler() {
		// TODO Auto-generated method stub
		
	}

	private byte[] BodyExtractor() {
		// TODO Auto-generated method stub
		return null;
	}

	private String[] HeaderExtractor() {
		// TODO Auto-generated method stub
		return null;
	}

}
