package drive.api.startech;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

// Child class representing the Create operation
public class Create extends Command {
    private final CommandE commandEnum = CommandE.CREATE;

    public Create(String[] arguments) { // Constructor to call the parent class constructor and initialize the fileName variable
        super(arguments[0]);    //the first element of the arguments array will be the fileName or the source file.
        if(arguments.length != 1){
            try {
                throw new Exception("Not the correct number of arguments! Create only takes one argument: the file name!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    // Implementation of performOperation method for Create class
    @Override
    protected void performOperation(File file) throws IOException {
        if (file.createNewFile()) { // Create a new file using the createNewFile() method of the File class
            System.out.println("File created: " + file); // Print a success message if the file is created
        } else {
            System.out.println("File already exists."); // Print an error message if the file already exists
        }
    }
}