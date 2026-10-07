import java.time.LocalDate;
import java.util.List;

public class CarRentalSystem {
    StoreManager storeManager;
    BookingManager bookingManager;
    List<User> users;

    public CarRentalSystem(StoreManager storeManager, BookingManager bookingManager, List<User> users) {
        this.storeManager = storeManager;
        this.bookingManager = bookingManager;
        this.users = users;
    }

    public List<Vehicle> searchVehicles(City city, VehicleType vehicleType) {
        return storeManager.searchVehicles(city, vehicleType);
    }

    public Booking bookVehicle(User user, Vehicle vehicle, LocalDate startDate, LocalDate endDate, PaymentStrategy paymentStrategy) {
        return bookingManager.bookVehicle(user, vehicle, startDate, endDate, paymentStrategy);
    }
}
