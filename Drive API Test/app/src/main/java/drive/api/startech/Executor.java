package drive.api.startech;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

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
                try {
                    UploadToFolder.moveFileToFolder(service, command.getFileName(), ((Move)command).getDestination());
                    CommandWindow.refresh(service);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case NULL:
                //does nothing.
                break;
            case PASTE:
                try {
                    UploadToFolder.pasteFileToFolder(service, command.getFileName(), ((Paste)command).getDestination());
                    CommandWindow.refresh(service);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case OPEN:
                try {
                    ByteArrayOutputStream byteArrayOutputStream = UploadToFolder.exportPdf(service, command.getFileName());
                    try (OutputStream outputStream = new FileOutputStream(((Open)command).getFile().getFile().getName())) {
                        byteArrayOutputStream.writeTo(outputStream);
                    }

                    CommandWindow.refresh(service);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case UNDO:
                //will not be implemented.
                break;
            default:
                break;
        }
    }
}
