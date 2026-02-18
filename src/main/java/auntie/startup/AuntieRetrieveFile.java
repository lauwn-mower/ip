package auntie.startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import auntie.task.AuntieException;
import auntie.task.Task;
import auntie.task.Todo;
import auntie.task.Deadline;
import auntie.task.Event;

public class AuntieRetrieveFile {

    public static final String FILE_PATH = "data/auntie.txt";

    // Create and initialise an ArrayList<Task> based on txt file
    public static ArrayList<Task> loadFileContents() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File f = new File(FILE_PATH);

        try {
            if (f.exists()) {
                System.out.println("Eh wait Auntie remember u! Come, I bring out ur task list for u");
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    // Pass on the line to decode
                    decodeFileString(s.nextLine(), loadedTasks);
                }
                s.close();
            } else {
                System.out.println("Oh first time meeting issit. I go find a notebook for you first ah");

                // Create the folder and file
                f.getParentFile().mkdirs(); // Create the "data" folder
                f.createNewFile();          // Create "auntie.txt"
            }
        } catch (IOException e) {
            System.out.println("Aiyo! Auntie cannot find or make the file sia" + e.getMessage());
        }

        return loadedTasks;
    }

    // Assumes machine-readable format: taskType | taskDoneStatus | taskName | Others
    private static void decodeFileString(String fileLine, ArrayList<Task> loadedTasks) {
        // Details are split by " | "
        String[] parts = fileLine.split(" \\| ");

        // Error handling: skip empty lines
        if (parts.length < 3) {
            return;
        }

        String taskType = parts[0];
        boolean taskDoneStatus = parts[1].equals("1");
        String taskDescription = parts[2];

        Task newTask = null;

        switch (taskType) {
        case "T":
            newTask = new Todo(taskDescription);
            break;
        case "D":
            if (parts.length >= 4) newTask = new Deadline(taskDescription, parts[3]);
            break;
        case "E":
            if (parts.length >= 5) newTask = new Event(taskDescription, parts[3], parts[4]);
            break;
        default:
            System.out.println("Eh what is this weird task in the file?");
        }

        // If task object successfully created, set taskDoneStatus and add it to ArrayList<Task>
        if (newTask != null) {
            newTask.setDone(taskDoneStatus);
            loadedTasks.add(newTask);
        }
    }

    // This method loops to convert and save every task onto the local file
    public static void saveFileContents(ArrayList<Task> taskList) {
        try {
            // Overwrite the file every time
            FileWriter fw = new FileWriter(FILE_PATH);

            for (Task t : taskList) {
                // Format each task then add new line
                fw.write(t.toFileFormat() + System.lineSeparator());
            }

            // Save the file
            fw.close();

        } catch (IOException e) {
            System.out.println("Aiyo, Auntie cannot write to the notebook! " + e.getMessage());
        }
    }
}