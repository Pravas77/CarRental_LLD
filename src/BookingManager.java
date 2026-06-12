import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class BookingManager {
    CostComputationStrategy costComputationStrategy;

    public BookingManager(CostComputationStrategy costComputationStrategy) {
        this.costComputationStrategy = costComputationStrategy;
    }

    public Booking bookVehicle(User user, Strore strore, Vehicle vehicle, LocalDate startDate, LocalDate endDate, PaymentStrategy paymentStrategy) {

        if (!vehicle.searchForReservation(startDate, endDate))
            throw new RuntimeException("Selected vehicle is not available");
        
          System.out.println(user.getId() + " is waiting for write lock for vehicle " + vehicle.getVehicleNumber());

        StampedLock lock = vehicle.getLock();
        long stamp = lock.writeLock();
        System.out.println(user.getId() + " got lock for vehicle " + vehicle.getVehicleNumber());

        try {


            if (!vehicle.searchVehicle(startDate, endDate, vehicle.getVehicleType()))
                throw new RuntimeException("Selected vehicle is not available");

            int cost = costComputationStrategy.compute(vehicle, startDate, endDate);

            PaymentStatus paymentStatus = null;
            CompletableFuture<PaymentStatus> payment = CompletableFuture.supplyAsync(() -> paymentStrategy.pay(cost));

            try {
                paymentStatus = payment.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                payment.cancel(true);
                paymentStatus = PaymentStatus.FAILED;
            }

            if (paymentStatus == PaymentStatus.SUCCEED) {
                vehicle.reserveVehicle(startDate, endDate);
                Booking booking = new Booking(user, strore, vehicle, startDate, endDate);
                return booking;
            } else {
                throw new RuntimeException("Payment failed please try again");
            }

        }

        finally {
            System.out.println(user.getId() + " got unlocked vehicle " + vehicle.getVehicleNumber());
            lock.unlockWrite(stamp);
        }


    }


    public void removeCompletedBooking(Booking booking){
        booking.getVehicle().removeCompletedBooking(booking.getStartDate(),booking.getEndDate());
    }
}
