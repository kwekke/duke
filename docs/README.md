# User Guide
Duke is a Task Manager.

## Features 

### Feature 1 - Tasks
Add, update and delete task.
### Feature 2 - Search Task
Find a task with a keyword.
### Feature 3 - Flexible Data Source
Choose the file for Duke to work on.

## Usage
### `todo` - Add task
Add a ToDo Task to Duke's list.
#### Example of usage: 
    todo (description of todo task)
#### Expected Outcome:
    Got it. I've added this task:
        [T][X] (description of todo task) 
    Now you have <n> tasks in the list.
    
### `deadline`/`event` - Add Special Tasks
Specify the date and time for tasks with deadlines and events.
#### Example of usage: 
For deadlines,

    deadline (description of deadline task) /by (date and time)

For events,

    event (description of event task) /at (date and time)

where `date and time` is in the format of `dd/MM/yyyy HHmm`.
#### Expected Outcome:
    Got it. I've added this task:
        [D][X] (description of deadline task) (date and time)
    Now you have <n> tasks in the list.

### `list` - View Tasks
View the list of tasks Duke is keeping track of.
#### Example of usage: 
    list
#### Expected Outcome:
    Now you have <n> tasks in the list.
    (Tasks in the list)
    
### `done` - Complete Task
Mark the task as done.
#### Example of usage: 
    done (task number)
#### Expected Outcome:
    Nice! I've marked this task as done:
        (task that marked as done)
        
### `delete` - Delete Task    
Delete a task.   
#### Example of usage: 
    delete (task number)
#### Expected Outcome:
    Noted. I've removed this task:
        (task that marked as done)  
    Now you have <n> tasks in the list.
    
### `find` - Find tasks
Find tasks which share a keyword.
#### Example of usage: 
    find (keyword)
#### Expected Outcome:
    <n> tasks found with this (keyword)
    (Tasks found with keyword)
    
### `file list` - View files
View the list of files in the folder.
#### Example of usage: 
    file list
#### Expected Outcome:
    You have the following files in this folder.
    (Files in this folder) 
    You are currently on <name of file Duke is on>.
    Use file names only (exclude .txt in commands).
    
### `file checkout` - Checkout file
Specify the file for Duke to work on.
#### Example of usage: 
    file checkout (name of file)
#### Expected Outcome:
    Checking out (name of file)!
    Now you have <n> tasks in the list.
    (Tasks in the list)
    
#### Notes:
The command `file checkout (name of file)` creates a new file of that name if the file does not already exists.
### `file copy` - Copy file
Copies a file's content into a new file.
#### Example of usage: 
    file copy (name of file)
#### Expected Outcome:
    You have copied (name of file).
    Currently checking out its copy.
    Now you have <n> tasks in the list.
    (Tasks in the list)
    
### `file rename` - Rename file
Rename a file.
#### Example of usage: 
    file rename (current name of file) /to (new name of file)
#### Expected Outcome:
    You have renamed (current name of file) to (new name of file).
    You are currently on (new name of file).
    
### `file delete` - Delete file
Delete a file.
#### Example of usage: 
    file delete (name of file)
#### Expected Outcome:
    (name of file deleted) deleted. 
    
#### Notes:
You cannot rename or delete the file Duke is currently checking out.     

### `bye` - Exit Duke
Quit the application.
#### Example of usage: 
    bye
#### Expected Outcome:
    (Duke application exits)
    