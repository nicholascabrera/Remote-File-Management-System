package drive.api.startech;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.security.GeneralSecurityException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* class to demonstrate use of Drive files list API */
public class DriveQuickstart {
  /**
   * CommandHistory Instance.
   */
  public CommandHistory commandHistory;

  /**
   * Application name.
   */
  private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
  /**
   * Global instance of the JSON factory.
   */
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  /**
   * Directory to store authorization tokens for this application.
   */
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES =
      Collections.singletonList(DriveScopes.DRIVE);
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

  private static CommandWindow window;

  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = DriveQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    //returns an authorized Credential object.
    return credential;
  }

  public void driveStart() throws IOException, GeneralSecurityException{
    // instantiate the invoker.
    this.commandHistory = new CommandHistory();

    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();
    
    /**
     * DONE(PM) - link GUI to drive by passing the files and their child files/folders.
     */
    createAndRunGUI(service, commandHistory);
  }

  public static CommandPanel setupFileHierarchy(Drive service) throws IOException{
    /**
     * I need to get all the folders in order to determine the root ID and so I know what I need to iterate through.
     */
    List<File> folders = new ArrayList<File>();
    String pageToken = null;
    do{
    // pulls the names, IDs, and parent folders for all files and folders.
      FileList result = service.files().list()
          .setQ("mimeType = 'application/vnd.google-apps.folder'")
          .setSpaces("drive")
          .setFields("nextPageToken, files(id, name, parents, mimeType)")
          .setPageToken(pageToken)
          .execute();

      folders.addAll(result.getFiles());
      pageToken = result.getNextPageToken();
    } while (pageToken != null);
    
    String rootID = (folders.size() == 1) ? folders.get(0).getParents().get(0) : "";
    if(rootID.equals("")){
      for(File folder : folders){
        //not needed at the moment
        //DONE(PM) - Complete this algorithm for finding the root folder when there is more than one folder.
        for(File file : folders){
          if(folder.getParents() != null){
            if(folder.getParents().get(0) == file.getId()){
              rootID = "";
              break;
            } else {
              rootID = folder.getParents().get(0);
            }
          }
        }
      }
    }

    /**
     * I need all the files, including the folders, so I can categorize everything.
     */
    List<File> files = new ArrayList<File>();
    pageToken = null;
    do{
    // pulls the names, IDs, and parent folders for all files and folders.
      FileList result = service.files().list()
          .setQ("")
          .setSpaces("drive")
          .setFields("nextPageToken, files(id, name, parents, mimeType)")
          .setPageToken(pageToken)
          .execute();

      files.addAll(result.getFiles());
      pageToken = result.getNextPageToken();
    } while (pageToken != null);

    CommandPanel rootFolder = new CommandPanel(service);
    rootFolder.buildRootPanel(new ArrayList<CommandPanel>(), rootID);

    /**
     * add the folders with root folder as their parent to root folder's children list
     */
    for(File folder : folders){
      if(folder.getParents() != null){
        String parentID = folder.getParents().get(0);
        if (parentID.equals(rootID)) {
          rootFolder.addChild(new CommandPanel(service, folder, new ArrayList<CommandPanel>(), rootFolder));
        }
      }
    }

    for(File folder : folders){
      CommandPanel currentFolder = new CommandPanel(service, folder, new ArrayList<CommandPanel>(), rootFolder);
      for(CommandPanel panel : rootFolder.getChildPanels()){
        if(panel.getFile().getId().equals(folder.getId())){
          currentFolder.setChildPanels(panel.getChildPanels());
        }
      }
      for(File file : files){
        if(file.getParents() != null){
          String parentID = file.getParents().get(0);
          if (parentID.equals(rootID) && !rootFolder.containsChild(file.getId())) {
            //the parent folder of this file is the root.
            rootFolder.addChild(new CommandPanel(service, file, new ArrayList<CommandPanel>(), rootFolder));
          } else if (parentID.equals(currentFolder.getFile().getId())) {
            //the parent folder of this file is the current folder.
            currentFolder.addChild(new CommandPanel(service, file, new ArrayList<CommandPanel>(), currentFolder));
          } else {
            //the parent folder of this file is neither the root nor the current folder.
          }
        }
      }
    }

    return rootFolder;
  }

  public static CommandWindow getOldWindow(){
    return window;
  }

  public static void createAndRunGUI(Drive service, CommandHistory commandHistory) throws IOException{
    window = new CommandWindow();
    CommandPanel rootFolder = setupFileHierarchy(service);
    window.setRootPanel(rootFolder);
    window.setup(service, rootFolder, commandHistory);
    window.execute();
  }

  public static void createAndRunGUI(Drive service, CommandHistory commandHistory, CommandWindow oldWindow) throws IOException{
    window = oldWindow;
    CommandPanel rootFolder = setupFileHierarchy(service);
    window.setRootPanel(rootFolder);
    window.setup(service, rootFolder, commandHistory);
    window.execute();
  }

  public static void main(String... args) throws IOException, GeneralSecurityException {
    new DriveQuickstart().driveStart();
  }
}