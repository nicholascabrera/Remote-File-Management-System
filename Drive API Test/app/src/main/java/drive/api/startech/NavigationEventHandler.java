package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.api.services.drive.Drive;

public class NavigationEventHandler implements ActionListener {

    private CommandWindow oldWindow;
    private CommandPanel currentPanel;
    private Drive service;

    public NavigationEventHandler(Drive service, CommandWindow oldWindow, CommandPanel currentPanel){
        this.service = service;
        this.oldWindow = oldWindow;
        this.currentPanel = currentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("back") && currentPanel.getParentPanel() != null){
            CommandWindow window = new CommandWindow(this.service, this.currentPanel.getParentPanel(), new CommandHistory());
            window.execute();
            oldWindow.dispose();
        }
    }
    
}
