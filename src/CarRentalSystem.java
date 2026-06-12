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

    public List<Strore> searchStores(City city) {
        return storeManager.searchStores(city);
    }

    public List<Vehicle> searchVehicles(LocalDate startDate, LocalDate endDate, VehicleType vehicleType, Strore strore) {
        return storeManager.searchVehicles(startDate, endDate, vehicleType, strore);
    }

    public Booking bookVehicle(User user, Strore strore, Vehicle vehicle, LocalDate startDate, LocalDate endDate, PaymentStrategy paymentStrategy) {
        return bookingManager.bookVehicle(user, strore, vehicle, startDate, endDate, paymentStrategy);
    }

    public void removeCompletedBooking(Booking booking) {
        bookingManager.removeCompletedBooking(booking);
    }
}
