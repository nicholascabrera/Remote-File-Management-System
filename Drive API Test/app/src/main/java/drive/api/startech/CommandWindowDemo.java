package drive.api.startech;
import javax.swing.*;

import java.util.ArrayList;


public class CommandWindowDemo {
    public void createAndRunGUI(){

        ArrayList<JPanel> panelsArray = new ArrayList<>();
        ArrayList<JPanel> folderArray = new ArrayList<>();

        CommandWindow window = new CommandWindow();

        EventHandler handler = new EventHandler(window);

        JPanel newpanel1 = new CommandPanel().buildPanel("FILE 1", "folder", "Folder 1", handler);
        JPanel newpanel2 = new CommandPanel().buildPanel("FILE 2", "doc", "Doc 1", handler);
        panelsArray.add(newpanel1);
        panelsArray.add(newpanel2);


        JPanel newpanel3 = new CommandPanel().buildPanel("FILE 3", "doc", "DOC 3", handler);
        JPanel newpanel4 = new CommandPanel().buildPanel("FILE 4", "doc", "DOC 4", handler);
        JPanel newpanel5 = new CommandPanel().buildPanel("FILE 5", "doc", "DOC 6", handler);
        JPanel newpanel6 = new CommandPanel().buildPanel("FILE 6", "folder", "DOC 6", handler);
        JPanel newpanel7 = new CommandPanel().buildPanel("FILE 7", "doc", "DOC 7", handler);
        folderArray.add(newpanel3);
        folderArray.add(newpanel4);
        folderArray.add(newpanel5);
        folderArray.add(newpanel6);
        folderArray.add(newpanel7);

        window.setArrayList(panelsArray);



        //TEMP ARRAY LIST TEST
        ArrayList<String> mimeArray = new ArrayList<>();
        mimeArray.add("folder");
        mimeArray.add("doc");
    }


}
