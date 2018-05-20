package utils;

import java.util.ArrayList;


public class Utils {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;
    
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

}
