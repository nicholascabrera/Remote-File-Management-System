package drive.api.startech;

import java.io.IOException;

import javax.swing.JOptionPane;

// Child class representing the Copy operation
public class Open extends Command {
    private final CommandE commandEnum = CommandE.OPEN;
    private CommandPanel file;

    /**
     * 
     * @param arguments - 0 is the file id.
     */
    public Open(String[] arguments, CommandPanel file) { // Constructor to call the parent class constructor and initialize the source and destination variables
        super(arguments[0]);
        this.file = file;
        if(arguments.length != 1){
            try {
                throw new Exception("Not the correct number of arguments! Open only takes one argument: the file ID!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public CommandPanel getFile() {
        return file;
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