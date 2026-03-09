# Lego – Personal Task Manager Chatbot

Chatbot name: Lego
(Lego is a simple command-line chatbot that helps you keep track of your tasks.)

This repository contains the skeleton of the Lego project used in CS2113-T.
The bot accepts a small set of text commands, stores your task list between
sessions, and can be extended later.
The sections below show how to get the project running and how to use all of
the important features.

## Setting up the project

Prerequisites: JDK 17.
Import the project into your IDE (IntelliJ or VS Code with the Java
extension) as a Gradle project. Keep the `src/main/java` folder structure
intact – tools such as Gradle expect Java sources to be located there.

### In IntelliJ

1. Open IntelliJ (if you are not in the welcome screen, choose File > Close
   Project first).
2. Click Open, select the project directory and click OK. Accept any
   prompts with the default options.
3. Configure the project to use JDK 17:
   File > Project Structure > Project Settings > Project SDK (set to 17) and
   set Project language level to SDK default.
4. Locate `src/main/java/Lego.java` in the Project view, right‑click it and
   choose Run 'Lego.main()’.
5. The run window should show the Lego banner:

```console
 Hello! I'm [Lego]
 What can I do for you?
 Ready to go shopping? Because I am! :D Type something and I will record it for you!
```

### In VS Code or command line

1. Ensure the Java Extension Pack is installed and the project is opened as
   a folder.
2. Make sure the workspace is using JDK 17 (check the status bar).
3. Run the main class Lego using the run configuration or via the terminal:

   cd /path/to/project
   ./gradlew run      # Windows: gradlew.bat run

A file named lego.txt (or similar) will be created in the project directory
to persist your tasks.

Warning: Keep the `src\main\java` folder as the root folder for Java
files (i.e. don’t rename those folders or move Java files outside of this
path); Gradle and the compiler expect it.

## Using Lego

Type commands at the prompt and press Enter. Commands are case‑insensitive.
Below is the list of supported features and the syntax you use to invoke them.

### Task creation

* todo <description> – adds a ToDo task.
* deadline <description> /<yyyy‑mm‑dd> – adds a Deadline task.
* event <description> /from <any date and or time> /to <any date and or time> – adds an Event task.

Example:

```console
todo buy milk
 Got it. I've added this task:
 [T][ ] buy milk
 Now you have 1 tasks in the list.

deadline CS2113 IP /Friday
 Got it. I've added this task:
 [D][ ] CS2113 IP (by: Friday)
 Now you have 2 tasks in the list.

event celebration /from 5pm /to 7pm
 Got it. I've added this task:
 [E][ ] celebration (from: 5pm to: 7pm)
 Now you have 3 tasks in the list.
```

### Viewing tasks

* list – display all current tasks with their status.

```console
list
 1 [T][X] buy milk
 2 [D][ ] CS2113 IP (by: Friday)
 3 [E][ ] celebration (from: 5pm to: 7pm)
```

* find <keyword> – show tasks whose description contains <keyword>.

```console
find buy
 Here are the matching tasks in your list:
 2 [T][ ] buy bread
 3 [T][ ] buy chicken
```

### Modifying tasks

* done <task number> – mark the specified task as completed.

```console
mark 1
 Nice! I've marked this task as done:
 [T][X] buy milk
'''

* delete <task number> – remove the specified task from the list.

```console
delete 1
 Noted. I've removed this task:
 [T][X] buy milk
```

Task numbers are those shown by the most recent list command.

### Persistence

Tasks are saved automatically when the application exits (via bye).
When you restart Lego, it will load the tasks from the storage file.

### Exiting

* bye – save the current task list and terminate the program.

```console
bye
 Bye. Hope to see you again soon!
```

### Error handling

Lego prints an error message if you enter an unrecognised command, omit a
required field, or specify an invalid task number.
Common mistakes:

* forgetting the /from or /to keyword,
* missing / in deadline and/or events,
* specifying a task number that doesn’t exist.
