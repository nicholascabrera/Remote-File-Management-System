package drive.api.startech;
import javax.swing.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.drive.Drive;

import java.awt.*;
import java.util.ArrayList;

public class CommandWindow extends JFrame {
    private CommandPanel rootPanel;
    private static JPanel mainPanel;
    private static JPanel textPanel;

    public CommandWindow(Drive service, CommandPanel rootPanel, CommandHistory commandHistory){
        setup(service, rootPanel, commandHistory);
    }

    public CommandWindow(Drive service, ArrayList<JPanel> panelArrayList, CommandHistory commandHistory) {
        super("Storage Drive");
        setArrayList(service, panelArrayList, commandHistory);
    }

    public CommandWindow() {}

    public void setRootPanel(CommandPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public void setup(Drive service, CommandPanel rootPanel, CommandHistory commandHistory){
        this.rootPanel = rootPanel;
        ArrayList<JPanel> panels = new ArrayList<>();

        for(CommandPanel childPanel : rootPanel.getChildPanels()){
            EventHandler handler = new EventHandler();
            handler.setCommandWindow(this);
            handler.setChildPanel(childPanel);
            handler.setService(service);
            JPanel panel = childPanel.buildPanel(childPanel.getFile(), handler, commandHistory);
            panels.add(panel);
        }

        setArrayList(service, panels, commandHistory);
    }

    public void setArrayList(Drive service, ArrayList<JPanel> panelArrayList, CommandHistory commandHistory){
        // Window size. create constants
        final int WINDOW_HEIGHT = 500;
        final int WINDOW_WIDTH = 700;

        final int WINDOW_X = ((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth())/2) - (WINDOW_WIDTH/2);
        final int WINDOW_Y = ((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight())/2) - (WINDOW_HEIGHT/2);

        // Create a window with title
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLocation(WINDOW_X, WINDOW_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); //we will do the layout ourselves.
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, WINDOW_WIDTH, 400);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new CommandPanel(service, rootPanel, rootPanel.getID()).buildNewFile(commandHistory));
        bottomPanel.setBounds(0, 400, WINDOW_WIDTH, 25);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        textPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setActionCommand("back");
        textPanel.add(back);
        textPanel.setBounds(0, 425, WINDOW_WIDTH, 75);

        if(this.rootPanel.getParentPanel() == null){
            back.setEnabled(false);
        }

        NavigationEventHandler nHandler = new NavigationEventHandler(service, this, this.rootPanel);
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

    public static void refresh(Drive service) throws IOException{
        try {
            new DriveQuickstart().driveStart();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
