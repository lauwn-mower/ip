package auntie.startup;

// TODO: add JavaDoc header comments
// This class is able to:
// load() the (existing) user's last tasklist from local file
// save() the user's update

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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
     * Section: Main Storage methods of load() and save()
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

    public void saveFile(TaskList taskList) throws IOException, IOException {
        int numberOfTasks = taskList.getSize();
        File updatedFile = new File(this.filePath);

        // If file missing, create new directory
        if (updatedFile.getParentFile() != null) {
            updatedFile.getParentFile().mkdirs();
        }

        // Open FileWriter with the filePath
        // Write taskList items to updateFile
        FileWriter fileWriter = new FileWriter(updatedFile);
        for (int i = 0; i < numberOfTasks; i += 1) {
            Task t = taskList.getTask(i);
            fileWriter.write(t.toFileFormat() + System.lineSeparator());
        }

        fileWriter.close();
    }

}
