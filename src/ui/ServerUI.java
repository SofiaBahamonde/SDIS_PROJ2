package ui;

import java.io.PrintStream;

public class ServerUI {


    public static String welcome(){

        System.out.print("--------- CARDS AGAINST SDIS --------- \n");
        System.out.print("\n");
        System.out.print("Welcome, please choose your username\n");
        System.out.print("Username: ");

        String username = System.console().readLine();
        return username;
    }
}
