package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;

import java.util.ArrayList;

public class FileListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Printer printer) {
        ArrayList<String> listOfFileNames = storage.getFileNames();
        printer.generateFileListMessage(listOfFileNames, storage.getFilePath());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
