import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private int id;
    private List<Vehicle> vehicles;

    public Store(int id, List<Vehicle> vehicles) {
        this.id = id;
        this.vehicles = vehicles;
    }

    public int getId() {
        return id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Vehicle> searchVehicles(VehicleType vehicleType) {

        List<Vehicle> requiredVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType() == vehicleType) requiredVehicles.add(vehicle);
        }
        return requiredVehicles;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                '}';
    }
}
