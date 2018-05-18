package ui;

public class ServerUI {


    public static String welcome(){

        System.out.print("--------- CARDS AGAINST SDIS --------\n");
        System.out.print("\n");
        System.out.print("Welcome, please choose your username\n");
        System.out.print("Username: ");

        String username = System.console().readLine();
        return username;
    }

	public static String menu() {
		
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("---------------- MENU ----------------\n");
        System.out.print("1 - Enter Room\n");
        System.out.print("2 - Create Room\n");
        System.out.print("Option: ");
        
        String option = System.console().readLine();
        return option;
	}

	public static void newRoom() {
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("------------ CREATE ROOM ------------\n");
		
	}
}
