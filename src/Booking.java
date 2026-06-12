import java.time.LocalDate;

public class Booking {

    private User user;
    private Strore strore;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(User user, Strore strore, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.strore = strore;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public Strore getStrore() {
        return strore;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
