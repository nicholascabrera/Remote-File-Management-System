package drive.api.startech;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

// Child class representing the Create operation
public class Create extends Command {
    private final CommandE commandEnum = CommandE.CREATE;
    private String mimeType;
    private CommandPanel folder;

    /**
     * 
     * @param arguments - 0 is the fileName, 1 is the mimeType, 2 is the folder
     */
    public Create(String[] arguments, CommandPanel folder) { // Constructor to call the parent class constructor and initialize the fileName variable
        super(arguments[0]);    //the first element of the arguments array will be the fileName.
        this.mimeType = arguments[1];
        this.folder = folder;
        if(arguments.length != 2){
            try {
                throw new Exception("Not the correct number of arguments! Create only takes two arguments: the file name and the mimeType!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public String getMimeType() {
        return mimeType;
    }

    public CommandPanel getFolder() {
        return folder;
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