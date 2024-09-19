public class Comparator {
    public int compare(TaylorSong song1, TaylorSong song2){
        // comparator to compare two taylor songs' popularity
        return Integer.compare(song1.getPopularity(), song2.getPopularity());
    }
}
