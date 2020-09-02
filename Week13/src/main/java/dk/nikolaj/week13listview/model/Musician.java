package dk.nikolaj.week13listview.model;

public class Musician {

    private int image; //They have unique ID's which are ints.
    private String name, genre;

    public Musician(int image, String name, String genre) { //Constructor
        this.image = image;
        this.name = name;
        this.genre = genre;
    }

    //Getters
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
