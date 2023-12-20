package drive.api.startech;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class CommandWindow extends JFrame {
    private CommandPanel rootPanel;

    public CommandWindow(CommandPanel rootPanel, CommandHistory commandHistory){
        this.rootPanel = rootPanel;
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
        setLayout(null); //we will do the layout ourselves.

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, WINDOW_WIDTH, 400);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new CommandPanel().buildNewFile(commandHistory));
        bottomPanel.setBounds(0, 400, WINDOW_WIDTH, 25);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel textPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setActionCommand("back");
        textPanel.add(back);
        textPanel.setBounds(0, 425, WINDOW_WIDTH, 75);

        if(this.rootPanel.getParentPanel() == null){
            back.setEnabled(false);
        }

        NavigationEventHandler nHandler = new NavigationEventHandler(this, this.rootPanel);
        back.addActionListener(nHandler);

        for (int i=0; i<panelArrayList.size(); i++) {
            mainPanel.add(panelArrayList.get(i));
        }
        mainPanel.add(bottomPanel);
        add(mainPanel);
        add(textPanel);
    }

    public void execute(){
        // Display window
        setVisible(true);
    }
}
