package player;

import javax.net.ssl.SSLSocket;

public class PlayerInfo {
	
	int id;
	String username;
	SSLSocket socket;
	boolean owner = false;
	
	
	
	
	public PlayerInfo(String username, SSLSocket socket, int id) {
		this.username = username;
		this.socket = socket;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return username;
	}
	
	public boolean isOwner() {
		return owner;
	}
	
	public void setOwner() {
		owner = true;
	}

	public int getPlayerID() {
		return id;
	}


}
