package drive.api.startech;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

// Child class representing the Move operation
public class Move extends Command {
    private String destination; // Variable to store the destination file name
    private final CommandE commandEnum = CommandE.MOVE;

    public Move(String[] arguments) { // Constructor to call the parent class constructor and initialize the source and destination variables
        super(arguments[0]);
        if(arguments.length != 2){
            try {
                throw new Exception("Not the correct number of arguments! Move takes two arguments: the source file name and the destination!");
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

    public String getDestination() { // Getter method for the destination variable
        return destination;
    }

    // Implementation of performOperation method for Move class
    @Override
    protected void performOperation(File file) throws IOException {
        File destFile = new File(destination); // Create a new File object with the specified destination file name
        Files.move(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING); // Move the source file to the destination file using the move() method of the Files class
        System.out.println(file.getName() + " has been moved to " + destFile.getPath()); // Print a success message
    }

}