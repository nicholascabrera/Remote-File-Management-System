package drive.api.startech;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class CommandWindow extends JFrame {

    public CommandWindow(CommandPanel rootPanel, CommandHistory commandHistory){
        ArrayList<JPanel> panels = new ArrayList<>();

        for(CommandPanel childPanel : rootPanel.getChildPanels()){
            EventHandler handler = new EventHandler();
            handler.setCommandWindow(this);
            handler.setChildPanel(childPanel);
            JPanel panel = childPanel.buildPanel(childPanel.getFile(), handler, commandHistory);
            panels.add(panel);
        }

        setArrayList(panels, commandHistory);
    }

    public CommandWindow(ArrayList<JPanel> panelArrayList, CommandHistory commandHistory) {
        super("Storage Drive");
        setArrayList(panelArrayList, commandHistory);
    }

    public void setArrayList(ArrayList<JPanel> panelArrayList, CommandHistory commandHistory){
        // Window size. create constants
        final int WINDOW_HEIGHT = 500;
        final int WINDOW_WIDTH = 700;

        // Create a window with title
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setMaximumSize(new Dimension(500,600));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new CommandPanel().buildNewFile(commandHistory));
        bottomPanel.setMaximumSize(new Dimension(400,40));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        for (int i=0; i<panelArrayList.size(); i++) {
            mainPanel.add(panelArrayList.get(i));
        }
        mainPanel.add(bottomPanel);
        add(mainPanel);
    }

    public void execute(){
        // Display window
        setVisible(true);
    }
}
