package lab16;

public class Task {

    static void add(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String taskName;
    private String taskDescription;
    private String developerFirstName;
    private String developerLastName;
    private int taskDuration; // in hours
    private String taskStatus;
    private static int taskCounter = 0; // Counter to track task numbers
    private static int totalHours = 0; // Accumulator for total task hours
    private String taskID;

    // Main constructor
    public Task(String taskName, String taskDescription, String developerFirstName, int taskDuration, String developerLastName) {
        // Validate task duration
        if (taskDuration < 0) {
            throw new IllegalArgumentException("Task duration cannot be negative.");
        }

        this.taskName = taskName;                                   
        this.taskDescription = taskDescription;                     
        this.developerFirstName = developerFirstName;                   
        this.developerLastName = developerLastName;                   
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
        totalHours += taskDuration; // Update total hours
    }

    // Task ID creation method
    String createTaskID() {          
        taskCounter++;
        String taskNamePart = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String developerNamePart = (developerFirstName + " " + developerLastName).length() >= 3 ? 
            (developerFirstName + " " + developerLastName).substring((developerFirstName + " " + developerLastName).length() - 3).toUpperCase() : 
            (developerFirstName + " " + developerLastName).toUpperCase();
        return taskNamePart + ":" + taskCounter + ":" + developerNamePart;
    }

    // Method to print task details
    public String printTaskDetails() {      
        return String.format(
                "Task Name: %s%nTask Description: %s%nDeveloper Details: %s %s%nTask Duration: %d hours%nTask Status: %s%nTask ID: %s",
                taskName, taskDescription, developerFirstName, developerLastName, taskDuration, taskStatus, taskID
        );
    }

    // Static method to return total hours
    public static int returnTotalHours() {    
        return totalHours;
    }

    // Getter for taskID
    public String getTaskID() {
        return taskID; // Return the task ID
    }
}