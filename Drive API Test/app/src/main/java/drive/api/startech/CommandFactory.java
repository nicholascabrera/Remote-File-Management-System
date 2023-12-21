package drive.api.startech;

public class CommandFactory { 

    public Command createCommand(CommandE commandEnum, String[] arguments, CommandPanel currentPanel) {
        //The createCommand method takes two parameters: commandName and arguments.
        // Mapping commandName to corresponding Command class
        switch (commandEnum) { /*switch statement is used to determine the appropriate subclass of the Command interface 
                                            based on the commandName parameter. The commandName is converted to lowercase using
                                            commandName.toLowerCase() in order to handle case-insensitive matching.*/
            case CREATE:
                System.out.println("CREATE COMMAND EXECUTED");
                return new Create(arguments, currentPanel.getParentPanel());  // Creating Create command
            case COPY:
                System.out.println("COPY COMMAND EXECUTED");
                return new Copy(arguments, currentPanel);  // Creating Copy command
            case DELETE:
                System.out.println("DELETE COMMAND EXECUTED");
                return new Delete(arguments);  // Creating Delete command
            case PASTE:
                System.out.println("PASTE COMMAND EXECUTED");
                return new Paste(arguments);  // Creating Paste command
            case MOVE:
                System.out.println("MOVE COMMAND EXECUTED");
                return new Move(arguments);  // Creating Move command
            default:
                throw new IllegalArgumentException("Unknown command.");
                /*If the commandName doesn't match any of the specific cases, an IllegalArgumentException is thrown 
                with an error message saying that the command is unknown. */
        }
    }

    public Command getCommand(CommandE commandEnum, String[] arguments, CommandPanel currentFolder){
        if(commandEnum==null){
            return new Delete(new String[]{"null"});
        }
        return this.createCommand(commandEnum, arguments, currentFolder);
    }
}
