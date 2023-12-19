package drive.api.startech;
import javax.swing.*;

import java.awt.*;

public class CommandPanel extends JFrame{
    // Attribute
    // Nested files (only if folder)
    //TEMP MIMETYPE
//    ArrayList<JPanel> nestedFilePanels = createPanelArray();



    // Build panel for file parameter
                // TEMP MIMETYPE
    public JPanel buildPanel(String FILE_DESCRIPTION, String MIMETYPE, String FILENAME, EventHandler handler) {
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
            String[] dropdownOptions = {"Open", "Rename", "Move", "Delete"};
            dropdownBox = new JComboBox<>(dropdownOptions);
            submitButton = new JButton("SUBMIT");

            // add elements to panel
            panel.add(fileName);
            panel.add(dropdownBox);
            panel.add(submitButton);
        }
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return panel;
    }

    public JPanel buildNewFile() {
        // Attributes
        final int PANEL_WIDTH = 400;
        final int PANEL_HEIGHT = 40;
        JLabel newFileLabel;
        JComboBox<String> dropdownFileType;
        JButton submitButton;
        JPanel panel;

        // create panel elements
        panel = new JPanel();
        newFileLabel = new JLabel("Create new file. Select file type.");
        String[] dropdownOptions = {"Doc", "Ppt", "xls", "folder"};
        dropdownFileType = new JComboBox<>(dropdownOptions);
        submitButton = new JButton("SUBMIT");

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