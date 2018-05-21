package player;

import javax.net.ssl.SSLSocket;

public class PlayerInfo {
	
	String username;
	SSLSocket socket;
	boolean owner = false;
	
	
	
	public PlayerInfo(String username, SSLSocket socket) {
		this.username = username;
		this.socket = socket;
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


}
