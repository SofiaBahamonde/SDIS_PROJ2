package utils;

import java.util.ArrayList;


public class Utils {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;
    
    
    public static <T> String toStringArray(ArrayList<T> array) {
    	String str ="";
    	
    	
    	for(T element: array) {
        	str += element.toString() + "\n";
        }
    	
    	return str;
    }

}
