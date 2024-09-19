import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Dlewor {

    // constants to allow colored text and backgrounds
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        // print welcome message
        System.out.println("Welcome to Dlewor(TM)");

        // get file name from command line argument
        File f = new File(args[0]);
        ArrayList<String> words = new ArrayList<String>();

        // print error if there is more than one command line argument
        if(args.length != 1){
            System.out.println("An error occurred.");
            System.exit(1);
        }

        // throw exception when file cannot open;
        // select words with length of 5 and put them in arraylist
        try{
            Scanner file = new Scanner(f);
            while(file.hasNextLine()){
                String word = file.nextLine();
                if(word.length() == 5){
                    words.add(word);
                }
            }
            file.close();
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            System.exit(2);
        }

        // generate random index for random word in the arraylist
        double number = Math.random() * words.size();
        int randomNumber = (int) number;
        String targetWord = words.get(randomNumber);

        Scanner scnr = new Scanner(System.in);

        // for loop used to ask client for word
        // with method call inside
        for(int i = 1; i <= 6; i++){
            System.out.print("Enter word (" + i + "): ");
            String attem = scnr.next();
            int a = 0;
            if(isSorted(words)){
                a = binarySearch(words, 0, words.size() - 1, attem);
            }
            else{
                a = linearSearch(words, 0, words.size() - 1, attem);
            }
            int [] finalmatch = matchDlewor(attem, targetWord);
            if(a != -1){
                printDelwor(attem, finalmatch);
                if(foundMatch(finalmatch)){
                    System.out.println("Yess!!");
                    break;
                }

            }
            else{
                System.out.println("Invalid word .");
                i--;
            }
            if(i == 6){
                System.out.println("You have reached the maximum try time. The correct Answer is: " + targetWord);
            }
        }
    }
    // method to see if imported file is in alphabetical order
    public static boolean isSorted(ArrayList<String> word){
        for(int i = 0; i < word.size() - 1; i ++){
            String first = word.get(i);
            String next = word.get(i + 1);
            if(first.compareTo(next) > 0){
                return false;
            }
        }
        return true;
    }
    public static boolean foundMatch(int [] matchAr){
        int j = 0;
        boolean foundmatch = false;
        // see if the entire word match with the target word
        for(int i = 0; i < matchAr.length; i++){
            if(matchAr[i] == 1){
                j++;
            }
        }
        if(j == 5){
            foundmatch = true;
        }
        return foundmatch;
    }

    public static int binarySearch(ArrayList<String> wod, int start, int end, String target){
       // binary search by spitting the search in half
        if (end >= start)
        {
            int mid = (start + end) / 2;

            if (wod.get(mid).equals(target)) {
                return mid;
            }

            else if (wod.get(mid).compareTo(target) > 0) {
                return binarySearch(wod, start, mid - 1, target);
            }
            else{
                return binarySearch(wod, mid + 1, end, target);
            }
        }
        return -1;
    }

    public static int linearSearch(ArrayList<String> wod, int start, int end, String target){
        // search the start index and end index in wod and see if match with target
        if (start > end) {
            return -1;
        }
        if(wod.get(end).equals(target)){
            return end;
        }
        else if (wod.get(start).equals(target)) {
            return start;
        }
        // recursion call
        else{
            return linearSearch(wod,start + 1,end - 1, target);
        }
    }
    public static int [] matchDlewor(String attemp, String target){
        int [] match = new int[5];
        int i;
        // see the match type of each character
        for(i = 0; i < 5; i++) {
            if (attemp.charAt(i) == target.charAt(i)) {
                match[i] = 1;
            }
            else{
                for (int j = 0; j < 5; j++) {
                    if (attemp.charAt(i) == target.charAt(j)) {
                        match[i] = 0;
                        break;
                    }
                    else{
                        match[i] = -1;
                    }
                }
            }

        }
        return match;
    }
    public static void printDelwor(String attempt, int [] matchArr) {
        // print color result based on match index
        for (int i = 0; i < 5; i++) {
            if (matchArr[i] == 1) {
                System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + attempt.charAt(i));
                System.out.print(ANSI_RESET);
            } else if (matchArr[i] == 0) {
                System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + attempt.charAt(i));
                System.out.print(ANSI_RESET);
            } else if (matchArr[i] == -1) {
                System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + attempt.charAt(i));
                System.out.print(ANSI_RESET);
            }
            System.out.print(ANSI_RESET);
        }
        System.out.println();
    }
}
