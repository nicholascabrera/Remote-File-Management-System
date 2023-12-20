package drive.api.startech;

import com.google.api.services.drive.model.File;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CommandPanel extends JFrame{
    // Attribute
    // Nested files (only if folder)
    //TEMP MIMETYPE
    private CommandPanel parentPanel;
    private ArrayList<CommandPanel> childPanels;
    private File file;

    public CommandPanel(File file, ArrayList<CommandPanel> childPanels, CommandPanel parentPanel){
        this.parentPanel = parentPanel;
        this.childPanels = childPanels;
        this.file = file;
    }

    public CommandPanel() {
    }

    public void buildRootPanel(ArrayList<CommandPanel> childPanels){
        this.childPanels = childPanels;
    }
    
    public JPanel buildPanel(File file, EventHandler handler, CommandHistory commandHistory) {
        this.file = file;

        // Attributes
        final int PANEL_WIDTH = 400;
        final int PANEL_HEIGHT = 40;
        JLabel fileName;
        JComboBox<String> dropdownBox;
        JButton submitButton;
        JButton openFolder;
        JPanel panel;


        //GET FILE DETAILS FROM API
        // mimetype
        // file name

        panel = new JPanel();
        fileName = new JLabel(file.getName());

        if (file.getMimeType().equals("application/vnd.google-apps.folder")) {
            openFolder = new JButton("OPEN");
            openFolder.addActionListener(handler);
            openFolder.setActionCommand("open");

            panel.add(fileName);
            panel.add(openFolder);
        } else {
            // Create panel elements
            String[] dropdownOptions = {"Open", "Copy", "Move", "Delete"};
            dropdownBox = new JComboBox<>(dropdownOptions);
            Invoker invoker = new Invoker(commandHistory, dropdownBox);
            submitButton = new JButton("SUBMIT");
            submitButton.addActionListener(invoker);
            submitButton.setActionCommand("submit");
            /**
             * TOD(PM) - Link the submit button with the invoker. (Completed 12/19/23 @ 19:36)
             */

            // add elements to panel
            panel.add(fileName);
            panel.add(dropdownBox);
            panel.add(submitButton);
        }
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return panel;
    }

    public CommandPanel getParentPanel() {
        return parentPanel;
    }

    public void setParentPanel(CommandPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public ArrayList<CommandPanel> getChildPanels() {
        return childPanels;
    }

    public void setChildPanels(ArrayList<CommandPanel> childPanels) {
        this.childPanels = childPanels;
    }

    public File getFile() {
        return file;
    }

    public Boolean containsChild(String ID){
        for(CommandPanel childPanel: this.childPanels){
            if (childPanel.getFile().getId().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public void addChild(CommandPanel childPanel){
        this.childPanels.add(childPanel);
    }

    // Build panel for file parameter
                // TEMP MIMETYPE
    public JPanel buildPanel(String FILE_DESCRIPTION, String MIMETYPE, String FILENAME, EventHandler handler, CommandHistory commandHistory) {
        // Attributes
        final int PANEL_WIDTH = 400;
        final int PANEL_HEIGHT = 40;
        JLabel fileName;
        JComboBox<String> dropdownBox;
        JButton submitButton;
        JButton openFolder;
        JPanel panel;


        //GET FILE DETAILS FROM API
        // mimetype
        // file name

        panel = new JPanel();
        fileName = new JLabel(FILENAME);

        if (MIMETYPE == "folder") {
            openFolder = new JButton("OPEN");
            openFolder.addActionListener(handler);
            openFolder.setActionCommand("open");

            panel.add(fileName);
            panel.add(openFolder);
        }


        else {
            // Create panel elements
            String[] dropdownOptions = {"Open", "Copy", "Move", "Delete"};
            dropdownBox = new JComboBox<>(dropdownOptions);
            Invoker invoker = new Invoker(commandHistory, dropdownBox);
            submitButton = new JButton("SUBMIT");
            submitButton.addActionListener(invoker);
            submitButton.setActionCommand("submit");
            /**
             * TOD(PM) - Link the submit button with the invoker. (Completed 12/19/23 @ 19:36)
             */

            // add elements to panel
            panel.add(fileName);
            panel.add(dropdownBox);
            panel.add(submitButton);
        }
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return panel;
    }

    public JPanel buildNewFile(CommandHistory commandHistory) {
        // Attributes
        // final int PANEL_WIDTH = 400;
        // final int PANEL_HEIGHT = 40;
        JLabel newFileLabel;
        JComboBox<String> dropdownFileType;
        JButton submitButton;
        JPanel panel;

        // create panel elements
        panel = new JPanel();
        newFileLabel = new JLabel("Create new file. Select file type.");
        String[] dropdownOptions = {"Doc", "Ppt", "xls", "folder"};
        dropdownFileType = new JComboBox<>(dropdownOptions);
        Invoker invoker = new Invoker(commandHistory);
        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(invoker);
        submitButton.setActionCommand("submit");

        // add elements to panel
        panel.add(newFileLabel);
        panel.add(dropdownFileType);
        panel.add(submitButton);

        return panel;
    }


    //TEMP MIMETYPE
//    public ArrayList<JPanel> createPanelArray(ArrayList<String> MIME_ARRAY) {
//        ArrayList<JPanel> newPanelArray = new ArrayList<>();
//        for (int i = 0; i<MIME_ARRAY.size(); i++); {
//            JPanel newPanel = new CommandPanel().buildPanel(MIME_ARRAY);
//            newPanelArray.add(newPanel);
//        }
//        return newPanelArray;
//    }
}