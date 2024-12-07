import java.util.PriorityQueue;
import java.util.Comparator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskScheduler {
    private PriorityQueue<TaskDetails> taskQueue;
    private JFrame frame;
    private JTextField taskNameField, dueDateField, priorityField;
    private JTextArea taskDisplayArea;
    private JButton addButton, removeButton, removeSpecificButton;

    // Constructor
    public TaskScheduler() {
        taskQueue = new PriorityQueue<>(new Comparator<TaskDetails>() {
            @Override
            public int compare(TaskDetails t1, TaskDetails t2) {
                int priorityComparison = Integer.compare(t1.getPriorityLevel(), t2.getPriorityLevel());
                if (priorityComparison == 0) {
                    return t1.getDueDate().compareTo(t2.getDueDate());
                }
                return priorityComparison;
            }
        });
// This is the code that builds the GUI
        frame = new JFrame("Task Scheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        taskNameField = new JTextField(20);
        dueDateField = new JTextField(20);
        priorityField = new JTextField(20);

        taskDisplayArea = new JTextArea(10, 40);
        taskDisplayArea.setEditable(false);

        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Top Task");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Task Name:"));
        inputPanel.add(taskNameField);
        inputPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        inputPanel.add(dueDateField);
        inputPanel.add(new JLabel("Priority Level:"));
        inputPanel.add(priorityField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(taskDisplayArea), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTaskFromGUI());
        removeButton.addActionListener(e -> removeTaskFromGUI());
    }
// How a user adds tasks
    private void addTaskFromGUI() {
        String taskName = taskNameField.getText();
        String dueDate = dueDateField.getText();
        int priorityLevel;

        if (!isValidDate(dueDate)) {
            JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        try {
            priorityLevel = Integer.parseInt(priorityField.getText());
            if (taskName.isEmpty() || dueDate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }
            TaskDetails task = new TaskDetails(taskName, dueDate, priorityLevel);
            taskQueue.add(task);
            displayTasksFromGUI();
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid priority number.");
        }
    }
//Checking for a valid date format
    private boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
//This makes it so tasks are displayed dynamically
    private void displayTasksFromGUI() {
        if (taskQueue.isEmpty()) {
            taskDisplayArea.setText("No tasks to display.");
            return;
        }
        taskDisplayArea.setText("Tasks in priority order:\n");
        PriorityQueue<TaskDetails> tempQueue = new PriorityQueue<>(taskQueue);
        while (!tempQueue.isEmpty()) {
            taskDisplayArea.append(tempQueue.poll().toString() + "\n");
        }
    }

    private void removeTaskFromGUI() {
        if (taskQueue.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No tasks to remove.");
            return;
        }
        taskQueue.poll();
        displayTasksFromGUI();
    }

    private void clearFields() {
        taskNameField.setText("");
        dueDateField.setText("");
        priorityField.setText("");
    }
public PriorityQueue<TaskDetails> getTaskQueue() {
    return taskQueue;
}

// Getters
public JTextField getTaskNameField() {
    return taskNameField;
}

public JTextField getDueDateField() {
    return dueDateField;
}

public JTextField getPriorityField() {
    return priorityField;
}

// this will clear the fields
public void clearFields() {
    taskNameField.setText("");
    dueDateField.setText("");
    priorityField.setText("");
}
    public void run() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.run();
    }
}
