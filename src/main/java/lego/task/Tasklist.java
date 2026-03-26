package lego.task;

import java.util.ArrayList;

import lego.exception.LegoException;

public class Tasklist {

    private ArrayList<Task> taskList;

    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Task does not exist. Choose another number.");
        }
    }

    public void resetTaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void markTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.get(index).setDone(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(this.taskList.get(index));
        } else {
            throw new IndexOutOfBoundsException("Task does not exist. Choose another number.");
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.get(index).setDone(false);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println(this.taskList.get(index));
        } else {
            throw new IndexOutOfBoundsException("Task does not exist. Choose another number.");
        }
    }

    public void deleteEvent(int index) {
        Task taskToDelete = this.taskList.get(index);
        this.taskList.remove(index);
        Task.decreaseNumOfTasks();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(taskToDelete);
        System.out.println(" Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void addNewEvent(String input) throws LegoException {
        String[] splitEvent = input.trim().split(" /");
        String eventOnly = splitEvent[0].trim();
        String eventFrom = splitEvent[1].replace("from", "").trim();
        String eventTo = splitEvent[2].replace("to", "").trim();
        Event newEvent = new Event(eventOnly, eventFrom, eventTo);
        System.out.println(" Got it. I've added this task:");
        this.taskList.add(newEvent);
        System.out.println(newEvent);
        System.out.println(" Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void addNewDeadline(String input) throws LegoException {
        System.out.println(" Got it. I've added this task:");
        String[] splitDeadline = input.trim().split(" /");
        String deadlineOnly = splitDeadline[0].trim();
        String taskDeadline = splitDeadline[1].trim();
        Deadline newDeadline = new Deadline(deadlineOnly, taskDeadline);
        this.taskList.add(newDeadline);
        System.out.println(newDeadline);
        System.out.println(" Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (this.taskList.isEmpty()) {
            System.out.println(" Your task list is currently empty.");
            return;
        }
        for (int i = 0; i < Task.getNumOfTasks(); i++) {
            Task currTask = this.taskList.get(i);
            System.out.println(" " + Integer.toString(currTask.taskNum) + currTask.toString());
        }
    }

    public void addTodo(String input) throws LegoException {
        String todoOnly = input.trim();
        System.out.println(" Got it. I've added this task:");
        Task newTodo = new Todo(todoOnly);
        this.taskList.add(newTodo);
        System.out.println(newTodo);
        System.out.println(" Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void findTask(String input) throws LegoException {
        boolean atLeastOneMatch = false;
        String keyword = input.trim();
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < Task.getNumOfTasks(); i++) {
            Task currTask = this.taskList.get(i);
            if (currTask.getDescription().contains(keyword)) {
                atLeastOneMatch = true;
                System.out.println(" " + Integer.toString(currTask.taskNum) + currTask.toString());
            }
        }
        if (!atLeastOneMatch) {
            System.out.println(" No matching tasks found.");
        }
    }
}
