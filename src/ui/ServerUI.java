package ui;

public class ServerUI {


    public static String welcome(){

    	System.out.print("\n");
    	System.out.print("\n");
        System.out.print("--------- CARDS AGAINST SDIS ---------\n");
        System.out.print("Welcome, please choose your username\n");
        System.out.print("\n");
        System.out.print("Username: ");

        String username = System.console().readLine();
        return username;
    }

	public static String menu() {
		
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("---------------- MENU ----------------\n");
        System.out.print("1 - Show Rooms\n");
        System.out.print("2 - Enter Room\n");
        System.out.print("3 - Create Room\n");
        System.out.print("Option: ");
        
        String option = System.console().readLine();
        return option;
	}

	public static String newRoom() {
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("------------ CREATE ROOM -------------\n");
		System.out.print("Rooms must have a name, the passoword field is optional\n");
		System.out.print("\n");
        System.out.print("Room name: ");
        
        String name = System.console().readLine();
        return name;
		
	}
	
	public static String enterRoom() {
		System.out.print("\n");
		System.out.print("Room Name: ");
		
		 String name = System.console().readLine();
         return name;
			
	}

	public static String password() {
		System.out.print("Password: ");
		
		String password = System.console().readLine();
		return password;
	}

	public static void showRooms() {
		System.out.print("\n---------------- ROOMS ---------------\n");
	}

	public static void showRoom() {
		System.out.print("\n---------------- ROOM ----------------\n");
		
	}

	public static String roomMenu() {
		System.out.print("1 - Start Game\n");
        System.out.print("2 - Leave Room\n");
        System.out.print("Option: ");
        
        String option = System.console().readLine();
        return option;
	}
	

}
