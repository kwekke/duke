package duke;

import exception.DukeException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath = "./data/duke.txt";
    private Scanner sc;

    /**
     * Initialises a storage object which handles with loading tasks from the file
     * and saving tasks in the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {

    }

    /**
     * Loads a list of tasks in the text file.
     * @return an ArrayList of tasks.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        try {
            file.createNewFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String tokenString = sc.nextLine();
            String[] token = tokenString.split("\\|\\|");
            String time;
            Task task;
            switch (token[0].trim()) {
            case "T":
                task = new ToDoTask(token[2].trim());
                break;
            case "D":
                time = token[3];
                task = new DeadlineTask(token[2].trim(), time);
                break;
            case "E":
                time = token[3];
                task = new EventTask(token[2].trim(), time);
                break;
            default:
                throw new DukeException("Corrupted file. Bad line is " + tokenString);
            }
            tasks.add(task);
            if (token[1].trim().equals("1")) {
                task.markAsDone();
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves tasks in the text file given a TaskList.
     * @param tasks TaskList to be saved into the text file.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks.getList()) {
                String s = task.toFileString() + "\n";
                fileWriter.write(s);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fileNameToFilePath(String fileName) {
        return String.format("./data/%s.txt", fileName);
    }

    public String getFilePath() {
        return filePath;
    }

    public void updateFileName(String fileName) {
        this.filePath = fileNameToFilePath(fileName);
    }

    /**
     * Returns a list of file names.
     * @return a list of file names.
     */
    public ArrayList<String> getFileNames() {
        ArrayList<String> listOfFileNames = new ArrayList<>();
        File fileDirectory = (new File(filePath)).getParentFile();
        for (File file : fileDirectory.listFiles()) {
            listOfFileNames.add(file.getName());
        }
        return listOfFileNames;
    }

    /**
     * Deletes a file in the current directory with the given file name.
     * @param deleteFileName the name of the file to be deleted.
     */
    public void deleteFile(String deleteFileName) {
        assert !filePath.equals(fileNameToFilePath(deleteFileName));
        Path deleteFilePath = Paths.get(fileNameToFilePath(deleteFileName));
        try {
            Files.deleteIfExists(deleteFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
