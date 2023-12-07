package com.startech;

public class Command {
    private CommandE e;

    public Command(){
        //nothing
    }

    public Command(CommandE e){
        this.e = e;
    }

    public void setEnum(CommandE e) {
        this.e = e;
    }

    public CommandE getEnum() {
        return e;
    }
}
