import java.util.*;
import java.io.*;

public class Lab8 {

    // Method declarations
    public static void quickSort(ArrayList<Salaries> a, int left, int right){
        if (left < right) {
            int partitionIndex = partition(a, left, right);
            quickSort(a, left, partitionIndex - 1);  // Sort the left subarray
            quickSort(a, partitionIndex + 1, right); // Sort the right subarray
        }
    }

    public static int partition (ArrayList<Salaries> a, int left, int right){
        Salaries pivot = a.get(right - 1);

        int i = left - 1; // Index of smaller element

        for (int j = left; j < right; j++) {
            // If current element is less than or equal to pivot
            if (Comparator.compare(a.get(j), pivot) < 0) {
                i++; // increment index of smaller element
                Swap(a, i, j); // Swap current element with the element at index i
            }
        }

        // Swap the pivot element with the element at index i+1
        Swap(a, i + 1, right - 1);
        return i + 1; // Return the partitioning index
    }

    public static void Swap(ArrayList<Salaries> a, int left, int right){
        Salaries first = a.get(left);
        a.set(left, a.get(right));
        a.set(right, first);
    }

    public static int bubbleSort(ArrayList<Salaries> a, int size){
        boolean swapped;
        int totalSwaps = 0;  // This will count the total number of swaps made during the sort

        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (Comparator.compare(a.get(j), a.get(j + 1)) > 0) {
                    // Swap a[j] and a[j + 1]
                    Salaries temp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, temp);
                    swapped = true;
                    totalSwaps++;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped)
                break;
        }

        return totalSwaps;  // Return the total number of swaps performed
    }

    public static int transpositionSort(ArrayList<Salaries> a, int size){
        boolean isSorted = false;
        int totalSwaps = 0;

        while (!isSorted) {
            isSorted = true;
            int swapsInThisPass = 0; // Counter for swaps in this pass

            // Odd indexed elements sort
            for (int i = 1; i <= size - 2; i += 2) {
                if (Comparator.compare(a.get(i), a.get(i + 1)) > 0) {
                    Salaries temp = a.get(i);
                    a.set(i, a.get(i + 1));
                    a.set(i + 1, temp);
                    isSorted = false;
                    swapsInThisPass++;
                }
            }

            // Even indexed elements sort
            for (int i = 0; i <= size - 2; i += 2) {
                if (Comparator.compare(a.get(i), a.get(i + 1)) > 0) {
                    Salaries temp = a.get(i);
                    a.set(i, a.get(i + 1));
                    a.set(i + 1, temp);
                    isSorted = false;
                    swapsInThisPass++;
                }
            }

            // Update total swaps only once per pass through both odd and even indices
            totalSwaps += swapsInThisPass;
        }
        return totalSwaps;
    }

    public static void main(String [] args){
        if (args.length != 3) {
            System.err.println("Error: Exactly three arguments required - dataset path, sorting algorithm, and number of records.");
            System.exit(1);
        }

        String datasetPath = args[0];
        String sortingAlgorithm = args[1];
        int numberOfRecords;

        try {
            numberOfRecords = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Error: The third argument must be an integer representing the number of records.");
            System.exit(1);
            return;
        }

        File datasetFile = new File(datasetPath);
        if (!datasetFile.exists() || !datasetFile.canRead()) {
            System.err.println("Error: The dataset file cannot be found or read.");
            System.exit(1);
        }

        ArrayList<Salaries> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(datasetFile))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count == 0) { // Skip the header row
                    count++;
                    continue;
                }
                String[] data = line.split(",");
                records.add(new Salaries(
                        Integer.parseInt(data[0].trim()),
                        data[1].trim(),
                        data[2].trim(),
                        Integer.parseInt(data[3].trim()),
                        data[4].trim(),
                        data[5].trim()));
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error reading from the dataset file: " + e.getMessage());
            System.exit(1);
        }

        ArrayList<Salaries> origList=new ArrayList<Salaries>();   // original list
        for(int i = 0; i < numberOfRecords; i++){
            origList.add(records.get(i));
        }
        ArrayList<Salaries> quickList=new ArrayList<Salaries>();   // list to be sorted via quick sort
        ArrayList<Salaries> bubbleList=new ArrayList<Salaries>();   // list to be sorted via bubble sort
        ArrayList<Salaries> transpositionList=new ArrayList<Salaries>();   // list to be sorted via transposition sort


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sortingAlgorithm + "_metrics.txt", true))) {
            long start, end;

            switch (sortingAlgorithm.toLowerCase()) {
                case "bubble":
                    for(int i=0;i<origList.size();i++) {
                        bubbleList.add(origList.get(i));
                    }
                    start = System.currentTimeMillis();
                    int bubbleSwaps = bubbleSort(bubbleList, bubbleList.size());
                    end = System.currentTimeMillis();
                    writer.write("Data Size: " + numberOfRecords + ", Swaps: " + bubbleSwaps + ", Time: " + (end - start) + " ms\n");
                    break;
                case "transposition":
                    for(int i=0;i<origList.size();i++) {
                        transpositionList.add(origList.get(i));
                    }
                    start = System.currentTimeMillis();
                    int transpositionSwaps = transpositionSort(transpositionList, transpositionList.size());
                    end = System.currentTimeMillis();
                    writer.write("Data Size: " + numberOfRecords + ", Swaps: " + transpositionSwaps + "\n");
                    break;
                case "quick":
                    for(int i=0;i<origList.size();i++) {
                        quickList.add(origList.get(i));
                    }
                    start = System.currentTimeMillis();
                    quickSort(quickList, 0, quickList.size());
                    end = System.currentTimeMillis();
                    writer.write("Data Size: " + numberOfRecords + ", Time: " + (end - start) + " ms\n");
                    break;
                default:
                    System.err.println("Invalid sort type. Use 'bubble', 'transposition', or 'quick'.");
                    System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }
}
