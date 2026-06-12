import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello");

        Vehicle vehicle1 = new Vehicle("BR-1",VehicleType.Economy);
        Vehicle vehicle2 = new Vehicle("BR-2",VehicleType.Luxury);
        Vehicle vehicle3 = new Vehicle("BR-3",VehicleType.Economy);
        Vehicle vehicle4 = new Vehicle("BR-4",VehicleType.Luxury);
        Vehicle vehicle5 = new Vehicle("BR-5",VehicleType.Economy);

        Strore strore1 = new Strore(1, List.of(vehicle1,vehicle2,vehicle3));
        Strore strore2 = new Strore(2, List.of(vehicle4,vehicle5));

        StoreManager storeManager = new StoreManager(Map.of(
                City.DELHI,List.of(strore1,strore2)
        ));

        BookingManager bookingManager = new BookingManager(new DailyCostComputationStrategy());

        User user1 = new User(1);
        User user2 = new User(2);

        CarRentalSystem carRentalSystem = new CarRentalSystem(storeManager,bookingManager,List.of(user1,user2));


        //client
        List<Strore> strores = carRentalSystem.searchStores(City.DELHI);
        for (Strore strore : strores) System.out.println(strore.getId());

        List<Vehicle> vehicles = carRentalSystem.searchVehicles(LocalDate.of(2026,12,5),LocalDate.of(2026,12,7),VehicleType.Economy,strores.get(0));
        for (Vehicle vehicle : vehicles) System.out.println(vehicle.getVehicleNumber());


        Thread thread1 = new Thread(() -> {
            Booking booking1 = carRentalSystem.bookVehicle(
                    user1,
                    strores.get(0),
                    vehicles.get(0),
                    LocalDate.of(2026,12,5),
                    LocalDate.of(2026,12,7),
                    new RazorparPaymentStrategy());

            System.out.println("User id " + booking1.getUser().getId() +" booked vehicle " + booking1.getVehicle().getVehicleNumber());
        });

        Thread thread2 = new Thread(() -> {
            Booking booking2 = carRentalSystem.bookVehicle(
                    user2,
                    strores.get(0),
                    vehicles.get(0),
                    LocalDate.of(2026,12,8),
                    LocalDate.of(2026,12,11),
                    new RazorparPaymentStrategy());

            System.out.println("User id " + booking2.getUser().getId() +" booked vehicle " + booking2.getVehicle().getVehicleNumber());
        });

        thread1.start();
//        Thread.sleep(20);
        thread2.start();


    }
}