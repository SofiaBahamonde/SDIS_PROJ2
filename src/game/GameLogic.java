package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;




public class GameLogic {
	ArrayList<String> black_cards_database;
	ArrayList<String> white_cards_database;
	ArrayList<String> used_black_cards;
	ArrayList<String> used_white_cards;
	
	
	public GameLogic(String black_cards, String white_cards){
		black_cards_database=new ArrayList<String>();
		white_cards_database=new ArrayList<String>();
		used_black_cards=new ArrayList<String>();
		used_white_cards=new ArrayList<String>();
		
		
		try {
			load_databases(black_cards,white_cards);
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

			int  n = rand.nextInt(black_cards_database.size()) + 1;
			 bc=black_cards_database.get(n);
		}
		while(used_black_cards.contains(bc));
		used_black_cards.add(bc);
		return bc;
	}
	
	public String getWhiteCards(int card_number){
		String white_cards= "";
		
		for(int i=0; i<card_number; i++) {
			String wc;
			do {
				Random rand = new Random();

				int  n = rand.nextInt(black_cards_database.size());
				 wc=white_cards_database.get(n);
			}
			while(used_white_cards.contains(wc));
			used_white_cards.add(wc);
			white_cards += wc + "_";
		}
		
		return white_cards;
	}
	
	

}
