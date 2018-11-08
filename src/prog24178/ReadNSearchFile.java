package prog24178;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * For deleting records references: https://www.youtube.com/watch?v=HFC-KspB9l4
 * 
 * @author Graham
 */
public class ReadNSearchFile {
    
    private static Scanner x;
    
    /* From example video
    
    public static void main(String[] args) {
        String filepath = "tutorial.txt";
        String removeTerm = "4444"; //This would be the String you'd remove
        
        removeRecord(filepath,removeTerm);
    }*/
    
    /* Takes in a string for the name of the file you'd like to edit and a 
     * String that you'd like to look for.
     * It creates a temp file which all the entries you want to keep are moved
     * to then deletes the original file before renaming the new one to the
     * same name as the previous file.
     */
    public static void removeRecord(String filepath, String removeTerm) {
        //Creates the name of a temp.txt file for storing values we want to keep
        String tempFile = "temp.txt";
        
        /*Creates a connection to the file we'd like to edit and the one we 
        create*/
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        
        int id; //Variable to store id 
        int year; //Variable to store year
        String title; //Variable to store title
        int runtime; //Variable to store movie length
        double ticketPrice; //Variable to store ticket price
        String genre; //Variable to store genre
        
        
        try {
            //Sets up the filewriter variables
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            //Creates a new scanner object for the desired file
            x = new Scanner(new File(filepath));
            
            //Sets the delimeter for each record as a next line
            x.useDelimiter("[\n]"); 
            
            //Gets the variables out of the records
            while(x.hasNext()) {
                id = Integer.parseInt(x.next());
                year = Integer.parseInt(x.next());
                title = x.next();
                runtime = Integer.parseInt(x.next());
                ticketPrice = Double.parseDouble(x.next());
                genre = x.next();
                
                //Checks to see if the non wanted field exists before writing
                if(!title.equals(removeTerm)) {
                    pw.println(id + "," + year + "," + title + "," + runtime + "," + ticketPrice + "," + genre);
                }
            }
            
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete(); //deletes the original file
            File dump = new File(filepath); //Creates a new file
            newFile.renameTo(dump); //Renames temp to dump
        } catch (Exception e) {
            System.out.println("Error: File issue");
        }
    }
}
