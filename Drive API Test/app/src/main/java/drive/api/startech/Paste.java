package drive.api.startech;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

// Child class representing the Paste operation
public class Paste extends Command {
    private final CommandE commandEnum = CommandE.PASTE;
    private String destination; // Variable to store the destination file name

    public Paste(String[] arguments) { // Constructor to call the parent class constructor and initialize the sourceFile and destinationFile variables
        super(arguments[0]);
        if(arguments.length != 2){
            try {
                throw new Exception("Not the correct number of arguments! Paste takes two arguments: the source file id and the destination file id!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        this.destination = arguments[1];
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    public String getDestination() {
        return destination;
    }

    // Implementation of performOperation method for Paste class
    @Override
    protected void performOperation(File file) throws IOException {
        File source = new File(fileName); // Create a new File object with the specified source file name
        File destination = new File(file.getPath()); // Create a new File object with the path of the destination folder

        if (source.exists()) { // Check if the source file exists
            try {
                Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING); // Copy the source file to the destination folder using the copy() method of the Files class
                System.out.println("File pasted to:\t" + this.destination); // Print a success message
            } catch (IOException e) {
                System.out.println("An error occurred while copying the file."); // Print an error message if an IOException occurs during the copy operation
            }
        } else {
            System.out.println("Source file does not exist."); // Print an error message if the source file does not exist
        }
    }
}
