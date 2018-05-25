package game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import player.Player;
import player.PlayerInfo;
import ui.GameUI;
import utils.Utils;

public class GameLogic {
	private static ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	private static int players_ready = 1;

	private static int round = 0;
	private static int jury = 0;
	private static boolean round_end = false;

	public static void start() {

		GameUI.startGame();
		Player.getDispatcher().sendMessage("START", "start", -1);

		sleep(100);

		round();

		//}
	}

	public static void round() {
		if (round < Utils.MAX_ROUNDS) {
		// draw black cards
		String black_card = Player.getBlackCard();
		Player.getDispatcher().sendMessage("BLACKCARD", black_card, -1);

		sleep(100);

		// choose jury
		Player.getDispatcher().sendMessage("NEWJUDGE", "jury", players.get(jury).getPlayerID());
		if (jury != players.size()-1)
			jury++;
		else
			jury = 0;

		// start round
		sleep(100);
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

	public static void sleep(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void end_round() {
		round_end = true;
	}

}
