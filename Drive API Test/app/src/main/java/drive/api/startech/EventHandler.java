package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.api.services.drive.Drive;

public class EventHandler implements ActionListener {

    private CommandWindow oldWindow;
    private CommandPanel childPanel;
    private CommandHistory commandHistory;

    private Drive service;

    public EventHandler(CommandHistory commandHistory){
        this.commandHistory = commandHistory;
    }

    public void setCommandWindow(CommandWindow oldWindow){
        this.oldWindow = oldWindow;
    }

    public void setChildPanel(CommandPanel childPanel){
        this.childPanel = childPanel;
    }

    public void setService(Drive service) {
        this.service = service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            CommandWindow window = new CommandWindow(this.service, this.childPanel, this.commandHistory);
            window.execute();
            oldWindow.dispose();
        }
    }
}
