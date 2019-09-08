package duke;

import command.Command;
import exception.DukeException;

import static javafx.application.Platform.exit;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Printer printer;

    public Duke() {
        this("./data/duke.txt");
    }

    /**
     * Creates an instance of Duke, a task manager.
     * @param filePath a string storing the location of the text file which stores the tasks' data.
     */
    private Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.printer = new Printer();
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns Duke's response after a user's input is parsed.
     * @param input user's input
     * @return Duke's output
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            c.execute(tasks, storage, printer);
            if (c.isExit()) {
                exit();
            }
            return printer.generateResponse();
        } catch (DukeException e) {
            return printer.generateExceptionMessage(e.getMessage());
        }
    }

    /**
     * Returns Duke's welcome message.
     * @return Duke's welcome message in a string
     */
    public String getWelcomeMessage() {
        printer.generateWelcomeMessage(tasks);
        return printer.generateResponse();
    }
}