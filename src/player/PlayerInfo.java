package player;

public class PlayerInfo {
	
	int id;
	String username;
	boolean owner = false;
	int score =0;
	
	
	
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
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getName() {
		return username;
	}


}
