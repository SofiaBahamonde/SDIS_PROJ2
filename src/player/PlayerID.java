package player;

import javax.net.ssl.SSLSocket;

public class PlayerID {
	
	String username;
	SSLSocket socket;
	private int player_id;
	
	public PlayerID(String username, SSLSocket socket) {
		this.username = username;
		this.socket = socket;
	}
	
	@Override
	public String toString() {
		return username;
	}


}
