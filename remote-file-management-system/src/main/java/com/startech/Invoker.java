package com.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Invoker implements ActionListener{

    private CommandFactory factory;
    private Stack<Command> commandHistory;

    public Invoker(){
        factory = new CommandFactory();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = new Command();

        if(e.getActionCommand() == "copy"){
            command = factory.getCommand(CommandE.COPY);
        } else if(e.getActionCommand() == "paste"){
            command = factory.getCommand(CommandE.PASTE);
        } else if(e.getActionCommand() == "create"){
            command = factory.getCommand(CommandE.CREATE);
        } else if(e.getActionCommand() == "delete"){
            command = factory.getCommand(CommandE.DELETE);
        } else if(e.getActionCommand() == "move"){
            command = factory.getCommand(CommandE.MOVE);
        } else if(e.getActionCommand() == "undo"){
            command = factory.getCommand(CommandE.UNDO);
        }

        commandHistory.add(command);
        Executor executor = new Executor(command);
        executor.execute();
    }
    
}
