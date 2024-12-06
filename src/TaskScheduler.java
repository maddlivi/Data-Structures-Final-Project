import java.util.PriorityQueue;
import java.util.Comparator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskScheduler {
    private PriorityQueue<TaskDetails> taskQueue;
    private JFrame frame;
    private JTextField taskNameField, dueDateField, priorityField;
    private JTextArea taskDisplayArea;
    private JButton addButton, removeButton, removeSpecificButton;

    // Constructor
    public TaskScheduler() {
        // PriorityQueue with a comparator for showing priority levels in ascending order
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

        frame = new JFrame("Task Scheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#797D62"));

        taskNameField = new JTextField(20);
        dueDateField = new JTextField(20);
        priorityField = new JTextField(20);

        taskDisplayArea = new JTextArea(10, 40);
        taskDisplayArea.setEditable(false);
        taskDisplayArea.setBackground(Color.decode("#D9AE94"));
        taskDisplayArea.setForeground(Color.WHITE);

        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Top Task");
        removeSpecificButton = new JButton("Remove Specific Task");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.setBackground(Color.decode("#9B9B7A"));
        inputPanel.add(new JLabel("Task Name:"));
        inputPanel.add(taskNameField);
        inputPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        inputPanel.add(dueDateField);
        inputPanel.add(new JLabel("Priority Level:"));
        inputPanel.add(priorityField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#D9AE94"));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeSpecificButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(taskDisplayArea), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskFromGUI();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTaskFromGUI();
            }
        });

        removeSpecificButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSpecificTaskFromGUI();
            }
        });
    }

    // Add a task from GUI
    private void addTaskFromGUI() {
        String taskName = taskNameField.getText();
        String dueDate = dueDateField.getText();
        int priorityLevel;

        try {
            priorityLevel = Integer.parseInt(priorityField.getText());
            if (taskName.isEmpty() || dueDate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }
            TaskDetails task = new TaskDetails(taskName, dueDate, priorityLevel);
            taskQueue.add(task);
            System.out.println("Task added: " + task.toString()); // Debug log
            displayTasksFromGUI(); // Update the display area
            clearFields(); // Clear input fields
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid priority number.");
        }
    }

    // Display tasks dynamically
    private void displayTasksFromGUI() {
        SwingUtilities.invokeLater(() -> {
            if (taskQueue.isEmpty()) {
                taskDisplayArea.setText("No tasks to display.");
                return;
            }

            taskDisplayArea.setText("Tasks in priority order:\n");
            PriorityQueue<TaskDetails> tempQueue = new PriorityQueue<>(taskQueue);

            while (!tempQueue.isEmpty()) {
                taskDisplayArea.append(tempQueue.poll().toString() + "\n");
            }
        });
    }

    // Remove the highest-priority task
    private void removeTaskFromGUI() {
        if (taskQueue.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No tasks to remove.");
            return;
        }

        TaskDetails removedTask = taskQueue.poll();
        JOptionPane.showMessageDialog(frame, "Removed task: " + removedTask.getTask());
        displayTasksFromGUI(); // Update the display
    }

    // Remove a specific task from the queue
    private void removeSpecificTaskFromGUI() {
        String taskNameToRemove = JOptionPane.showInputDialog(frame, "Enter task name to remove:");
        if (taskNameToRemove == null || taskNameToRemove.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No task name entered.");
            return;
        }

        boolean taskRemoved = false;
        PriorityQueue<TaskDetails> tempQueue = new PriorityQueue<>(taskQueue);
        PriorityQueue<TaskDetails> newQueue = new PriorityQueue<>(taskQueue.comparator());

        while (!tempQueue.isEmpty()) {
            TaskDetails task = tempQueue.poll();
            if (task.getTask().equalsIgnoreCase(taskNameToRemove)) {
                taskRemoved = true;
                JOptionPane.showMessageDialog(frame, "Task removed: " + task.getTask());
            } else {
                newQueue.add(task);
            }
        }

        if (!taskRemoved) {
            JOptionPane.showMessageDialog(frame, "Task not found.");
        }

        taskQueue = newQueue;
        displayTasksFromGUI(); // Update the display
    }

    // Clear input fields
    private void clearFields() {
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

