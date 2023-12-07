package com.startech;

public class CommandFactory {
    // class members

    public CommandFactory(){
        // constructor stuff
    }

    public Command getCommand(CommandE commandEnum){
        System.out.println(commandEnum + " COMMAND EXECUTED");
        return new Create("blah");   //placeholder code so it doesn't error out
    }

    // public Object get(){
    //     if(command == CommandE.COPY){
    //         return COPY object
    //     }
    // }
}
