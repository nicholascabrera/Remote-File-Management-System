package drive.api.startech;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

// Child class representing the Delete operation
public class Delete extends Command {
    private final CommandE commandEnum = CommandE.DELETE;

    public Delete(String[] arguments) { // Constructor to call the parent class constructor and initialize the fileName variable
        super(arguments[0]);
        if(arguments.length != 1){
            try {
                throw new Exception("Not the correct number of arguments! Delete only takes one argument: the file name!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    // Implementation of performOperation method for Delete class
    @Override
    protected void performOperation(File file) throws IOException {
        int option = JOptionPane.showConfirmDialog((Component)null, "Are you sure you want to delete the file?", // Show a confirmation dialog box using the showConfirmDialog() method of the JOptionPane class
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) { // Check if the user chooses the Yes option
            if (file.delete()) { // Delete the file using the delete() method of the File class
                System.out.println("File deleted: " + file); // Print a success message if the file is deleted
            } else {
                System.out.println("File could not be deleted."); // Print an error message if the file could not be deleted
            }
        } else {
            System.out.println("File deletion canceled."); // Print a message if the user chooses the No option
        }
    }
}