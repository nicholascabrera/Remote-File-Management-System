package com.startech;

public class CommandFactory {
    // class members

    public CommandFactory(){
        // constructor stuff
    }

    public Command getCommand(CommandE commandEnum){
        return new Command(commandEnum);   //placeholder code so it doesn't error out
    }

    // public Object get(){
    //     if(command == CommandE.COPY){
    //         return COPY object
    //     }
    // }
}
