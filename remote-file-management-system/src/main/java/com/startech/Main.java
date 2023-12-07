package com.startech;

import java.awt.event.ActionEvent;

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
    }
}
