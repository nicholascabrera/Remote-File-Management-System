package drive.api.startech;

public class CommandFactory { 

    public Command createCommand(CommandE commandEnum, String[] arguments, CommandPanel currentPanel) {
        //The createCommand method takes three parameters: commandName, arguments, and the current panel.
        // Mapping commandName to corresponding Command class
        switch (commandEnum) { /*switch statement is used to determine the appropriate subclass of the Command interface 
                                            based on the Command Name parameter.*/
            case CREATE:
                System.out.println("CREATE COMMAND EXECUTED");
                return new Create(arguments, currentPanel.getParentPanel());  // Creating Create command
            case COPY:
                System.out.println("COPY COMMAND EXECUTED");
                return new Copy(arguments, currentPanel);  // Creating Copy command
            case MOVE_COPY:
                System.out.println("MOVE COPY COMMAND EXECUTED");
                return new MoveCopy(arguments, currentPanel);  // Creating Move-Copy command
            case DELETE:
                System.out.println("DELETE COMMAND EXECUTED");
                return new Delete(arguments);  // Creating Delete command
            case PASTE:
                System.out.println("PASTE COMMAND EXECUTED");
                return new Paste(arguments);  // Creating Paste command
            case MOVE:
                System.out.println("MOVE COMMAND EXECUTED");
                return new Move(arguments);  // Creating Move command
            case OPEN:
                System.out.println("OPEN COMMAND EXECUTED");
                return new Open(arguments, currentPanel); // Creating Open Command
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
