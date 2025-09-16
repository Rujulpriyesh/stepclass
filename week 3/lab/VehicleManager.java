public class VehicleManager {
    public static void main(String[] args) {
        Vehicle.setCompanyName("Super Rentals");
        Vehicle v1 = new Vehicle("V001", "Toyota", "Camry", 1000);
        Vehicle v2 = new Vehicle("V002", "Honda", "Civic", 900);
        v1.rentVehicle(3);
        v2.rentVehicle(2);
        v1.returnVehicle();
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        Vehicle.displayCompanyStats();
    }
}

class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable = true;
    private int rentalDays = 0;
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName;
    private static int companyRentalDays = 0;
    public Vehicle(String id, String brand, String model, double rent) {
        this.vehicleId = id;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rent;
        totalVehicles++;
    }
    public void rentVehicle(int days) {
        if (isAvailable && days > 0) {
            isAvailable = false;
            rentalDays += days;
            companyRentalDays += days;
            double rentAmount = calculateRent(days);
            totalRevenue += rentAmount;
        }
    }
    public void returnVehicle() {
        isAvailable = true;
    }
    public double calculateRent(int days) {
        return rentPerDay * days;
    }
    public void displayVehicleInfo() {
        System.out.printf("ID: %s, Brand: %s, Model: %s, Available: %b, Rental Days: %d\n",
            vehicleId, brand, model, isAvailable, rentalDays);
    }
    public static void setCompanyName(String name) {
        companyName = name;
    }
    public static double getTotalRevenue() {
        return totalRevenue;
    }
    public static double getAverageRentPerDay() {
        return companyRentalDays == 0 ? 0 : totalRevenue / companyRentalDays;
    }
    public static void displayCompanyStats() {
        System.out.printf("Company: %s, Total Vehicles: %d, Total Revenue: %.2f, Avg Rent/Day: %.2f\n",
            companyName, totalVehicles, totalRevenue, getAverageRentPerDay());
    }
}
