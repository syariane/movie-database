/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178;

public class Movie implements Comparable<Movie>{
    
    private int id = 1;
    private int year = 2018;
    private String title = "Title";
    private int runtime = 60;
    private double ticketPrice = 8.99;
    private String genre = "Action";
    
    /**
     * Creates a default Movie object.
     */
    public Movie() {
        
    }
    
    /**
     * Creates a Movie object with programmer-specified id, year, title, 
     * runtime, ticketPrice and genre.
     * 
     * @param id
     * @param year
     * @param title
     * @param runtime
     * @param ticketPrice
     * @param genre 
     */
    public Movie(int id, int year, String title, int runtime, 
            double ticketPrice, String genre) {
        setId(id);
        setTitle(title);
        setYear(year);
        setRuntime(runtime);
        setTicketPrice(ticketPrice);
        setGenre(genre);
    }
    
    /**
     * Gets this Movie object's id.
     * 
     * @return this Movie object's id.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Places a valid value in this Movie's id which must be greater than
     * zero otherwise an IAE is thrown.
     * 
     * @param id programmer-specified id
     * @throws IllegalArgumentException 
     */
    public void setId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Error: id must be greater than"
                    + "zero.");
        }
        else {
            this.id = id;
        }
    }
    
    /**
     * Gets this Movie object's year.
     * 
     * @return this Movie object's year.
     */
    public int getYear() {
        return this.year;
    }
    
    /**
     * Places a valid value in this Movie's year which must be greater than
     * zero otherwise an IAE is thrown.
     * 
     * @param year programmer-specified year
     * @throws IllegalArgumentException 
     */
    public void setYear(int year) throws IllegalArgumentException {
        if (year <= 0) {
            throw new IllegalArgumentException("Error: year must be greater than"
                    + "zero.");
        }
        else {
            this.year = year;
        }
    }
    
    /**
     * Gets this Movie object's title
     * 
     * @return this Movie object's title.
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Places a valid value in this Movie's title which must be not be
     * empty otherwise an IAE is thrown.
     * 
     * @param title programmer-specified title
     * @throws IllegalArgumentException 
     */
    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("Error: title must not be "
                    + "empty.");
        }
        else {
            this.title =  title;
        }
    }
    
    /**
     * Gets this Movie object's runtime.
     * 
     * @return this Movie object's runtime.
     */
    public int getRuntime() {
        return this.runtime;
    }
    
    /**
     * Places a valid value in this Movie's runtime which must be greater
     * than zero otherwise an IAE is thrown.
     * 
     * @param runtime programmer-specified runtime
     * @throws IllegalArgumentException 
     */
    public void setRuntime(int runtime) throws IllegalArgumentException {
       
        if (runtime <= 0) {
            throw new IllegalArgumentException("Error: runtime must be greater"
                    + "than zero.");
        }
        else {
            this.runtime = runtime;
        }
    }
    
    /**
     * Gets this Movie object's ticketPrice.
     * 
     * @return this Movie object's ticketPrice
     */
    public double getTicketPrice() {
        return this.ticketPrice;
    }
    
    /**
     * Places a valid value in this Movie's ticketPrice which must be greater
     * than zero otherwise an IAE is thrown.
     * 
     * @param ticketPrice programmer-specified ticketPrice
     * @throws IllegalArgumentException 
     */
    public void setTicketPrice(double ticketPrice) throws
            IllegalArgumentException {
        if (ticketPrice <= 0) {
            throw new IllegalArgumentException("Error: runtime must be greater"
                    + "than zero.");
        }
        else {
            this.ticketPrice = ticketPrice;
        }
    }
    
    /**
     * Gets this Movie object's genre.
     * 
     * @return this Movie object's genre.
     */
    public String getGenre() {
        return this.genre;
    }
    
    /**
     * Places a valid value in this Movie's genre which must not be
     * an empty string, otherwise an IEA is thrown.
     * 
     * @param genre programmer-specified genre
     * @throws IllegalArgumentException 
     */
    public void setGenre(String genre) throws IllegalArgumentException {
        if (genre == null || genre.equals("")) {
            throw new IllegalArgumentException("Error: genre must not be "
                    + "empty.");
        }
        else {
            this.genre =  genre;
        }
    }
    
    @Override
    public int compareTo(Movie movie) {
        int compareID = movie.getId();
        
        return this.id - compareID;
    }
    
    /**
     * Gets a String representation of this Movie object.
     * 
     * @return String representation of this Movie object.
     */
    @Override
    public String toString(){
        return String.format("%d,%d,%s,%d,%.2f,%s", id, year, title, runtime, ticketPrice, genre);
    }
}
