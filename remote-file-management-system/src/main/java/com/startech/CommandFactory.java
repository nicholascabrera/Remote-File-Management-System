package com.startech;

class CommandFactory {  
    public Command createCommand(String commandName, String[] arguments) {
        //The createCommand method takes two parameters: commandName and arguments.
        // Mapping commandName to corresponding Command class
        switch (commandName.toLowerCase()) { /*switch statement is used to determine the appropriate subclass of the Command interface 
                                            based on the commandName parameter. The commandName is converted to lowercase using
                                            commandName.toLowerCase() in order to handle case-insensitive matching.*/
            case "create":
                System.out.println(commandName.toUpperCase() + " COMMAND EXECUTED");
                return new Create(arguments);  // Creating Create command
            case "copy":
                System.out.println(commandName.toUpperCase() + " COMMAND EXECUTED");
                return new Copy(arguments);  // Creating Copy command
            case "delete":
                System.out.println(commandName.toUpperCase() + " COMMAND EXECUTED");
                return new Delete(arguments);  // Creating Delete command
            case "paste":
                System.out.println(commandName.toUpperCase() + " COMMAND EXECUTED");
                return new Paste(arguments);  // Creating Paste command
            case "move":
                System.out.println(commandName.toUpperCase() + " COMMAND EXECUTED");
                return new Move(arguments);  // Creating Move command
            default:
                throw new IllegalArgumentException("Unknown command: " + commandName);
                /*If the commandName doesn't match any of the specific cases, an IllegalArgumentException is thrown 
                with an error message saying that the command is unknown. */
        }
    }

    public Command getCommand(CommandE commandEnum, String[] arguments){
        if(commandEnum==null){
            return new Create(new String[]{"null"});
        }
        return this.createCommand(commandEnum.toString(), arguments);
    }
}
