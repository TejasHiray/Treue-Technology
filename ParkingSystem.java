import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ParkingSpot class representing a parking spot
class ParkingSpot {
    private int spotNumber;
    private boolean isAvailable;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.isAvailable = true;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// User class representing a user of the system
class User {
    private String username;
    private String password;
    private List<Integer> bookings;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookings = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getBookings() {
        return bookings;
    }
}

// Main ParkingSystem class to manage the parking system
public class ParkingSystem {
    private Map<Integer, ParkingSpot> parkingSpots;
    private Map<String, User> users;

    public ParkingSystem() {
        this.parkingSpots = new HashMap<>();
        this.users = new HashMap<>();
    }

    // Add a parking spot to the system
    public void addParkingSpot(int spotNumber) {
        parkingSpots.put(spotNumber, new ParkingSpot(spotNumber));
    }

    // Register a new user
    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    // User login (not a secure implementation, just for demonstration)
    public boolean login(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    // Search for available parking spots
    public List<ParkingSpot> searchAvailableSpots() {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots.values()) {
            if (spot.isAvailable()) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;
    }

    // Book a parking spot
    public boolean bookParkingSpot(String username, int spotNumber) {
        User user = users.get(username);
        ParkingSpot spot = parkingSpots.get(spotNumber);

        if (user != null && spot != null && spot.isAvailable()) {
            spot.setAvailable(false);
            user.getBookings().add(spotNumber);
            return true;
        }
        return false;
    }

    // Main method to test the parking system
    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem();

        // Add some parking spots
        parkingSystem.addParkingSpot(1);
        parkingSystem.addParkingSpot(2);
        parkingSystem.addParkingSpot(3);

        // Register users
        parkingSystem.registerUser("user1", "password1");
        parkingSystem.registerUser("user2", "password2");

        // User login (not a secure implementation, just for demonstration)
        if (parkingSystem.login("user1", "password1")) {
            // Search available spots
            List<ParkingSpot> availableSpots = parkingSystem.searchAvailableSpots();
            System.out.println("Available spots: " + availableSpots);

            // Book a spot
            boolean bookingSuccess = parkingSystem.bookParkingSpot("user1", 1);
            if (bookingSuccess) {
                System.out.println("Booking confirmed! Spot 1 is now reserved for user1.");
            } else {
                System.out.println("Booking failed. Spot 1 might be unavailable.");
            }
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }
}
