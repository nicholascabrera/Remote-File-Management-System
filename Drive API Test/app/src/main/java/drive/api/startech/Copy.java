package drive.api.startech;

import java.io.File;
import java.io.IOException;

// Child class representing the Copy operation
public class Copy extends Command {
    private final CommandE commandEnum = CommandE.COPY;

    public Copy(String[] arguments) { // Constructor to call the parent class constructor and initialize the source and destination variables
        super(arguments[0]);
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    // Implementation of performOperation method for Copy class
    @Override
    protected void performOperation(File file) throws IOException {
        //nothin
    }
}