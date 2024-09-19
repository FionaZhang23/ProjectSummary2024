import java.io.*;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {

        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim(); // Remove redundant spaces
            if (line.isEmpty()) continue;  // Ignore blank lines

            String[] command = line.split("\\s+");
            operate_BST(command);
        }

        scanner.close();
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        if (command == null || command.length == 0) {
            writeToFile("Invalid Command", "./result.txt");
            return;
        }
        switch (command[0].toLowerCase()) {
            case "insert" -> {
                // Ensure there is a second argument for the value
                if (command.length == 2) {
                    try {
                        // Parse the value and insert into the BST
                        int value = Integer.parseInt(command[1]);
                        mybst.insert(value);
                        writeToFile("insert " + value, "./result.txt");
                    } catch (NumberFormatException e) {
                        // Handle case where the value is not a valid integer
                        writeToFile("Invalid Command", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }
            case "search" -> {
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]);
                        boolean found = mybst.search(value);
                        if (found) {
                            writeToFile("found " + value, "./result.txt");
                        } else {
                            writeToFile("search failed", "./result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }
            case "remove" -> {
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]);
                        boolean removed = mybst.search(value); // Check if the value exists before removing
                        if (removed) {
                            mybst.remove(value);
                            writeToFile("removed " + value, "./result.txt");
                        } else {
                            writeToFile("remove failed", "./result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command", "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./result.txt");
                }
            }
            case "print" -> {
                // Print the BST in ascending order using an iterator
                StringBuilder output = new StringBuilder();
                for (Integer value : mybst) {
                    output.append(value).append(" ");
                }
                // Write the result to the file
                writeToFile(output.toString().trim(), "./result.txt");
            }

            // default case for Invalid Command
            default -> writeToFile("Invalid Command", "./result.txt");
        }
    }

    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);    // Write the content to the file
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();      // Print stack trace in case of an exception
        }
    }
}
