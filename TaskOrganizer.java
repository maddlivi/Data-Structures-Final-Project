import java.util.LinkedList;


// This is using java's built in linked list to store the tasks
public class TaskOrganizer {
    private LinkedList<TaskDetails> taskList; 

   //creating an empty list
    public TaskOrganizer() {
        taskList = new LinkedList<>();
    }

    // How to add tasks
    public void addTask(TaskDetails task) {
        taskList.add(task);}
        // Displayig tasks
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

    //This is where I used the insertion sort method
    public void insertionSort() {
        for (int i = 1; i < taskList.size(); i++) {
            TaskDetails currentTask = taskList.get(i);
            int j = i - 1;

            
            while (j >= 0 && taskList.get(j).getPriorityLevel() > currentTask.getPriorityLevel()) {
                taskList.set(j + 1, taskList.get(j));
                j = j - 1;
            }
            taskList.set(j + 1, currentTask);
        }
    }

   // Code to remove tasks
    public void removeTask(String taskName) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        boolean found = false;
        for (TaskDetails task : taskList) {
            if (task.getTask().equals(taskName)) {
                taskList.remove(task);
                System.out.println("Task " + taskName + " removed.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task " + taskName + " not found.");
        }
    }
}
