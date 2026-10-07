import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.StampedLock;

public class Vehicle {

    private String vehicleNumber;
    private VehicleType vehicleType;
    private Store store;
    private List<Reservation> reservations;
    private StampedLock lock;

    public Vehicle(String vehicleNumber, VehicleType vehicleType, Store store) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.store = store;
        this.reservations = new CopyOnWriteArrayList<>();
        this.lock = new StampedLock();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Store getStore() {
        return store;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public StampedLock getLock() {
        return lock;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int searchVehicle(LocalDate startDate, LocalDate endDate) {

        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();

        long prevEnd = -1;
        int index = -1;
        for (int i = 0; i < reservations.size(); i++) {

            long nextStart = reservations.get(i).getStartTime().toEpochDay();
            if (prevEnd < start && end < nextStart) {
                index = i;
                break;
            }
            prevEnd = reservations.get(i).getEndTime().toEpochDay();
        }

        if (index == -1 && prevEnd < start) index = reservations.size();
        return index;
    }

    public boolean searchForReservation(LocalDate startDate, LocalDate endDate) {
        long stamp = lock.tryOptimisticRead();
        if (searchVehicle(startDate, endDate) == -1) return false;
        return lock.validate(stamp);
    }

    public void reserveVehicle(int index, LocalDate startDate, LocalDate endDate) {
        reservations.add(index, new Reservation(startDate, endDate));
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
