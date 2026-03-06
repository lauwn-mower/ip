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
        //TODO: loadfile
    }

    public void saveFile(TaskList taskList) throws IOException, IOException {
        //TODO: savefile
    }

}
