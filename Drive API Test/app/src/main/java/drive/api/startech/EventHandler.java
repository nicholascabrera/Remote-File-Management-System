package drive.api.startech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {

    private CommandWindow oldWindow;
    private CommandPanel childPanel;

    public EventHandler(){}

    public void setCommandWindow(CommandWindow oldWindow){
        this.oldWindow = oldWindow;
    }

    public void setChildPanel(CommandPanel childPanel){
        this.childPanel = childPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            CommandWindow window = new CommandWindow(this.childPanel, new CommandHistory());
            window.execute();
            oldWindow.dispose();
        }
    }
}
