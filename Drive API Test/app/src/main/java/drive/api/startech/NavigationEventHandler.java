package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationEventHandler implements ActionListener {

    private CommandWindow oldWindow;
    private CommandPanel currentPanel;

    public NavigationEventHandler(CommandWindow oldWindow, CommandPanel currentPanel){
        this.oldWindow = oldWindow;
        this.currentPanel = currentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("back") && currentPanel.getParentPanel() != null){
            CommandWindow window = new CommandWindow(this.currentPanel.getParentPanel(), new CommandHistory());
            window.execute();
            oldWindow.dispose();
        }
    }
    
}
