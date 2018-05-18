package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GameLogic {
	ArrayList<Black_Card> black_cards_database;
	ArrayList<White_Card> white_cards_database;
	ArrayList<Black_Card> used_black_cards;
	ArrayList<White_Card> used_white_cards;
	
	
	GameLogic(String black_cards, String white_cards){
		black_cards_database=new ArrayList<Black_Card>();
		white_cards_database=new ArrayList<White_Card>();
		used_black_cards=new ArrayList<Black_Card>();
		used_white_cards=new ArrayList<White_Card>();
		
		
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
		       Black_Card bc=new Black_Card(line);
		       black_cards_database.add(bc);
		    }
		}
		try (BufferedReader br = new BufferedReader(new FileReader(white_cards))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       White_Card wc=new White_Card(line);
		       white_cards_database.add(wc);
		    }
		}	
		
		
	}
	
	public Black_Card draw_blackCard() {
		Black_Card bc;
		do {
			Random rand = new Random();

			int  n = rand.nextInt(black_cards_database.size()) + 1;
			 bc=black_cards_database.get(n);
		}
		while(used_black_cards.contains(bc));
		used_black_cards.add(bc);
		return bc;
	}
	
	public ArrayList<White_Card> makePlay(int number_players){
		ArrayList<White_Card> white_cards=new ArrayList<White_Card>();
		
		for(int i=0; i<number_players; i++) {
			White_Card wc;
			do {
				Random rand = new Random();

				int  n = rand.nextInt(black_cards_database.size()) + 1;
				 wc=white_cards_database.get(n);
			}
			while(used_white_cards.contains(wc));
			used_white_cards.add(wc);
			white_cards.add(wc);
		}
		return white_cards;
	}
	
	
	private void print_database() {
		for(int i=0; i< black_cards_database.size();i++) {
			System.out.println(black_cards_database.get(i).getText());
		}
	}
	
	public static void main(String [] args) throws Exception {
	GameLogic gl=new GameLogic("black_cards.txt","white_cards.txt");
	ArrayList<White_Card> bc=gl.makePlay(30);
	
for(int i=0; i< bc.size();i++) {
	
	System.out.println(bc.get(i).getText());
	}
	}

}
