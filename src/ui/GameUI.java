package ui;

import java.util.ArrayList;


public class GameUI {
	
	public static String makePlay(String card,ArrayList<String> cards,int points) {
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("Your Score is "+points);
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
        return cards.get(o-1);
        
	}
	
	
	public static String printAnswers(ArrayList<String> cards) {
		
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("-------- Choose the winner --------\n");
		showWhiteCards(cards);
        System.out.print("Option: ");
        
        String option = System.console().readLine();
        System.out.print("\n");
        int o = Integer.parseInt(option);
        return cards.get(o-1);
        
	}

	public static void showWhiteCards(ArrayList<String> cards) {
		for(int i=0;i<cards.size();i++) {
			 System.out.println(i+1 + " - " + cards.get(i));
			}

	}


	public static void startGame() {
		System.out.print("ALL PPLAYERS ARE READY!\n");
		System.out.print("------ PRESS ENTER TO START ------\n");
		
		System.console().readLine();
		
	}


	public static void printResults() {
		System.out.println("GAME ENDED");
		System.out.println("------ SCORES ------");
	}
	
	

}
