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

public class FileRenameCommand extends Command {

    private String oldFileName;
    private String newFileName;

    public FileRenameCommand(String oldFileName, String newFileName) {
        this.oldFileName = oldFileName;
        this.newFileName = newFileName;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException {
        ArrayList<String> fileNames = storage.getFileNames();
        if (!(fileNames.contains(String.format("%s.txt", oldFileName)))) {
            throw new DukeException(String.format("No file named %s to rename.", oldFileName));
        }
        if (fileNames.contains(String.format("%s.txt", newFileName))) {
            throw new DukeException(String.format("A file name of %s already exists.", newFileName));
        }
        Path sourceFile = Paths.get(String.format("./data/%s.txt", oldFileName));
        Path targetFile = Paths.get(String.format("./data/%s.txt", newFileName));
        try {
            Files.move(sourceFile, targetFile);
            storage.updateFileName(newFileName);
            printer.generateFileRenameMessage(oldFileName, newFileName);
        } catch (IOException e) {
            throw new DukeException("Cannot rename file.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
