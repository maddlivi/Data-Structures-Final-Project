

// this is the main class that will hold all of the important task details
public class TaskDetails {
	
	public String task;
	public String dueDate;
	public int priorityLevel;



// constructor
public TaskDetails(String task, String dueDate, int priorityLevel) {
this.task=task;
this.dueDate=dueDate;
this.priorityLevel=priorityLevel;
	
}
//getters and setters all listed below
public String getTask() {
	return task;
}

public void setTask (String task) {
	this.task=task;
}

public String getDueDate() {
	return dueDate;
}

public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
    
    
}

public int getPriorityLevel() {
    return priorityLevel;
}

public void setPriorityLevel(int priorityLevel) {
    this.priorityLevel = priorityLevel;
}
// this is just making it so everything is formatted correctly when printed
@Override
public String toString() {
    return "Task: " + task + ", Due Date: " + dueDate + ", Priority: " + priorityLevel;
}

}