package drive.api.startech;

import java.io.IOException;

import com.google.api.services.drive.Drive;

import javax.swing.JOptionPane;

public class Executor {
    private Command command;
    private Drive service;

    public Executor(Drive service, Command command){
        this.service = service;
        this.command = command;
    }

    public void execute(){
        switch(command.getEnum()){
            case CREATE:
                try {
                    UploadToFolder.createFile(service, command.getFileName(), ((Create)command).getMimeType(), ((Create)command).getFolder());
                    CommandWindow.refresh(service);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case COPY:
                System.out.println("Copied file: " + command.getFileName());
                break;
            case DELETE:
                try {
                    UploadToFolder.deleteFile(service, command.getFileName());
                    CommandWindow.refresh(service);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case MOVE:
                break;
            case NULL:
                //does nothing.
                break;
            case PASTE:
                break;
            case UNDO:
                //will not be implemented.
                break;
            default:
                break;
        }
    }
}
