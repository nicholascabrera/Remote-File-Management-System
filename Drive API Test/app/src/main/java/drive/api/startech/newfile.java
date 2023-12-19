
// import com.google.api.client.googleapis.json.GoogleJsonResponseException;
// import com.google.api.client.http.HttpRequestInitializer;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.gson.GsonFactory;
// import com.google.api.services.drive.Drive;
// import com.google.api.services.drive.DriveScopes;
// import com.google.api.services.drive.model.File;
// import com.google.auth.http.HttpCredentialsAdapter;
// import com.google.auth.oauth2.GoogleCredentials;
// import java.io.IOException;
// import java.util.Arrays;

// /* Class to demonstrate use of Drive's create folder API */
// public class CreateFolder {


//   /**
//    * Create new folder.
//    *
//    * @return Inserted folder id if successful, {@code null} otherwise.
//    * @throws IOException if service account credentials file not found.
//    */
//   public static String createFolder() throws IOException {
//     // Load pre-authorized user credentials from the environment.
//     // guides on implementing OAuth2 for your application.
//     GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
//         .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
//     HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
//         credentials);

//     // Build a new authorized API client service.
//     Drive service = new Drive.Builder(new NetHttpTransport(),
//         GsonFactory.getDefaultInstance(),
//         requestInitializer)
//         .setApplicationName("Drive samples")
//         .build();
//     // File's metadata.
//     File fileMetadata = new File();
//     fileMetadata.setName("Test");
//     fileMetadata.setMimeType("application/vnd.google-apps.folder");
//     try {
//       File file = service.files().create(fileMetadata)
//           .setFields("id")
//           .execute();
//       System.out.println("Folder ID: " + file.getId());
//       return file.getId();
//     } catch (GoogleJsonResponseException e) {
//       System.err.println("Unable to create folder: " + e.getDetails());
//       throw e;
//     }
//   }
// }




// /* Class to demonstrate use case for moving file to folder.*/
// public class MoveFileToFolder {


//   /**
//    * @param fileId   Id of file to be moved.
//    * @param folderId Id of folder where the fill will be moved.
//    * @return list of parent ids for the file.
//    */
//   public static List<String> moveFileToFolder(String fileId, String folderId)
//       throws IOException {
//         /* Load pre-authorized user credentials from the environment.
//         guides on implementing OAuth2 for your application.*/
//     GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
//         .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
//     HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
//         credentials);
//     // Build a new authorized API client service.
//     Drive service = new Drive.Builder(new NetHttpTransport(),
//         GsonFactory.getDefaultInstance(),
//         requestInitializer)
//         .setApplicationName("Drive samples")
//         .build();

//     // Retrieve the existing parents to remove
//     File file = service.files().get(fileId)
//         .setFields("parents")
//         .execute();
//     StringBuilder previousParents = new StringBuilder();
//     for (String parent : file.getParents()) {
//       previousParents.append(parent);
//       previousParents.append(',');
//     }
//     try {
//       // Move the file to the new folder
//       file = service.files().update(fileId, null)
//           .setAddParents(folderId)
//           .setRemoveParents(previousParents.toString())
//           .setFields("id, parents")
//           .execute();

//       return file.getParents();
//     } catch (GoogleJsonResponseException e) {
//       System.err.println("Unable to move file: " + e.getDetails());
//       throw e;
//     }
//   }
 
//   //copy file
//  const oldFolderId = <something>
// const newFolderId = <something else>
// async file => {
// // Create Copy of File
//   const cloned = (await gapi.client.drive.files.copy({
//    fileId: file.id
//   })).result
  
//   // Move copy to new folder
//   await gapi.client.drive.files.update({
//    fileId: cloned.id,
//    addParents: newFolderId,
//    removeParents: oldFolderId,
//    resource: { name: file.name },
//    fields: 'id, parents'
//   })
// }


// //copy folder 
// async function cloneFolder(from, to){
//  // Create new folder
//  const newFolder = (await gapi.client.drive.files.create({
//   resource: {
//     name: from.name,
//     mimeType: 'application/vnd.google-apps.folder',
//     parents: [to.id]
//   }
//  })).result
//  // Find all sub-folders
//  const folders = (await gapi.client.drive.files.list({
//   q: `'${from.id}' in parents and mimeType =  'application/vnd.google-apps.folder' and trashed = false`,
//   pageSize: 100,
//   fields: 'nextPageToken, files(id, name)'
//  })).result.files
//  // Find all files 
//  const files = (await gapi.client.drive.files.list({
//   q: `'${from.id}' in parents and mimeType !=   'application/vnd.google-apps.folder' and trashed = false`,
//   pageSize: 100,
//   fields: 'nextPageToken, files(id, name)'
//  })).result.files
//  files.forEach(async file => {
//   // Create Copy of File
//   const cloned = (await gapi.client.drive.files.copy({
//     fileId: file.id
//   })).result
//   // Move copy to new folder
//   await gapi.client.drive.files.update({
//     fileId: cloned.id,
//     addParents: newFolder.id,
//     removeParents: from.id,
//     resource: { name: file.name },
//     fields: 'id, parents'
//   })
//  })
//  // Recursion
//  folders.forEach(folder => cloneFolder(folder, newFolder, false))
// }