package drive.api.startech;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.util.Collections;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

public class UploadToFolder {
    /**
     * Creates a new file in Google Drive.
     *
     * @param driveService Initialized Drive service object.
     * @param filename     Name of the file.
     * @param mimeType     Mime type of the file.
     * @param folder       the folder where the file will be created.
     * @return The newly created file metadata if successful; otherwise, null.
     * @throws IOException if service account credentials file not found.
     */
    public static void createFile(Drive driveService, String filename, String mimeType, CommandPanel folder)
            throws IOException {
        try {
            File fileMetadata = new File();
            fileMetadata.setName(filename);
            fileMetadata.setMimeType(mimeType);
            fileMetadata.setParents(Collections.singletonList(folder.getID()));

            File createdFile = driveService.files().create(fileMetadata)
                    .setFields("id, mimeType, parents")
                    .execute();

            System.out.println("Created File ID: " + createdFile.getId());
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to create file: " + e.getDetails());
            throw e;
        }
    }

    /**
     * Delete a file from Google Drive.
     *
     * @param driveService Initialized Drive service object.
     * @param fileId       Id of the file to delete.
     * @throws IOException if service account credentials file not found.
     */
    public static void deleteFile(Drive driveService, String fileId) throws IOException {
        try {
            driveService.files().delete(fileId).execute();
            System.out.println("File deleted successfully.");
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to delete file: " + e.getDetails());
            throw e;
        }
    }

    /**
     * Paste a file into a destination folder.
     *
     * @param driveService        Initialized Drive service object.
     * @param fileId              Id of the file to paste.
     * @param destinationFolderId Id of the destination folder.
     * @return The pasted file metadata if successful; otherwise, null.
     * @throws IOException if service account credentials file not found.
     */
    public static void pasteFileToFolder(Drive driveService, File fileToPaste, String destinationFolderId)
            throws IOException {
        try {
            // Create a new copy with the destination folder as the parent
            fileToPaste.setParents(Collections.singletonList(destinationFolderId));
            File pastedFile = driveService.files().copy(fileToPaste.getId(), fileToPaste)
                    .setFields("id, parents")
                    .execute();

            System.out.println("Pasted File ID: " + pastedFile.getId());
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to paste file: " + e.getDetails());
            throw e;
        }
    }

    /**
     * Move a file to the specified destination folder.
     *
     * @param driveService        Initialized Drive service object.
     * @param fileId              Id of the file to move.
     * @param destinationFolderId Id of the destination folder.
     * @return Moved file metadata if successful; otherwise, null.
     * @throws IOException if service account credentials file not found.
     */
    public static void moveFileToFolder(Drive driveService, String fileId, String destinationFolderId)
            throws IOException {
        try {
            File fileToMove = driveService.files().get(fileId)
                    .setFields("id, parents")
                    .execute();

            String originalParents = fileToMove.getParents().get(0);
            fileToMove.setParents(Collections.singletonList(destinationFolderId));
            fileToMove = driveService.files().update(fileToMove.getId(), null)
                    .setAddParents(destinationFolderId)
                    .setRemoveParents(originalParents)
                    .setFields("id, parents")
                    .execute();

            System.out.println("Moved File ID: " + fileToMove.getId());
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to move file: " + e.getDetails());
            throw e;
        }
    }
}