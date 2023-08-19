import java.util.*;

class Appointment {
    private Date dateTime;
    private String serviceProvider;

    public Appointment(Date dateTime, String serviceProvider) {
        this.dateTime = dateTime;
        this.serviceProvider = serviceProvider;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }
}

class User {
    private String name;
    private String email;
    private List<Appointment> appointments;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}

class AppointmentSystem {
    private List<Appointment> availableAppointments;
    private List<User> users;

    public AppointmentSystem() {
        availableAppointments = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addAvailableAppointment(Appointment appointment) {
        availableAppointments.add(appointment);
    }

    public void registerUser(String name, String email) {
        users.add(new User(name, email));
    }

    public void scheduleAppointment(User user, Appointment appointment) {
        user.addAppointment(appointment);
        availableAppointments.remove(appointment);
        System.out.println("Appointment scheduled for " + user.getName() + " with " + appointment.getServiceProvider() + " at " + appointment.getDateTime());
    }
}

public class Main {
    public static void main(String[] args) {
        AppointmentSystem appointmentSystem = new AppointmentSystem();

        // Add available appointments
        appointmentSystem.addAvailableAppointment(new Appointment(new Date(2023, 8, 18, 10, 0), "Doctor A"));
        appointmentSystem.addAvailableAppointment(new Appointment(new Date(2023, 8, 18, 14, 0), "Doctor B"));

        // Register users
        appointmentSystem.registerUser("Alice", "alice@example.com");
        appointmentSystem.registerUser("Bob", "bob@example.com");

        // Schedule appointments
        User alice = appointmentSystem.getUsers().get(0);
        User bob = appointmentSystem.getUsers().get(1);

        appointmentSystem.scheduleAppointment(alice, appointmentSystem.getAvailableAppointments().get(0));
        appointmentSystem.scheduleAppointment(bob, appointmentSystem.getAvailableAppointments().get(1));
    }
}
