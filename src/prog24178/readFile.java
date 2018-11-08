package prog24178;


import java.io.*;
import java.util.*;

/**
 * Youtube reference: https://www.youtube.com/watch?v=3RNYUKxAgmw
 * 
 * @author Graham
 */
public class readFile {
    
    private Scanner x;
    
    public void openFile(){
        try {
            x = new Scanner(new File("movies.txt"));
        } catch (Exception e) {
            System.out.println("Couldn't find file");
        }
    }
    
    public void readFile() {
        // hasNext(): loop will continue until the file has no more content
        while(x.hasNext()) {
            //Goes through each line of the file and grabs each of the variables
            int id = Integer.parseInt(x.next());                //For ID
            int year = Integer.parseInt(x.next());              //For Year
            String title = x.next();                            //For Title
            int runtime = Integer.parseInt(x.next());           //For Runtime
            double ticketPrice = Double.parseDouble(x.next());  //For T. Price
            String genre = x.next();                            //For Genre
            
            //Uses values to create a movie object
            Movie readMovie = new Movie(id, year, title, runtime, ticketPrice, genre);
            
            /*THIS METHOD SHOULD USE THE MOVIE ARRAY LIST TO CREATE A LIST
            MOVIE OBJECTS THAT CAN BE PASSED BACK TO THE USER FOR STORAGE IN A 
            MOVIELIST VARIABLE, TO DO THIS WE MUST:
                1) CREATE THE MOVIE ARRAY LIST OBJECT OUTSIDE THE WHILE LOOP
                2) ON EACH PASS OF THE LOOP ADD THE MOVIE OBJECT TO THE LIST 
                3) ONCE LOOP STOPS RETURN THE VARIABLE AS MOVIE ARRAY LIST
            THIS METHOD WILL HAVE TO BECOME "public MovieDatabase readFile()
            */
            
            /*Prints movie object out to the terminal (Just to show that the 
            method works)*/
            System.out.println(String.format("%s", readMovie));
        }
    }
    
    public void closeFile(){
        x.close();
    }
}
