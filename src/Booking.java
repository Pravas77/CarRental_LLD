import java.time.LocalDate;

public class Booking {

    private User user;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private int cost;

    public Booking(User user, Vehicle vehicle, LocalDate startDate, LocalDate endDate, int cost) {
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public User getUser() {
        return user;
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

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "user=" + user +
                ", vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                '}';
    }
}
