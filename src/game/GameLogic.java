package game;

import java.util.ArrayList;
import java.util.Collections;

import player.Player;
import player.PlayerInfo;
import ui.GameUI;
import utils.Utils;

public class GameLogic {
	private static ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	private static int players_ready = 1;

	private static int round = 0;
	private static int jury = 0;
	
    private static ArrayList<Integer>scores =new ArrayList<Integer>();
    private static ArrayList<String>players_scores =new ArrayList<String>();
    private static ArrayList<Integer>players_that_answered =new ArrayList<Integer>();
	
	public static void start() {

		GameUI.startGame();
		Player.getDispatcher().sendMessage("START", "start", -1);

		Utils.sleep(100);

		round();

	}
	

	public static void round() {
		if(round!=0) {
			
			removePlayersNotAnswered();
			if (jury != players.size()-1)
				jury++;
			else
				jury = 0;
		}
		if (round < Utils.MAX_ROUNDS) {
		// draw black cards
		String black_card = Player.getBlackCard();
		Player.getDispatcher().sendMessage("BLACKCARD", black_card, -1);

		Utils.sleep(100);

		// choose jury
		Player.getDispatcher().sendMessage("NEWJUDGE", "jury", players.get(jury).getPlayerID());
			// start round
		Utils.sleep(100);
		Player.getDispatcher().sendMessage("START_ROUND", "round", -1);
		round++;
	}
		else {
			Player.getDispatcher().sendMessage("GAME_END", "end", -1);
		}
	}

	private static void removePlayersNotAnswered() {

		
		for(int i=0; i<players.size();i++) {
			if(!players_that_answered.contains(players.get(i).getPlayerID()) && players.get(i).getPlayerID()!=Player.getPlayer_id()&& i!=jury) {
				System.out.println("PLAYER "+players.get(i).getName()+" REMOVED");
				players.remove(i);
				i--;
			}
		}
		players_that_answered=new ArrayList<Integer>();
		//there is no conditions to continue the game
		if (players.size() < 3)
			Player.getDispatcher().sendMessage("ERROR", "error", -1);
	}


	public static void addNewPlayer(PlayerInfo playerInfo) {
		players.add(playerInfo);
	}

	public static void playerReady() {
		players_ready++;

		// check if all players are ready
		if (players.size() == players_ready && players.size() > 2)
			start();

	}


	public void addScore(String player, int score) {
		
		scores.add(score);
		players_scores.add(player);
		
		if(scores.size() == players.size()) {
			
			int max= Collections.max(scores);
			int winner_id= scores.indexOf(max);
			
			String winner_name=players_scores.get(winner_id);
			System.out.println("The winner is "+winner_name + " with " +max+ " points");
			
			GameUI.printResults(scores,players_scores);
			Utils.sleep(100);
			Player.getDispatcher().sendMessage("WINNER",winner_name,-1);
		}
		
		
			
	}
	
	public void addPlayerAnswered(int player_id) {
		players_that_answered.add(player_id);
	}

}
