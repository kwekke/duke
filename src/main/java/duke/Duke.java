package duke;

import command.Command;
import exception.DukeException;

import static javafx.application.Platform.exit;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Printer printer;

    public Duke() {
        initialize();
    }

    private void initialize() {
        try {
            this.storage = new Storage();
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
            tasks = new TaskList(storage.load());
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