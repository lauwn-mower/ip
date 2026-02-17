package auntie.startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Level 7 task: Save
 * Save the tasks in the hard disk automatically whenever the task list changes.
 * Load the data from the hard disk when the chatbot starts up.
 * You may hard-code the file name and relative path from the project root e.g., ./data/duke.txt
 */

//plan: on startup, check if user got existing file or not --> auntie bad memory but remembers you / meeting u first time --> loaded file then ask quick what yu want do. if "bye" then save file before exiting

// This class assumes that the file is existent. Else, need to create new file outside this class
public class AuntieRetrieveFile {

    public static ArrayList<Task> existingTaskList = new ArrayList<>();

    private static Task convertStringToTask(String taskDescription){
        // Analyse first checkbox to understand Task type
        
        // Analyse second checkbox to understand Task done status

        // Decode task details
    }

    // If file exists, load the contents onto ArrayList<Task> taskList
    private static void loadFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        // Update taskList with contents in existingTaskList
        while (s.hasNext()) {
            for (Task t : existingTaskList) {
                auntie.task.AuntieTasker.taskList.add(s.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        File f = new File("data/auntie.txt");
        //actual main starts here

        // First check if user has an existing file
        // if yes then welcome user back (plus nickname alr set)
        // else create new file and ask nickname
        if (f.exists()) {
            // Welcome user back then load file
            System.out.println("Eh wait Auntie remember u! Come, I bring out ur task list for u.");
            loadFileContents("data/auntie.txt");
        } else {
            // Greet new user and create file
            System.out.println("Oh first time meeting issit. Then we make a tasklist ya");
            File createdFile = new File("data/auntie.txt");

        }

        try {
            loadFileContents("data/auntie.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Ehh the file not here leh?");
        }

    }
}



