package game;

import java.util.ArrayList;

import player.Player;
import player.PlayerInfo;
import ui.GameUI;
import utils.Utils;

public class GameLogic {
	private static ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	private static int players_ready = 1;

	private static int round = 0;
	private static int jury = 0;
	
	public static void start() {

		GameUI.startGame();
		Player.getDispatcher().sendMessage("START", "start", -1);

		Utils.sleep(100);

		round();

	}

	public static void round() {
		if (round < Utils.MAX_ROUNDS) {
		// draw black cards
		String black_card = Player.getBlackCard();
		Player.getDispatcher().sendMessage("BLACKCARD", black_card, -1);

		Utils.sleep(100);

		// choose jury
		Player.getDispatcher().sendMessage("NEWJUDGE", "jury", players.get(jury).getPlayerID());
		if (jury != players.size()-1)
			jury++;
		else
			jury = 0;

		// start round
		Utils.sleep(100);
		Player.getDispatcher().sendMessage("START_ROUND", "round", -1);
		round++;
	}
		else {
			Player.getDispatcher().sendMessage("GAME_END", "round", -1);
		}
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

	public static int getNumberPlayers() {
		return players.size();
	}

	public void addScore(int score, int player_id) {
		// TODO Auto-generated method stub
		
	}

}
