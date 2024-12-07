import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.PriorityQueue;

public class TaskSchedulerTest {

    private TaskScheduler scheduler;
    private TaskDetails task1;
    private TaskDetails task2;
    private TaskDetails task3;

    @BeforeEach
    public void setUp() {
        scheduler = new TaskScheduler();
        task1 = new TaskDetails("Task 1", "2024-09-15", 1);
        task2 = new TaskDetails("Task 2", "2024-09-20", 2);
        task3 = new TaskDetails("Task 3", "2024-09-10", 1);
    }

    @Test
    public void testValidDate() {
        assertTrue(scheduler.isValidDate("2024-09-15"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(scheduler.isValidDate("15-09-2024"));
        assertFalse(scheduler.isValidDate("2024/09/15"));
        assertFalse(scheduler.isValidDate("invalid-date"));
    }

    @Test
    public void testAddTask() {
        PriorityQueue<TaskDetails> taskQueue = scheduler.getTaskQueue();
        taskQueue.add(task1);
        taskQueue.add(task2);

        assertEquals(2, taskQueue.size());
        assertTrue(taskQueue.contains(task1));
        assertTrue(taskQueue.contains(task2));
    }

    @Test
    public void testAddTaskWithSamePriority() {
        PriorityQueue<TaskDetails> taskQueue = scheduler.getTaskQueue();
        taskQueue.add(task1);
        taskQueue.add(task3);

        assertEquals(2, taskQueue.size());
        assertTrue(taskQueue.contains(task1));
        assertTrue(taskQueue.contains(task3));
    }

    @Test
    public void testRemoveTask() {
        PriorityQueue<TaskDetails> taskQueue = scheduler.getTaskQueue();
        taskQueue.add(task1);
        taskQueue.add(task2);

        taskQueue.poll(); 

        assertEquals(1, taskQueue.size());
        assertFalse(taskQueue.contains(task1));
    }

    @Test
    public void testEmptyQueueAfterRemoval() {
        PriorityQueue<TaskDetails> taskQueue = scheduler.getTaskQueue();
        taskQueue.add(task1);
        taskQueue.poll(); 

        assertTrue(taskQueue.isEmpty());
    }

    @Test
    public void testClearFields() {
        scheduler.getTaskNameField().setText("Sample Task");
        scheduler.getDueDateField().setText("2024-09-15");
        scheduler.getPriorityField().setText("1");

        scheduler.clearFields();

        assertEquals("", scheduler.getTaskNameField().getText());
        assertEquals("", scheduler.getDueDateField().getText());
        assertEquals("", scheduler.getPriorityField().getText());
    }
}
