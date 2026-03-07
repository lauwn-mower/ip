# AuntieBot User Guide
### *Your Rabak Life Manager*

Auntie is a CLI (Command Line Interface) task manager (spiced with a tinge of Singlish) that helps you keep your todo-list "steady pom pi pi." 
---

## Quick Start

1. Ensure you have **Java JDK 17** installed on your computer.
2. Download the latest `Auntie.jar` from the [Releases](https://github.com/lauwn-mower/ip/releases) page.
3. Copy the file to the folder you want to use as the **home folder** for your task manager.
4. Open a command window (Terminal/Command Prompt) in that folder and run the command:
   ```text
   java -jar Auntie.jar
   ```
   
## Features
### Help feature
Displays a comprehensive list of all available commands and their correct usage formats.

Command: `help`

Output:
   ```text
   Mai kanchiong, Auntie will help you.
Here is what Auntie can do for you:

**VIEWING TASKS**
  list           - See ur whole task list.
  find <keyword> - Show u the tasks containing ur keyword.
...
```

### Adding tasks
Adds a new task (Todo, Deadline, or Event) to Auntie's notebook.

Formats:
`todo <description>`
`deadline <description> by <time>`
`event <description from <start> to <end>`

Example: `event cs2113 lecture from friday 4pm to 6pm`

Expected output:
   ```text
Ok, added liao:
  [E][ ] cs2113 lecture (from friday 6pm)
Now u got 3 things to do hor.
```

### Deleting tasks
Removes an existing task from the list using its specific index number.

Format: `delete <index>`


### View task list:
Displays all the tasks currently in your list, showing their completion status, type, and any associated timings.


### Find task(s)
Searches for tasks in the list that contain the specified keyword in their description.

Format: `find <description>`


### Autosave on local device as txt.file
Ensures your progress is never lost by saving data locally.

Feature Details:
Every time a command modifies the task list (add, delete, mark, or unmark), Auntie automatically updates a tasks.txt file located in the /data folder of your home directory. When you restart the application, Auntie automatically detects the file and loads your tasks.



