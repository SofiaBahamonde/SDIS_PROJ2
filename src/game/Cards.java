package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import utils.Utils;

public class Cards {
	ArrayList<String> black_cards_database =new ArrayList<String>();
	ArrayList<String> white_cards_database =new ArrayList<String>();
	ArrayList<String> used_black_cards = new ArrayList<String>();
	ArrayList<String> used_white_cards = new ArrayList<String>();
	
	public Cards(){
		try {
			load_databases(Utils.BLACK_CARDS,Utils.WHITE_CARDS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load_databases(String black_cards, String white_cards) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(black_cards))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       black_cards_database.add(line);
		    }
		}
		try (BufferedReader br = new BufferedReader(new FileReader(white_cards))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       white_cards_database.add(line);
		    }
		}	
		
		
	}
	
	public String drawBlackCard() {
		String bc;
		do {
			Random rand = new Random();

			int  n = rand.nextInt(black_cards_database.size());
			 bc=black_cards_database.get(n);
		}
		while(used_black_cards.contains(bc));
		used_black_cards.add(bc);
		return bc;
	}
	
	public String drawWhiteCard(){
		String white_card;
		
		do {
			Random rand = new Random();

			int  n = rand.nextInt(black_cards_database.size());
			white_card=white_cards_database.get(n);
		}
		while(used_white_cards.contains(white_card));
		used_white_cards.add(white_card);	
		return white_card;
		
	}
	
	
}
