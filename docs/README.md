# User Guide
Duke is a Task Manager.

## Features 

### Feature 1 - Tasks
Add, update and delete task.
### Feature 2 - Find Task
Find a task with a keyword.
### Feature 3 - Flexible Data Source
Choose the file for Duke to work on.

## Usage
### 1. Add a todo task - `todo`
Adds a ToDo Task to Duke's list.

Format: `todo <TASK DESCRIPTION>`
#### Example of usage: 
    todo CS2103T iP
    
#### Expected Outcome:
    Got it. I've added this task:
        [T][X] CS2103T iP
    Now you have 1 task in the list.
    
### 2. Add a task with a deadline - `deadline`
Adds a deadline task which has date and time information.

Format: `deadline <TASK DESCRIPTION> /by <DATE AND TIME>`

* `<DATE AND TIME>` is in the format of `dd/MM/yyyy HHmm`.

#### Example of usage: 
    deadline homework /by 11/11/2019 2359
    
#### Expected Outcome:
    Got it. I've added this task:
        [D][X] homework (by: 11/11/2019 2359)
    Now you have 2 tasks in the list.    
    
### 3. Add a task with an event - `event`
Adds an event task which has date and time information.
#### Example of usage: 
Format: `event <TASK DESCRIPTION> /at <DATE AND TIME>`

* `<DATE AND TIME>` is in the format of `dd/MM/yyyy HHmm`.

#### Example of usage: 
    event midterm test /at 04/10/2019 1100
    
#### Expected Outcome:
    Got it. I've added this task:
        [E][X] midterm test (at: 04/10/2019 1100)
    Now you have 3 tasks in the list.

### 4. View Tasks - `list`
Shows a list of tasks.
#### Example of usage: 
    list
    
#### Expected Outcome:
    Now you have 3 tasks in the list.
    1. [T][X] CS2103T iP
    2. [D][X] homework (by: 11/11/2019 2359)
    3. [E][X] midterm test (at: 04/10/2019 1100)
    
### 5. Complete Task - `done`
Marks a task as done.
Format: `delete <INDEX>`

* Duke marks the task at the specified INDEX as done in its list.
* The index refers to the index number shown in the displayed tasks list.
* The index must be a positive integer 1,2,3, ...

#### Example of usage: 
    done 1
    
#### Expected Outcome:
    Nice! I've marked this task as done:
        [T][✔] CS2103T iP
        
### 6. Delete Task - `delete`
Deletes a task.   

Format: delete <INDEX> 

* Duke deletes the task at the specified INDEX in its list.
* The index refers to the index number shown in the displayed tasks list.
* The index must be a positive integer 1,2,3, ...

#### Example of usage: 
    delete 1
    
#### Expected Outcome:
    Noted. I've removed this task:
        [T][✔] CS2103T iP  
    Now you have 2 tasks in the list.
    
### 7. Find tasks - `find`
Finds tasks which share a keyword.
#### Example of usage: 
    find test
    
#### Expected Outcome:
    1 task found with the "test" keyword.
    1. [E][X] midterm test (at: 04/10/2019 1100)
    
### 8. View files - `file list`
Shows the list of files names in the folder.
#### Example of usage: 
    file list
    
#### Expected Outcome:
    You have the following files in this folder.
    1. duke.txt
    You are currently on duke.txt.
    Use file names only (exclude .txt in commands).
    
### 9. Check out file - `file checkout`
Specifies the file for Duke to work on.

Format: `file checkout <FILE NAME>`

* Creates a new file of that name if the file does not already exists.

#### Example of usage: 
    file checkout fluke
    
#### Expected Outcome:
    Checking out fluke.txt!
    Now you have 0 tasks in the list.
   
### 10. Copy file - `file copy`
Copies a file's content into a new file.

Format: `file copy <FILE NAME>`

* The default name of the new file created is `<FILE NAME>-copy`.

#### Example of usage: 
    file copy duke
    
#### Expected Outcome:
    You have copied duke.txt.
    Currently checking out its copy.
    Now you have 2 tasks in the list.
    1. [D][X] homework (by: 11/11/2019 2359)
    2. [E][X] midterm test (at: 04/10/2019 1100)
    
### 11. Rename file - `file rename`
Renames a file.

Format: `file rename <ORIGINAL FILE NAME> /to <UPDATED FILE NAME>`

* Duke checks out the file after it is renamed.

#### Example of usage: 
    file rename duke-copy /to duke2
    
#### Expected Outcome:
    You have renamed duke-copy.txt to duke2.txt.
    You are currently on duke2.txt.
    
### 12. Delete file - `file delete`
Deletes a file.

Format: `delete <FILE NAME>`
    
* Duke cannot delete a file that is being checked out.

#### Example of usage: 
    file delete fluke
    
#### Expected Outcome:
    fluke.txt deleted. 

### 13. Exit Duke - `bye`
Quits the application.
#### Example of usage: 
    bye
    
#### Expected Outcome:
    (Duke application exits)
    
