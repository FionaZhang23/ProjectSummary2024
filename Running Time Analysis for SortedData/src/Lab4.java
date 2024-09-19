import java.io.*;
import java.util.*;
import java.lang.*;

import static java.lang.System.out;

public class Lab4 {
    public static void main(String[] args) {
        // create a songs arraylist in TaylorSong Object
        ArrayList<TaylorSong> songs = new ArrayList<TaylorSong>();
        // open and read the file
        try{
            FileReader f = new FileReader("/Users/fionazhang/Desktop/project-4-FionaZhang23/src/spotify_taylorswift.csv");
            BufferedReader bf = new BufferedReader(f);
            bf.readLine();
            String line;
            while((line = bf.readLine()) != null){
                String[] value = line.split(",");
                TaylorSong song = new TaylorSong(value[1], value[2], value[4], Integer.parseInt(value[6]));
                songs.add(song);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        int size = songs.size();
        // calculate the runtime for sort method
        long startTime = System.currentTimeMillis();
        sort(songs, size);
        long endTime = System.currentTimeMillis();
        System.out.println("Sorting time: " + (endTime - startTime) + " ms");
        // write the sorted data in new txt file
        try(PrintWriter out = new PrintWriter("sortedDataset.txt")){
            for(int i = 0; i < size; i++){
                out.println(songs.get(i).toString());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void sort(ArrayList<TaylorSong> songArray, int n){
        // sort the popularity for small to large
        Comparator comp = new Comparator();
        boolean isSorted;
        do{
            isSorted = true;
            for(int i = 0; i < n - 1; i++){
                if(comp.compare(songArray.get(i), songArray.get(i + 1)) > 0){
                    swap(songArray, i, i+1);
                    isSorted = false;
                }
            }
        } while(!isSorted);
    }
    public static void swap(ArrayList<TaylorSong> array, int a, int b){
        // swap two songs if popularity is larger for the former
        TaylorSong song = array.get(a);
        array.set(a, array.get(b));
        array.set(b, song);
    }
}
