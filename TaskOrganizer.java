import java.util.LinkedList;

public class TaskOrganizer {
    private LinkedList<TaskDetails> taskList; // LinkedList to store tasks

   
    public TaskOrganizer() {
        taskList = new LinkedList<>();
    }

    
    public void addTask(TaskDetails task) {
        taskList.add(task);}
        
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
