package player;

import javax.net.ssl.SSLSocket;

public class PlayerInfo {
	
	int id;
	String username;
	boolean owner = false;
	
	
	
	
	public PlayerInfo(String username, int id) {
		this.username = username;
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
