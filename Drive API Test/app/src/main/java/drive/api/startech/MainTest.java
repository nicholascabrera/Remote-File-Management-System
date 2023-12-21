// package drive.api.startech;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import java.util.zip.ZipFile;

// import java.awt.event.ActionEvent;
// import java.io.File;
// import java.io.IOException;
// import java.nio.channels.FileLock;

// import static org.junit.jupiter.api.Assertions.*;

// class MainTest {
//     @Test
//     public void testCreateFileWithoutName(){
//         try {
//             File file = new File("");
//             file.createNewFile();
//             fail("Expected IOException");
//         } catch (IOException e) {
//             assertTrue(e.getMessage().contains("No such file or directory"));
//         }
//     }@Test
//     public void testCreateFileWithSpecialCharacterInName(){
//         try{

//             File file = new File("file_with_special_character!.txt");
//             file.createNewFile();
//             assertTrue(file.exists()); 
//         } catch (IOException e) {
//             fail("Unexpected IOException: " + e.getMessage());
//         }
//     }@Test
//     public void testCreateFileWithExistingName(){
//         try{
//             File existingFile = File.createTempFile("existingFile", ".txt");
//             File file = new File(existingFile.getAbsolutePath());
//             file.createNewFile();
//             fail("Expected IOException");
//         } catch (IOException e) {
//             assertTrue(e.getMessage().contains("File already exists"));
//         }
//     }@Test
//     public void testCreateAzipFile(){
//         try {
//             File file = new File("file_protected.zip");
//             File.createTempFile("existingFile", "zip");
//             assertTrue(file.exists());
//         } catch (IOException e){
//             Object fail = fail("Unexpected IOException: " + e.getMessage());
//         }
//     } @Test
//     public void testFileWithoutaSuffix() {
//         try {
//             File file = new File("file_without_a_suffix");
//             File.createTempFile("existingFile", null);
//             assertTrue(file.exists());
//         } catch (IOException e) {
//             Object fail = fail("Unexpected IOException: " + e.getMessage());
//         }
//     }
// }
