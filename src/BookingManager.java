import java.time.LocalDate;
import java.util.concurrent.locks.StampedLock;

public class BookingManager {
    CostComputationStrategy costComputationStrategy;

    public BookingManager(CostComputationStrategy costComputationStrategy) {
        this.costComputationStrategy = costComputationStrategy;
    }

    public Booking bookVehicle(User user, Vehicle vehicle, LocalDate startDate, LocalDate endDate, PaymentStrategy paymentStrategy) {

        if (!vehicle.searchForReservation(startDate, endDate))
            throw new RuntimeException("Selected vehicle is not available");

        StampedLock lock = vehicle.getLock();
        long stamp = lock.writeLock();

        try {
            int index = vehicle.searchVehicle(startDate, endDate);
            if (index == -1) throw new RuntimeException("Selected vehicle is not available");

            int cost = costComputationStrategy.compute(vehicle, startDate, endDate);
            boolean paymentStatus = paymentStrategy.pay(cost);

            if (paymentStatus) {
                vehicle.reserveVehicle(index, startDate, endDate);
                Booking booking = new Booking(user, vehicle, startDate, endDate, cost);
                return booking;
            } else {
                throw new RuntimeException("Payment failed please try again");
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
