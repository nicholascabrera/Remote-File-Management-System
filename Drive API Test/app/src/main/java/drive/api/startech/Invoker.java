package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.google.api.services.drive.Drive;

public class Invoker implements ActionListener{

    private CommandFactory factory;
    private CommandHistory commandHistory;
    private JComboBox<String> dropDownBox;
    private CommandPanel currentFolder;
    private Drive service;

    public Invoker(Drive service, CommandHistory commandHistory, CommandPanel currentFolder){
        factory = new CommandFactory();
        this.service = service;
        this.commandHistory = commandHistory;
        this.currentFolder = currentFolder;
    }

    public Invoker(Drive service, CommandHistory commandHistory, JComboBox<String> dropDownBox, CommandPanel currentFolder){
        factory = new CommandFactory();
        this.service = service;
        this.commandHistory = commandHistory;
        this.dropDownBox = dropDownBox;
        this.currentFolder = currentFolder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = new Command("Untitled");

        /*
         * all the arguments are made up, since we don't have a gui to work
         * with its the best we've got for now.
         */
        if(e.getActionCommand() == "submit"){
            String selectedItem = (this.dropDownBox.getSelectedItem().equals("File") || this.dropDownBox.getSelectedItem().equals("Folder")) ? "create" : ((String)this.dropDownBox.getSelectedItem()).toLowerCase();
            if(selectedItem.equals("copy")){
                command = factory.getCommand(CommandE.COPY, new String[]{currentFolder.getFile().getId()}, currentFolder);

            } else if(selectedItem.equals("create")){
                String fileName = JOptionPane.showInputDialog(String.format("Please enter a name for your new %s.", this.dropDownBox.getSelectedItem()));
                switch((String)this.dropDownBox.getSelectedItem()){
                    case "File":
                        command = factory.getCommand(CommandE.CREATE, new String[]{fileName, "application/vnd.google-apps.document"}, this.currentFolder);
                        break;
                    case "Folder":
                        command = factory.getCommand(CommandE.CREATE, new String[]{fileName, "application/vnd.google-apps.folder"}, this.currentFolder);
                        break;
                    default:
                        break;
                }

            } else if(selectedItem.equals("delete")){
                command = factory.getCommand(CommandE.DELETE, new String[]{currentFolder.getFile().getId()}, currentFolder);

            } else if(selectedItem.equals("move")){
                command = factory.getCommand(CommandE.MOVE_COPY, new String[]{currentFolder.getFile().getId()}, currentFolder);

            } else if(selectedItem.equals("undo") && commandHistory.size() > 0){
                Command lastCommand = commandHistory.pop();
                switch(lastCommand.getEnum()){
                    case CREATE:
                        command = factory.getCommand(CommandE.DELETE, new String[]{lastCommand.getFileName()}, currentFolder);
                        //set parameters somehow, work with melodie.
                        break;
                    case DELETE:
                        command = factory.getCommand(CommandE.CREATE, new String[]{lastCommand.getFileName()}, currentFolder);
                        //set parameters somehow, work with melodie.
                        break;
                    case MOVE:
                        command = factory.getCommand(CommandE.MOVE, new String[]{((Move)(lastCommand)).getDestination(), lastCommand.getFileName()}, currentFolder);
                        //set the new destination to the old source, and the new source to the old destination.
                        //set parameters somehow, work with melodie.
                        break;
                    case PASTE:
                        command = factory.getCommand(CommandE.PASTE, new String[]{((Paste)(lastCommand)).getDestination(), lastCommand.getFileName()}, currentFolder);
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
            }  else if(selectedItem.equals("undo") && commandHistory.size() == 0) {
                System.out.println("There are no commands to undo!");
                return;
            }

        } else if(e.getActionCommand().equals("paste") && commandHistory.peek().getEnum() == CommandE.COPY){
            command = factory.getCommand(CommandE.PASTE, new String[]{commandHistory.peek().getFileName(), currentFolder.getID()}, currentFolder);

        } else if(e.getActionCommand().equals("move") && commandHistory.peek().getEnum() == CommandE.MOVE_COPY){
            command = factory.getCommand(CommandE.MOVE, new String[]{commandHistory.peek().getFileName(), currentFolder.getID()}, currentFolder);

        }

        if(e.getActionCommand() != "undo"){commandHistory.add(command);}
        Executor executor = new Executor(service, command);
        executor.execute();
    }
}