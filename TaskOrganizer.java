import java.util.LinkedList;

public class TaskOrganizer {
   private LinkedList<TaskDetails> taskList; // using the built-in linkedlist feature....makes it so much easier!

 // constructor 
 public TaskOrganizer() {
   taskList = new LinkedList<>(); 
 }

 // adding a task to the list
 public void addTask(TaskDetails task) {
   taskList.add(task); // This will add a task to the end of the list
 }

 // displaying all tasks in order
 public void displayTasks() {
  if (taskList.isEmpty()) {
   System.out.println("No tasks to display.");
   return;
  }
  System.out.println("Tasks:");
  for (TaskDetails task : taskList) {
   System.out.println(task);
  }
 }

 // This is using the bubble sort to sort tasks by priority level
 public void sortTasks() {
  if (taskList.isEmpty()) return; 

  boolean swapped;
  do {
   swapped = false;
   for (int i = 0; i < taskList.size() - 1; i++) {
    if (taskList.get(i).getPriorityLevel() > taskList.get(i + 1).getPriorityLevel()) {
     TaskDetails temp = taskList.get(i);
     taskList.set(i, taskList.get(i + 1));
     taskList.set(i + 1, temp);
     swapped = true;
    }
   }
  } while (swapped);
 }

 // this allows you to type the task to remove it, which you would need to do if you complete the task
 public void removeTask(String taskName) {
  if (taskList.isEmpty()) {
   System.out.println("No tasks to remove.");
   return;
  }

  boolean found = false;
  for (TaskDetails task : taskList) {
   if (task.getTask().equals(taskName)) //This is what will appear if you successfully delete a task 
	   {
    taskList.remove(task);
    System.out.println("Task + taskName + removed.");
    found = true;
    break;
   }
  }
//This will appear if you mistyped the task name 
  if (!found) {
   System.out.println("Task  + taskName +  not found.");
  }
 }
}
