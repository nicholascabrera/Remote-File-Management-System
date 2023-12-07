package com.startech;

import java.awt.event.ActionEvent;
// import java.util.Scanner;

/**
 * This is the main class.
 */
public class Main {
    public static void main( String[] args )
    {
        //quick test code - this should be in its own test class so the main class has as few lines of code as possible.
        // in the future, the only lines of code that will be here are those which create and run the GUI.
        Invoker invoker = new Invoker();

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "undo"));  //should forcibly perform an undo command
        // Expected output: "There are no commands to undo!"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "copy"));  //should forcibly perform a copy command
        // Expected output: "COPY COMMAND EXECUTED"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "paste"));  //should forcibly perform a paste command
        // Expected output: "PASTE COMMAND EXECUTED"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "create"));  //should forcibly perform a create command
        // Expected output: "CREATE COMMAND EXECUTED"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "delete"));  //should forcibly perform a delete command
        // Expected output: "DELETE COMMAND EXECUTED"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "move"));  //should forcibly perform a move command
        // Expected output: "MOVE COMMAND EXECUTED"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "undo"));  //should forcibly perform an undo command
        // Expected output: "MOVE COMMAND EXECUTED"

        invoker.actionPerformed(new ActionEvent(new Object(), 0, "undo"));  //should forcibly perform an undo command
        // Expected output: "CREATE COMMAND EXECUTED"

        // Scanner scanner = new Scanner(System.in);

        // //  get the file name to create
        // System.out.println("Enter the file name to create:");
        // String createFileName = scanner.nextLine();
        // Command createCommand = new Create(createFileName);
        // createCommand.execute();

        // //  get the source and destination file names for copying
        // System.out.println("Enter the source file name to copy:");
        // String copySourceFileName = scanner.nextLine();
        // System.out.println("Enter the destination file name for copying:");
        // String copyDestFileName = scanner.nextLine();
        // Command copyCommand = new Copy(copySourceFileName, copyDestFileName);
        // copyCommand.execute();

        // //  get the file name to delete
        // System.out.println("Enter the file name to delete:");
        // String deleteFileName = scanner.nextLine();
        // Command deleteCommand = new Delete(deleteFileName);
        // deleteCommand.execute();

        // // get the source and destination file names for pasting
        // System.out.println("Enter the source file name to paste:");
        // String pasteSourceFileName = scanner.nextLine();
        // System.out.println("Enter the destination file name for pasting:");
        // String pasteDestFileName = scanner.nextLine();
        // Command pasteCommand = new Paste(pasteSourceFileName, pasteDestFileName);
        // pasteCommand.execute();

        // //  get the source and destination file names for moving
        // System.out.println("Enter the source file name to move:");
        // String moveSourceFileName = scanner.nextLine();
        // System.out.println("Enter the destination file name for moving:");
        // String moveDestFileName = scanner.nextLine();
        // Command moveCommand = new Move(moveSourceFileName, moveDestFileName);
        // moveCommand.execute();

        // scanner.close();
    }
}
