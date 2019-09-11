package command;

import duke.Printer;
import duke.Storage;
import exception.DukeException;
import duke.TaskList;


public class FileCheckOutCommand extends Command {

    private String fileName;

    public FileCheckOutCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException {
        storage.updateFileName(fileName);
        TaskList newTaskList = new TaskList(storage.load());
        printer.generateFileCheckOutMessage(newTaskList, fileName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}