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
     * Initializes Storage and ensures that a default .txt file exists.
     */
    public Storage() {
        if (!doesFileExist()) {
            createFile();
        }
    }

    private boolean doesFileExist() {
        return Paths.get(filePath).toFile().exists();
    }

    private void createFile() {
        try {
            Path parentDirectory = Paths.get(filePath).getParent();
            Files.createDirectories(parentDirectory);
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
            System.out.println(e.getMessage());
        }
        return readTasksFromFile();
    }

    private ArrayList<Task> readTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String token = sc.nextLine();
            String[] tokenSplit = token.split("\\|\\|");
            try {
                Task task = readLine(token, tokenSplit);
                if (tokenSplit[1].trim().equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
        sc.close();
        return tasks;
    }

    private Task readLine(String tokenString, String[] token) throws DukeException {
        String time;
        switch (token[0].trim()) {
        case "T":
            return new ToDoTask(token[2].trim());
        case "D":
            time = token[3];
            return new DeadlineTask(token[2].trim(), time);
        case "E":
            time = token[3];
            return new EventTask(token[2].trim(), time);
        default:
            throw new DukeException("Corrupted file. Bad line is " + tokenString);
        }

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
            System.out.println(e.getMessage());
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
        File[] listOfFiles = fileDirectory.listFiles();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            listOfFileNames.add(file.getName());
        }
        return listOfFileNames;
    }

    /**
     * Deletes a file in the current directory with the given file name.
     * @param deleteFileName the name of the file to be deleted.
     */
    public void deleteFile(String deleteFileName) throws DukeException {
        assert !filePath.equals(fileNameToFilePath(deleteFileName));
        Path deleteFilePath = Paths.get(fileNameToFilePath(deleteFileName));
        try {
            Files.deleteIfExists(deleteFilePath);
        } catch (IOException e) {
            throw new DukeException("File does not exist.");
        }
    }
}
