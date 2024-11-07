import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

public class TaskScheduler {
 private PriorityQueue<TaskDetails> taskQueue;
 private Scanner scanner;

 //constructor
 public TaskScheduler() {
  //PriorityQueue with a comparator for showing priority levels in ascending order
  taskQueue = new PriorityQueue<>(new Comparator<TaskDetails>() {
   @Override
   public int compare(TaskDetails t1, TaskDetails t2) {
    //First it will be compared by priority level
    int priorityComparison = Integer.compare(t1.getPriorityLevel(), t2.getPriorityLevel());
    
    // if priorities match, then compare by due date
    if (priorityComparison == 0) {
     return t1.getDueDate().compareTo(t2.getDueDate());
    }
    
    return priorityComparison;
   }
  });
  
  scanner = new Scanner(System.in); // this is starting the scanner needed for user input
 }

 // How a user will add a task
 public void addTask() {
  System.out.print("Enter task name: ");
  String taskName = scanner.nextLine(); // THis line will just make it easier to read by going to the next line
  
  System.out.print("Enter due date in this format (yyyy-mm-dd): ");
  String dueDate = scanner.nextLine();
  
  System.out.print("Enter priority level (1 is highest priority): ");
  int priorityLevel = scanner.nextInt();
  scanner.nextLine(); 
  
  //This will create a TaskDetails object with all required info 
  TaskDetails task = new TaskDetails(taskName, dueDate, priorityLevel);
  taskQueue.add(task);
  System.out.println("Task added: " + task.getTask());
 }

 //Display tasks in priority order
 public void displayTasks() {
  if (taskQueue.isEmpty()) {
   System.out.println("There are no tasks yet.");
   return;
  }
  System.out.println("Tasks in priority order:");
  PriorityQueue<TaskDetails> tempQueue = new PriorityQueue<>(taskQueue);
  while (!tempQueue.isEmpty()) {
   System.out.println(tempQueue.poll());
  }
 }

 // To remove the highest priority task (as if you completed it)
 public void remove1stTask() {
  if (taskQueue.isEmpty()) {
   System.out.println("There are no tasks to remove.");
   return;
  }
  TaskDetails removedTask = taskQueue.poll(); 
  System.out.println("Removed task: " + removedTask.getTask());
 }

 // This would be used to view the highest priority task
 public void show1stTask() {
  if (taskQueue.isEmpty()) {
   System.out.println("No tasks available.");
   return;
  }
  TaskDetails highest = taskQueue.peek();
  System.out.println("Highest priority task is: " + highest.getTask() + " with priority " + highest.getPriorityLevel());
 }

 // This is the part of the console app that the user will see to enter tasks and display them
 public void run() {
  while (true) {
   System.out.println("\nTask Scheduler Menu:");
   System.out.println("1. Add a task");
   System.out.println("2. Display tasks");
   System.out.println("3. Remove highest priority task");
   System.out.println("4. Peek at the highest priority task");
   System.out.println("5. Exit");
   System.out.print("Choose an option: ");
   
   int choice = scanner.nextInt();
   scanner.nextLine(); 
   
   switch (choice) {
    case 1:
     addTask();
     break;
    case 2:
     displayTasks();
     break;
    case 3:
     remove1stTask();
     break;
    case 4:
     show1stTask();
     break;
    case 5:
     System.out.println("Bye bye.");
     return;
    default:
     System.out.println("Invalid. Try again.");
   }
  }
 }
//starting the user input portion
 public static void main(String[] args) {
  TaskScheduler scheduler = new TaskScheduler();
  scheduler.run(); 
 }
}
