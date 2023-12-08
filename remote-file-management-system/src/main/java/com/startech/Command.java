package com.startech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;
import java.awt.Component; 
import java.io.File;

// Abstract class representing a file operation
abstract class Command {
    protected String fileName; // Variable to store the file name

    public Command(String fileName) { // Constructor to initialize the fileName variable
        this.fileName = fileName;
    }

    abstract CommandE getEnum();

    public String getFileName() { // Getter method for the fileName variable
        return fileName;
    }

    // Method to execute the operation
    public void execute() { 
        File file = new File(fileName); // Create a new File object with the specified fileName
        try {
            performOperation(file); // Perform operation on the file
        } catch (IOException e) {
            System.out.println("An error occurred while performing the operation."); // Print an error message if an IOException occurs
        }
    }

    // Abstract method to perform the specific file operation
    abstract void performOperation(File file) throws IOException;
}

// Child class representing the Create operation
class Create extends Command {
    private final CommandE commandEnum = CommandE.CREATE;

    public Create(String[] arguments) { // Constructor to call the parent class constructor and initialize the fileName variable
        super(arguments[0]);    //the first element of the arguments array will be the fileName or the source file.
        if(arguments.length != 1){
            try {
                throw new Exception("Not the correct number of arguments! Create only takes one argument: the file name!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    // Implementation of performOperation method for Create class
    @Override
    protected void performOperation(File file) throws IOException {
        if (file.createNewFile()) { // Create a new file using the createNewFile() method of the File class
            System.out.println("File created: " + file); // Print a success message if the file is created
        } else {
            System.out.println("File already exists."); // Print an error message if the file already exists
        }
    }
}

// Child class representing the Copy operation
class Copy extends Command {
    private final CommandE commandEnum = CommandE.COPY;
    private String destination; // Variable to store the destination file name

    public Copy(String[] arguments) { // Constructor to call the parent class constructor and initialize the source and destination variables
        super(arguments[0]);
        if(arguments.length != 2){
            try {
                throw new Exception("Not the correct number of arguments! Copy takes two arguments: the source file name and the destination!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        this.destination = arguments[1];
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    public String getDestination() { // Getter method for the destination variable
        return destination;
    }

    // Implementation of performOperation method for Copy class
    @Override
    protected void performOperation(File file) throws IOException {
        File destFile = new File(destination); // Create a new File object with the specified destination file name
        Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING); // Copy the source file to the destination file using the copy() method of the Files class
        System.out.println(file.getName() + " has been copied to " + destFile.getPath()); // Print a success message
    }
}

// Child class representing the Delete operation
class Delete extends Command {
    private final CommandE commandEnum = CommandE.DELETE;

    public Delete(String[] arguments) { // Constructor to call the parent class constructor and initialize the fileName variable
        super(arguments[0]);
        if(arguments.length != 1){
            try {
                throw new Exception("Not the correct number of arguments! Delete only takes one argument: the file name!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    // Implementation of performOperation method for Delete class
    @Override
    protected void performOperation(File file) throws IOException {
        int option = JOptionPane.showConfirmDialog((Component)null, "Are you sure you want to delete the file?", // Show a confirmation dialog box using the showConfirmDialog() method of the JOptionPane class
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) { // Check if the user chooses the Yes option
            if (file.delete()) { // Delete the file using the delete() method of the File class
                System.out.println("File deleted: " + file); // Print a success message if the file is deleted
            } else {
                System.out.println("File could not be deleted."); // Print an error message if the file could not be deleted
            }
        } else {
            System.out.println("File deletion canceled."); // Print a message if the user chooses the No option
        }
    }
}

// Child class representing the Paste operation
class Paste extends Command {
    private final CommandE commandEnum = CommandE.PASTE;
    private String destination; // Variable to store the destination file name

    public Paste(String[] arguments) { // Constructor to call the parent class constructor and initialize the sourceFile and destinationFile variables
        super(arguments[0]);
        if(arguments.length != 2){
            try {
                throw new Exception("Not the correct number of arguments! Paste takes two arguments: the source file name and the destination!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        this.destination = arguments[1];
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    public String getDestination() {
        return destination;
    }

    // Implementation of performOperation method for Paste class
    @Override
    protected void performOperation(File file) throws IOException {
        File source = new File(fileName); // Create a new File object with the specified source file name
        File destination = new File(file.getPath()); // Create a new File object with the path of the destination folder

        if (source.exists()) { // Check if the source file exists
            try {
                Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING); // Copy the source file to the destination folder using the copy() method of the Files class
                System.out.println("File pasted to:\t" + this.destination); // Print a success message
            } catch (IOException e) {
                System.out.println("An error occurred while copying the file."); // Print an error message if an IOException occurs during the copy operation
            }
        } else {
            System.out.println("Source file does not exist."); // Print an error message if the source file does not exist
        }
    }
}

// Child class representing the Move operation
class Move extends Command {
    private String destination; // Variable to store the destination file name
    private final CommandE commandEnum = CommandE.MOVE;

    public Move(String[] arguments) { // Constructor to call the parent class constructor and initialize the source and destination variables
        super(arguments[0]);
        if(arguments.length != 2){
            try {
                throw new Exception("Not the correct number of arguments! Move takes two arguments: the source file name and the destination!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        this.destination = arguments[1];
    }

    public CommandE getEnum() {
        return commandEnum;
    }

    public String getDestination() { // Getter method for the destination variable
        return destination;
    }

    // Implementation of performOperation method for Move class
    @Override
    protected void performOperation(File file) throws IOException {
        File destFile = new File(destination); // Create a new File object with the specified destination file name
        Files.move(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING); // Move the source file to the destination file using the move() method of the Files class
        System.out.println(file.getName() + " has been moved to " + destFile.getPath()); // Print a success message
    }
}