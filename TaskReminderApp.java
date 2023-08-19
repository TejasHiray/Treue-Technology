import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskReminderApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskReminderSystem taskReminder = new TaskReminderSystem();

        while (true) {
            System.out.println("Task Reminder Application");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Priority (1-5): ");
                    int priority = scanner.nextInt();
                    System.out.print("Due Date (YYYY-MM-DD): ");
                    String dueDateString = scanner.next();
                    LocalDate dueDate = LocalDate.parse(dueDateString);
                    taskReminder.addTask(title, category, priority, dueDate);
                    System.out.println("Task added successfully!");
                    break;

                case 2:
                    List<Task> tasks = taskReminder.getTasks();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + ". " + task.getTitle() + " - " + task.getDueDate());
                    }
                    break;

                case 3:
                    System.out.print("Enter the index of the task to mark as completed: ");
                    int taskIndex = scanner.nextInt();
                    taskReminder.markTaskAsCompleted(taskIndex);
                    System.out.println("Task marked as completed!");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

