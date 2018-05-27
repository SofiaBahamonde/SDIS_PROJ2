package utils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Utils {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8080;
    
    public static final int PACKET_SIZE=2048;
	public static final String CRLF = "\r"+"\n";
	
	public static String BLACK_CARDS = "../black_cards.txt";
	public static String WHITE_CARDS =  "../white_cards.txt";
	
	public static int MAX_ROUNDS=4;
	
    public static ArrayList<Integer> ports = new ArrayList<Integer>(){ 
    private static final long serialVersionUID = 1L;
	{
        add(4445);
        add(4446);
        add(4447);
    }};
    
    
    public static ArrayList<String> addresses = new ArrayList<String>(){
	private static final long serialVersionUID = 1L;
	{
        add("224.0.0.0");
        add("224.0.0.1");
        add("224.0.0.2");
    }};
    
    
    public static <T> String toStringArray(ArrayList<T> array) {
    	String str ="";
    	
    	
    	for(T element: array) {
        	str += element.toString() + "\n";
        }
    	
    	return str;
    }
    

	public static void sleep(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}
