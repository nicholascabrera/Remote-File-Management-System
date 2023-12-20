package drive.api.startech;
/**
 * TODO(UI/UX) - Find a way to navigate back from a file you entered.
 */
public class CommandWindowDemo {
    public void createAndRunGUI(CommandHistory commandHistory, CommandPanel rootPanel){
        CommandWindow window = new CommandWindow(rootPanel, commandHistory);
        window.execute();
    }
}
