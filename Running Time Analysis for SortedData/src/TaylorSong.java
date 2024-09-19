// class to get and store songs from the dataset
public class TaylorSong {
    private String name;
    private String album;
    private String releaseDate;
    private int popularity;
    public TaylorSong(String name, String album, String releaseDate, int popularity){
        // constructor to initialize
        this.name = name;
        this.album = album;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public int getPopularity() {
        // return the number for popularity
        return popularity;
    }

    public String toString(){
        // return the string version of the name, album, releasedate and popularity for each song in the dataset
        return "TaylorSong{" + "name = " + name + ", album = " + album + ", releaseDate = " + releaseDate + ", popularity = " + popularity +"}";
    }
}
