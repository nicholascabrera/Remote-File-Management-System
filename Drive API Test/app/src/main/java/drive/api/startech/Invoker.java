package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Invoker implements ActionListener{

    private CommandFactory factory;
    private Stack<Command> commandHistory;

    public Invoker(){
        factory = new CommandFactory();
        commandHistory = new Stack<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = factory.getCommand(null, null);

        /*
         * all the arguments are made up, since we don't have a gui to work
         * with its the best we've got for now.
         */
        if(e.getActionCommand() == "copy"){
            command = factory.getCommand(CommandE.COPY, new String[]{"source", "destination"});    
        } else if(e.getActionCommand() == "paste"){
            command = factory.getCommand(CommandE.PASTE, new String[]{"source", "destination"});
        } else if(e.getActionCommand() == "create"){
            command = factory.getCommand(CommandE.CREATE, new String[]{"source"});
        } else if(e.getActionCommand() == "delete"){
            command = factory.getCommand(CommandE.DELETE, new String[]{"source"});
        } else if(e.getActionCommand() == "move"){
            command = factory.getCommand(CommandE.MOVE, new String[]{"source", "destination"});
        } else if(e.getActionCommand() == "undo" && commandHistory.size() > 0){
            Command lastCommand = commandHistory.pop();
            switch(lastCommand.getEnum()){
                case CREATE:
                    command = factory.getCommand(CommandE.DELETE, new String[]{lastCommand.getFileName()});
                    //set parameters somehow, work with melodie.
                    break;
                case DELETE:
                    command = factory.getCommand(CommandE.CREATE, new String[]{lastCommand.getFileName()});
                    //set parameters somehow, work with melodie.
                    break;
                case MOVE:
                    command = factory.getCommand(CommandE.MOVE, new String[]{((Move)(lastCommand)).getDestination(), lastCommand.getFileName()});
                    //set the new destination to the old source, and the new source to the old destination.
                    //set parameters somehow, work with melodie.
                    break;
                case PASTE:
                    command = factory.getCommand(CommandE.PASTE, new String[]{((Paste)(lastCommand)).getDestination(), lastCommand.getFileName()});
                    //in order to undo a paste, the file must be deleted and created at its original source. the best way to do this
                        // is with another paste command.
                    //set parameters somehow, work with melodie.
                    break;
                default:
                    //in the case of copy, no action is necessary as nothing actually happens, only prepares for a paste command.
                        //paste deletes the file at its original destination, and creates it again at the new destination.
                    //in the case of undo, i simply do not want to go through the headache that is undoing an undo.
                    break;
            }
        }  else if(e.getActionCommand() == "undo" && commandHistory.size() == 0) {
            System.out.println("There are no commands to undo!");
            return;
        }

        if(e.getActionCommand() != "undo"){commandHistory.add(command);}
        Executor executor = new Executor(command);
        executor.execute();
    }
    
}
