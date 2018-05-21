package ui;

import java.util.ArrayList;


public class GameUI {
	
	public static String makePlay(String card,ArrayList<String> cards) {
		
		System.out.print("\n");
		System.out.print("\n");
		System.out.println("Sentence: " + card);
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("-------- Choose One to complete the sentence --------\n");
		showWhiteCards(cards);
        System.out.print("Option: ");
        
        String option = System.console().readLine();
        System.out.print("\n");
        int o = Integer.parseInt(option);
        return cards.get(o);
        
	}

	public static void showWhiteCards(ArrayList<String> cards) {
		for(int i=0;i<cards.size();i++) {
			 System.out.println(i+1 + " - " + cards.get(i));
			}

	}
	

}
