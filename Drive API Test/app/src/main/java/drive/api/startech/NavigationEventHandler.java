package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.api.services.drive.Drive;

public class NavigationEventHandler implements ActionListener {

    private CommandWindow oldWindow;
    private CommandPanel currentPanel;
    private CommandHistory commandHistory;

    private Drive service;

    public NavigationEventHandler(Drive service, CommandWindow oldWindow, CommandPanel currentPanel, CommandHistory commandHistory){
        this.service = service;
        this.oldWindow = oldWindow;
        this.currentPanel = currentPanel;
        this.commandHistory = commandHistory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("back") && currentPanel.getParentPanel() != null){
            CommandWindow window = new CommandWindow(this.service, this.currentPanel.getParentPanel(), this.commandHistory);
            window.execute();
            oldWindow.dispose();
        }
    }
    
}
