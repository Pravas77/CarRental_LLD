import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoreManager {
    private Map<City, List<Store>> storesMap;

    public StoreManager(Map<City, List<Store>> storesMap) {
        this.storesMap = storesMap;
    }

    public List<Vehicle> searchVehicles(City city, VehicleType vehicleType) {

        List<Store> stores = storesMap.getOrDefault(city, new ArrayList<>());
        List<Vehicle> vehicles = new ArrayList<>();
        for (Store store : stores) {
            vehicles.addAll(store.searchVehicles(vehicleType));
        }
        return vehicles;
    }
}
