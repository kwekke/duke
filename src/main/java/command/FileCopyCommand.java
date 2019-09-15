package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileCopyCommand extends Command {

    private String fileName;

    public FileCopyCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException {
        ArrayList<String> fileNames = storage.getFileNames();
        if (!(fileNames.contains(String.format("%s.txt", fileName)))) {
            throw new DukeException(String.format("No file named %s to copy.", fileName));
        }
        String newFileName = String.format("%s-copy", fileName);
        Path sourceFile = Paths.get(String.format("./data/%s.txt", fileName));
        Path targetFile = Paths.get(String.format("./data/%s.txt", newFileName));
        try {
            Files.copy(sourceFile, targetFile);
            storage.updateFileName(newFileName);
            TaskList newTaskList = new TaskList(storage.load());
            printer.generateFileCopyMessage(newTaskList, fileName);
        } catch (IOException e) {
            throw new DukeException("Close your other duke programs.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
