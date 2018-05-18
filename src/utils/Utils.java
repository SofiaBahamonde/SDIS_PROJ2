package utils;

import java.util.ArrayList;


public class Utils {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;
    
    
    public static <T> void printArray(ArrayList<T> array) {

    	for(T element: array) {
        	System.out.println(element.toString());
        }
    }

}
