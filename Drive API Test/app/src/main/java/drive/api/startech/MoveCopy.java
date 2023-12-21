package drive.api.startech;

import com.google.api.services.drive.model.File;

import java.io.IOException;

import javax.swing.JOptionPane;

// Child class representing the Copy operation
public class MoveCopy extends Command {
    private final CommandE commandEnum = CommandE.MOVE_COPY;
    private File file;

    /**
     * 
     * @param arguments - 0 is the file id.
     */
    public MoveCopy(String[] arguments, CommandPanel file) { // Constructor to call the parent class constructor and initialize the source and destination variables
        super(arguments[0]);
        this.file = file.getFile();
        if(arguments.length != 1){
            try {
                throw new Exception("Not the correct number of arguments! Copy only takes one argument: the file ID!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public File getFile() {
        return this.file;
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    // Implementation of performOperation method for Copy class
    @Override
    protected void performOperation(java.io.File file) throws IOException {
        //nothin
    }
}