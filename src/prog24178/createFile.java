package prog24178;

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * Use this video as reference for setting up these methods:
 * https://www.youtube.com/watch?v=Bws9aQuAcdg
 * 
 * @author Graham
 */
public class createFile {
    
    private Formatter x;
    
    /*Creates a new file*/
    public void openFile(){
        try {
            
            x = new Formatter("movies.txt");
        
        } catch (Exception e) {
            System.out.println("You have an error: Can't open file");
        }
    }
    
    /*Uses the file established from the openFile method*/
    public void addRecords(Movie movie){
        x.format("%s", movie);
    }
    
    /*Closes the file, for a type of coding housekeeping*/
    public void closeFile(){
        x.close();
    }
    
}
