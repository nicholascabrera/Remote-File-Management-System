package drive.api.startech;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class CommandWindow extends JFrame {

    public CommandWindow(){}

    public CommandWindow(ArrayList<JPanel> panelArrayList) {
        super("Storage Drive");
        setArrayList(panelArrayList);
    }

    public void setArrayList(ArrayList<JPanel> panelArrayList){
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
        bottomPanel.add(new CommandPanel().buildNewFile());
        bottomPanel.setMaximumSize(new Dimension(400,40));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        for (int i=0; i<panelArrayList.size(); i++) {
            mainPanel.add(panelArrayList.get(i));
        }
        mainPanel.add(bottomPanel);
        add(mainPanel);
        // Display window
        setVisible(true);
    }
}
