/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Graham
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lblTrashcan, lblErrorDisplay, lblRecord, lblMovies;

    @FXML
    private ComboBox cmbGenre;

    @FXML
    private Button btnClear, btnView, btnEdit, btnDelete, btnSubmit, btnSearch,
            first, previous, next, last, btnMovies;

    @FXML
    private TextField txtID, txtTitle, txtYear, txtRuntime, txtPrice;

    ArrayList<Movie> movieList = new ArrayList();
    int navID = 0; //Sets the default position variable to be used for nav

    /**
     * Initialize Method
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Whenever there is a change for the ID value, notify the user that
        // only integers are accepted if the current input is not valid.
        txtID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtID.setText(newValue.replaceAll("[^\\d]", ""));
                    lblErrorDisplay.setText("This field only accepts integers");
                } else {
                    lblErrorDisplay.setText("");
                }
            }
        });
        // Whenever there is a change for the year value, notify the user that
        // only integers are accepted if the current input is not valid.
        txtYear.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtYear.setText(newValue.replaceAll("[^\\d]", ""));
                    lblErrorDisplay.setText("This field only excepts integers");
                } else {
                    lblErrorDisplay.setText("");
                }
            }
        });
        // Whenever there is a change for the runtime, notify the user that
        // only integers are accepted if the current input is not valid.
        txtRuntime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtRuntime.setText(newValue.replaceAll("[^\\d]", ""));
                    lblErrorDisplay.setText("This field only excepts integers");
                } else {
                    lblErrorDisplay.setText("");
                }
            }
        });
        // Whenever there is a change for the price value, notify the user that
        // only decimal numbers are accepted if the curren input is not valid.
        
        
        txtPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                // https://stackoverflow.com/questions/
                // 24974816/regex-for-numerics-and-decimals-in-java
                if (!newValue.matches("^\\d*\\.\\d+|\\d+\\.\\d*$")) {
                    txtPrice.setText(newValue.replaceAll("[^\\d.]", ""));
                    lblErrorDisplay.setText("This field only accepts valid "
                            + "decimal numbers.");
                } else {
                    lblErrorDisplay.setText("");
                }

            }

        });
        
        // Add button disabled until ID field has values.
        btnSubmit.disableProperty().bind(Bindings.isEmpty(txtID.textProperty())
                .or(Bindings.isEmpty(txtTitle.textProperty()))
                .or(Bindings.isEmpty(txtYear.textProperty()))
                .or(Bindings.isEmpty(txtRuntime.textProperty()))
                .or(Bindings.isEmpty(txtPrice.textProperty())));

        // Search Button disabled until ID field has values.
        btnSearch.disableProperty().bind(Bindings.isEmpty(
                txtID.textProperty()));

        //Delete Button disbaled until ID field has values.
        btnDelete.disableProperty().bind(Bindings.isEmpty(
                txtID.textProperty()));

        //Edit button disabled unitl ID field has input
        btnEdit.disableProperty().bind(Bindings.isEmpty(txtID.textProperty()));

        //Initialize Combo Box Values:
        cmbGenre.getItems().addAll("Select Genre...", "Action", "Adventure", "Documentary",
                "Thriller");
        cmbGenre.getSelectionModel().select(0);

        //Calls the update movieList to populate the ArrayList
        updateMovieList();

    }
    /*
    * Method populates the MovieList with movie objects created from the file
     */
    public void updateMovieList() {
        // Populate the arraylist movieList with existing content from the file.
        // https://stackoverflow.com/questions/34208962/read-a-text-file-line-by-line-into-strings

        //Create file object to check if the file exists or not
        File f = new File("movies.txt");
        ArrayList<String> list = new ArrayList();
        movieList.clear(); //Clears the list so there aren't any appending issues

        //Checks to see if the file exists
        if (f.exists() && !f.isDirectory()) {
            // If the file does exist, then proceed to read the file.
            // Use its contents to populate the arraylist movieList.

            Scanner input;
            try {
                input = new Scanner(f);
                while (input.hasNextLine()) {
                    list.add(input.nextLine());
                }
                // Populate he arraylist movieList by iterating through each 
                // line.
                for (int i = 0; i < list.size(); i++) {
                    String[] movie = list.get(i).split(",");

                    // The list movie will have exactly 6 elements.
                    int id = Integer.parseInt(movie[0]);
                    int year = Integer.parseInt(movie[1]);
                    String title = movie[2];
                    int runtime = Integer.parseInt(movie[3]);
                    double ticketPrice = Double.parseDouble(movie[4]);
                    String genre = movie[5];

                    Movie newMovie = new Movie(id, year, title, runtime,
                            ticketPrice, genre);
                    movieList.add(newMovie);

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Prints console message to confirm the Appending of the record
            System.out.println("Appended to file");

        } else {
            /*If the file does not exist then the file must be created using the
            createFile class*/
            createFile file = new createFile();
            file.openFile();
            file.closeFile();

            //Prints console message to confirm the Creation of the record
            System.out.println("Created file");
        }
    }
 //Clear Button clears all the textfields and output
    @FXML
    public void onClear(ActionEvent Event) {
        txtID.setText("");
        txtTitle.setText("");
        txtYear.setText("");
        txtRuntime.setText("");
        txtPrice.setText("");
        cmbGenre.getSelectionModel().select(0);
        lblErrorDisplay.setText("");
    }
    
    //Vieiw movie button shows all the movies in Movie list in output on UI
    @FXML
    public void onViewMovies(ActionEvent Event){
        //if movie file/list is empty will prompt user
        if(movieList.size()==0){
            lblErrorDisplay.setText("Movie Database is empty");
        }else{
        
        String output = "";
        for (Movie movie : movieList) {
           output = output + "\n" + movie;
        }   
        lblErrorDisplay.setText(output);
    }
    }
  
    /* 
    will output a movie object to the UI when the user inputs ID and clicks
    search
    */
    @FXML
    public void onSearch(ActionEvent Event) {

        // Create a default Movie object.
        Movie movie = new Movie();

        // If the id is empty, notify the user.
        if (txtID.getText() == null || txtID.getText().equals("")) {
            lblErrorDisplay.setText("Please input the movie id.");
        } else {

            // Set the ID in our Movie object.
            movie.setId(Integer.parseInt(txtID.getText()));

            // Searches the movie list for the same movie object.
            int index = searchMovie(movie);

            // If the movie exists, then just display it to the user.
            if (index != -1) {
                txtID.setText("" + movieList.get(index).getId());
                txtTitle.setText(movieList.get(index).getTitle());
                txtYear.setText("" + movieList.get(index).getYear());
                txtRuntime.setText("" + movieList.get(index).getRuntime());
                txtPrice.setText("" + movieList.get(index).getTicketPrice());
            } // Otherwise, notify the user the ID does not exist.
            else {
                lblErrorDisplay.setText("Movie ID does not exist in the"
                        + "database.");
            }
        }

    }
/*
    On click of Add button the movie the user entered will be added to the file
    */
    @FXML
    public void onSubmit(ActionEvent event) throws IOException {
        //Creates a movie object using the createMovie() method
        Movie movieVar = createMovie();
        if (doesIdExist(movieVar)) {
            lblErrorDisplay.setText("ID already Exists");
        } else {
            //Adds the movie list item to the list
            movieList.add(movieVar);

            //Adds the movie to the file
            addEntry(movieVar);

            //Updates the movieList array
            updateMovieList();
            //lblErrorDisplay.setText("Movie Added");

            sortAndWrite();
        }
    }
    /*
    User has to input an exisiting MOvie ID and then can input data into the 
    they text box field they wish to change
    */
    @FXML
    public void onEdit(ActionEvent event) throws IOException {
        System.out.println("onEdit --------------------------------");

        //Gets the index from the users ID text box
        int index = Integer.parseInt(txtID.getText());
        System.out.println("Index: " + index);

        //Updates the movie list to ensure no files are lost
        updateMovieList();

        //Holds the position of the record the user wants to remove
        int recordPos = 0;

        //Finds the position of the stuff we want changed
        for (int i = 0; i <= (movieList.size() - 1); i++) {
            System.out.println("movieList: " + movieList.get(i).getId());
            System.out.println("i: " + i);
            if (movieList.get(i).getId() == index) {
                recordPos = i;
                break;
            }
        }

        System.out.println("Record Position:" + recordPos);

        //prints the position of the record
        //System.out.println("recordPos: " + recordPos);
        //Deletes the old file
        createFile file = new createFile();
        file.openFile();
        file.closeFile();

        //Creates variables to hold values to create a movie object
        String title;
        int year;
        int runtime;
        double price;
        String genre;

        //Checks to see which of the movie values need to be changed
        if (txtID.getText() != null || Integer.parseInt(txtID.getText()) > 0) {
            System.out.println("Valid id");

            //Checks to see if there is input in the title field
            if (!txtTitle.getText().equals("")) {
                System.out.println("Valid Title");
                title = txtTitle.getText();
            } else {
                System.out.println("Invalid Title");
                title = movieList.get(recordPos).getTitle();
            }

            //Checks to see if there is input in the Year field
            if (!((String) txtYear.getText()).equals("")) {
                System.out.println("Valid Year");
                year = Integer.parseInt(txtYear.getText());
            } else {
                year = movieList.get(recordPos).getYear();
            }

            //Checks to see if there is input in the Runtime field
            if (!((String) txtRuntime.getText()).equals("")) {
                System.out.println("Valid Runtime");
                runtime = Integer.parseInt(txtRuntime.getText());
            } else {
                runtime = movieList.get(recordPos).getRuntime();
            }

            //Checks to see if there is input in the Price field
            if (!((String) txtPrice.getText()).equals("")) {
                System.out.println("Valid Price");
                price = Double.parseDouble(txtPrice.getText());
            } else {
                price = movieList.get(recordPos).getTicketPrice();
            }

            //Checks to see if there is input in the Genre field
            if (!((String) cmbGenre.getValue()).equals("Select Genre...")) {
                System.out.println("Valid Genre");
                genre = (String) cmbGenre.getValue();
            } else {
                genre = movieList.get(recordPos).getGenre();
            }

            //Creates a new movie object
            Movie movie = new Movie(index, year, title, runtime, price, genre);

            System.out.println("Movie Created: " + movie);
            System.out.println("Changed Movie: " + movieList.get(recordPos));

            //Creates a temporary List
            ArrayList<Movie> tempList = new ArrayList();

            //Prints the movie list size
            System.out.println("MovieList Size: " + movieList.size());

            //Fills the tempList array
            for (int i = 0; i <= (movieList.size() - 1); i++) {
                if (i != recordPos) {
                    Movie newMovie = movieList.get(i);
                    tempList.add(newMovie);
                } else {
                    tempList.add(movie);
                }
                System.out.println("Added: " + tempList.get(i));
            }

            Collections.sort(tempList);

            for (int i = 0; i <= (movieList.size() - 1); i++) {
                System.out.println("Sorted: " + tempList.get(i));
            }

            //Creates a new movies.txt file with the new list
            for (int i = 0; i <= (movieList.size() - 1); i++) {
                addEntry(tempList.get(i));
            }

            //Completes the edit
            System.out.println("File update complete!");
        } else {
            lblErrorDisplay.setText("Error: Must input a valid ID");
        }
        updateMovieList();

        System.out.println("End onEdit ----------------");
        lblErrorDisplay.setText("Movie entry Edited");

    }

    public void addEntry(Movie movieVar) throws IOException {

        //Create file object to check if the file exists or not
        File f = new File("movies.txt");
        //System.out.println(f.getAbsoluteFile());

        //Checks to see if the file exists
        if (f.exists() && !f.isDirectory()) {
            //If the file does exist this method appends to the end of the file
            //System.out.println("NEW FILE!!!!");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%s%n", movieVar));
            bw.close();
            //Prints console message to confirm the Appending of the record
            System.out.println("Appended to file");

        } else {
            /*If the file does not exist then the file must be created using the
            createFile class*/
            createFile file = new createFile();
            file.openFile();
            file.addRecords(movieVar);
            file.closeFile();

            //Prints console message to confirm the Creation of the record
        
        }
    }

    // Deletes movie from file
    @FXML
    public void onDelete(ActionEvent event) throws IOException {

        // Creates a movie object from user text field input
        Movie movie = new Movie();
        if (txtID.getText() == null || txtID.getText().equals("")) {
            lblErrorDisplay.setText("Please enter the  movie ID.");
        } else {
            movie.setId(Integer.parseInt(txtID.getText()));

            // Searches the movie list for the same movie object.
            int index = searchMovie(movie);
            if (index != -1) {
                System.out.println("Index: " + index);
                movieList.remove(index);

                // Now, clear the existing file.
                //https://stackoverflow.com/questions/6994518/how-to-delete-the-content-of-text-file-without-deleting-itself
                File file = new File("movies.txt");
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();

                // Then populate it with the updated movieList which 
                // no longer has the movie we want to delete.
                for (int i = 0; i < movieList.size(); i++) {
                    System.out.println(movieList.get(i));

                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(String.format("%s%n", movieList.get(i)));
                    bw.close();
                    //Prints console message to confirm the Appending of the record
                    System.out.println("Appended to file");

                }

                //Completes the edit
                System.out.println("File deletion complete!");
                lblErrorDisplay.setText("Movie entry Deleted");
            } else {
                lblErrorDisplay.setText("Error: movie does not exist.");
            }
        }

    }

    //searches for movie object in movie list
    public int searchMovie(Movie movie) {
        int index = -1;
        for (int i = 0; i < movieList.size(); i++) {
            //System.out.println(movieList.get(i));
            if (movie.getId() == movieList.get(i).getId()) {
                index = i;
            }
        }
        return index;
    }
//Checks if MovieObject has the same Id 
    public Boolean doesIdExist(Movie movie) {
        return searchMovie(movie) != -1;
    }
// creates a movie object from the user input in the textfields
    public Movie createMovie() {

        //Creates and sets variables for each of the input fields
        Movie newMovie = new Movie();
        try {
            int id = Integer.parseInt(txtID.getText());
            int year = Integer.parseInt(txtYear.getText());
            String title = String.format(txtTitle.getText());
            int runtime = Integer.parseInt(txtRuntime.getText());
            double ticketPrice = Double.parseDouble(txtPrice.getText());
            String genre = (String) cmbGenre.getValue();

            //NOTE: THE STATMENTS ABOVE MUST BE ERROR CHECKED SO THAT THE USER CANNOT INPUT INCORRECT VALUES
            newMovie = new Movie(id, year, title, runtime, ticketPrice, genre);
        } catch (NumberFormatException ex) {
            lblErrorDisplay.setText("Runtime must be a number");
        }

        return newMovie;
    }

    /**
     * Grab Record takes in the index of the movie object we want printed out to
     * the user
     */
    private String grabRecord(int index) {
        //Sets all the variables to be equal to the 
        String id = Integer.toString(movieList.get(index).getId());
        String title = movieList.get(index).getTitle();
        String year = Integer.toString(movieList.get(index).getYear());
        String runtime = Integer.toString(movieList.get(index).getRuntime());
        String price = Double.toString(movieList.get(index).getTicketPrice());
        String genre = movieList.get(index).getGenre();

        return String.format("%s, %s, %s, %s, %s, %s", id, title, year, runtime,
                price, genre);
    }
/*
 On click of the first navigation button, the ui will display the first movie 
    object in the file
 */
    @FXML
    private void onFirst(ActionEvent event) {
        /* Displays the first patient record in the existing textboxes.
           Calls grabFirstRecord() method and prints it to the label*/
        if (movieList.isEmpty()) {
            lblErrorDisplay.setText("The database is empty.");
        } else {
            lblRecord.setText(String.format("%s", grabRecord(0)));

            //Sets the navID for navigation positioning
            navID = 0;
        }
    }
/*
 On click of the last navigation button, the ui will display the last movie 
    object in the file
 */
    @FXML
    private void onLast(ActionEvent event) {
        // Displays the last patient record in the existing textboxes.
        if (movieList.isEmpty()) {
            lblErrorDisplay.setText("The database is empty.");
        } else {
            lblRecord.setText(String.format("%s", grabRecord(movieList.size()
                    - 1)));

            navID = movieList.size() - 1; //Sets the navID for positioning
        }

    }
/*
    On click of the pervios navigation button, the ui will display the previous 
    movie object in the file
    */
    @FXML
    private void onPrevious(ActionEvent event) {
        /*Checks to see if the navID variable has been change or is going to
        go out of range of the array*/
        if (movieList.isEmpty()) {
            lblErrorDisplay.setText("The database is empty.");
        } else {
            if (navID == 0 || (navID - 1) < 0) {
                //if out of range/not moved then go to first record
                lblRecord.setText(grabRecord(0));
            } else {
                //if not go to previous record
                lblRecord.setText(grabRecord(navID - 1));
                navID = navID - 1; //Repositions navID
            }
            System.out.println("Prev nav: " + navID);
        }
    }
/*
 On click of the next navigation button, the ui will display the fnext movie 
    object in the file
 */
    @FXML
    private void onNext(ActionEvent event) {
        System.out.println("MovieList Size: " + movieList.size());
        System.out.println("Nav ID B4: " + navID);

        if (movieList.isEmpty()) {
            lblErrorDisplay.setText("The database is empty.");
        } else {
            if (navID < 0 || navID >= movieList.size()
                    - 1) {
                lblRecord.setText(grabRecord(movieList.size()
                        - 1));
                navID = movieList.size() - 1;
            } else {
                lblRecord.setText(grabRecord(navID + 1));
                navID = navID + 1; //Repositions navID
            }

            System.out.println("Nav ID AT: " + navID);
        }
    }
/*
    sorts the movie IDs in the movielist and writes the sorted list to the file
    */
    public void sortAndWrite() throws FileNotFoundException, IOException {

        // Sort the movieList.
        Collections.sort(movieList);

        // Clear and write to the file.
        // Now, clear the existing file.
        File file = new File("movies.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        // Then populate it with the updated movieList which 
        // no longer has the movie we want to delete.
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(movieList.get(i));
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%s%n", movieList.get(i)));
            bw.close();
            //Prints console message to confirm the Appending of the record
            System.out.println("Sorted and Written to File.");

        }
    }
    
  
}
