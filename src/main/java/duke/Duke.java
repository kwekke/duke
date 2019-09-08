package duke;

import command.Command;
import exception.DukeException;

import static javafx.application.Platform.exit;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this("./data/duke.txt");
    }

    /**
     * Creates an instance of Duke, a task manager.
     * @param filePath a string storing the location of the text file which stores the tasks' data.
     */
    private Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
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
            Command c = Parser.parse(input);
            if (c.isExit()) {
                exit();
            }
            String res = c.execute(tasks, storage);
            assert !res.equals("");
            return res;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns Duke's welcome message.
     * @return Duke's welcome message in a string
     */
    public String getWelcomeMessage() {
        String msg = "Hello from Duke \n";
        msg += "You have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            msg += (i + 1) + "." + tasks.getTask(i) + "\n";
        }
        msg += "\nWhat can i do for you?\n";
        return msg;
    }
}