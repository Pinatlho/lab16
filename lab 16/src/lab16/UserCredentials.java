package lab16;

import java.util.HashMap;
import java.util.Map;

public class UserCredentials {
    private static UserCredentials instance;
    private Map<String, String> credentials = new HashMap<>();

    // Private constructor for singleton pattern
    private UserCredentials() {}

    // Method to get the single instance of UserCredentials
    public static UserCredentials getInstance() {
        if (instance == null) {
            instance = new UserCredentials();
        }
        return instance;
    }

    // Method to store user credentials
    public void setCredentials(String username, String password) {
        // You might want to hash the password before storing it
        credentials.put(username, password);
        System.out.println("Stored username: " + username + " Stored Password: " + password);
    }

    // Method to check if the provided credentials match the stored ones
    public boolean checkCredentials(String username, String password) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }
}