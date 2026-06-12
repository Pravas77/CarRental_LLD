import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class Vehicle {

    private String vehicleNumber;
    private VehicleType vehicleType;
    private List<Pair> reservations = new ArrayList<>();
    private StampedLock lock = new StampedLock();

    public StampedLock getLock() {
        return lock;
    }

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }


    public boolean searchVehicle(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {

        if (this.vehicleType != vehicleType) return false;

        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();

        for (Pair reservation : reservations) {

            if (end < reservation.getStartDate() || start > reservation.getEndDate()) continue;
            else return false;
        }

        return true;

    }

    public void reserveVehicle(LocalDate startDate, LocalDate endDate) {
        reservations.add(new Pair(startDate.toEpochDay(), endDate.toEpochDay()));
    }


    public boolean searchForReservation(LocalDate startDate, LocalDate endDate) {
        long stamp = lock.tryOptimisticRead();
        if (!searchVehicle(startDate, endDate, getVehicleType())) return false;
        return lock.validate(stamp);
    }

    public void removeCompletedBooking(LocalDate startDate, LocalDate endDate){
        long stamp = lock.writeLock();
        reservations.remove(new Pair(startDate.toEpochDay(), endDate.toEpochDay()));
        lock.unlockWrite(stamp);
    }


}
