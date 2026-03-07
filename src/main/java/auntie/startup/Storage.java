package auntie.startup;

import static auntie.startup.FileRetriever.decodeFileString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import auntie.task.AuntieException;
import auntie.task.Task;
import auntie.task.TaskList;

/**
 * Handles the loading and saving of task data to a local file.
 * Acts as the bridge between the application's TaskList and the persistent storage
 * on the hard drive.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file and returns them as an ArrayList.
     * Processes each line of the file using a decoder to reconstruct Task objects.
     * * @return An ArrayList of Task objects retrieved from the file.
     * @throws AuntieException If the file cannot be found or accessed.
     */
    public ArrayList<Task> loadFile() throws AuntieException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File f = new File(this.filePath);

        if (!f.exists()) {
            throw new AuntieException("File doesn't exist leh");
        }

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                // Use your decode helper to turn the text line into a Task object
                decodeFileString(s.nextLine(), loadedTasks);
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new AuntieException("Cannot find the file!");
        }

        return loadedTasks;    }

    /**
     * Saves the current list of tasks to the data file.
     * Iterates through the TaskList and writes each task using its specific
     * toFileFormat documentation.
     * * @param taskList The TaskList containing the tasks to be saved.
     * @throws IOException If an error occurs during the file writing process.
     */
    public void saveFile(TaskList taskList) throws IOException, IOException {
        int numberOfTasks = taskList.getSize();
        File updatedFile = new File(this.filePath);

        // Creates directory if it is missing
        if (updatedFile.getParentFile() != null) {
            updatedFile.getParentFile().mkdirs();
        }

        // Writes taskList items to the file using the Task's toFileFormat()
        FileWriter fileWriter = new FileWriter(updatedFile);
        for (int i = 0; i < numberOfTasks; i += 1) {
            Task t = taskList.getTask(i);
            fileWriter.write(t.toFileFormat() + System.lineSeparator());
        }

        fileWriter.close();
    }

}
