package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;

import java.util.ArrayList;

public class FileDeleteCommand extends Command {

    private String fileName;

    public FileDeleteCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException {
        ArrayList<String> fileNames = storage.getFileNames();
        if (!(fileNames.contains(String.format("%s.txt", fileName)))) {
            throw new DukeException(String.format("No file named %s to delete.", fileName));
        }
        String currentFilePath = storage.getFilePath();
        if (currentFilePath.equals(String.format("./data/%s.txt", fileName))) {
            throw new DukeException("You can't delete the file you are on.");
        }

        storage.deleteFile(fileName);
        printer.generateFileDeleteMessage(fileName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
