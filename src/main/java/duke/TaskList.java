package duke;

import task.Task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Creates a TaskList which stores Tasks.
     * @param tasks an ArrayList of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void doneTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Returns a string for printing a list of all tasks to the user.
     * @return a string of all tasks in the list.
     */
    public String toString() {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;
    }
}
