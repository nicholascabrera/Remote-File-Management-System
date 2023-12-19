package drive.api.startech;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class EventHandler implements ActionListener {

    CommandWindow oldWindow;

    public EventHandler(){

    }

    public EventHandler(CommandWindow oldWindow){
        this.oldWindow = oldWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            oldWindow.dispose();
            new CommandWindow(new ArrayList<JPanel>());
        }
    }
}
