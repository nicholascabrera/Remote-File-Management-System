package drive.api.startech;

import java.io.IOException;

import java.io.File;

// Abstract class representing a file operation
public class Command {
    protected String fileName; // Variable to store the file name

    public Command(String fileName) { // Constructor to initialize the fileName variable
        this.fileName = fileName;
    }

    public CommandE getEnum(){
        return CommandE.NULL;
    }

    public String getFileName() { // Getter method for the fileName variable
        return fileName;
    }

    // Method to execute the operation
    public void execute() { 
        File file = new File(fileName); // Create a new File object with the specified fileName
        try {
            performOperation(file); // Perform operation on the file
        } catch (IOException e) {
            System.out.println("An error occurred while performing the operation."); // Print an error message if an IOException occurs
        }
    }

    // Abstract method to perform the specific file operation
    protected void performOperation(File file) throws IOException{}
}