public class EmployeeManager {
    public static void main(String[] args) {
        Employee emp1 = new Employee("E001", "Alice", "HR", 50000, "FullTime");
        Employee emp2 = new Employee("E002", "Bob", "IT", 200, "PartTime", 120);
        Employee emp3 = new Employee("E003", "Charlie", "Finance", 30000, "Contract");
        emp1.generatePaySlip();
        emp2.generatePaySlip();
        emp3.generatePaySlip();
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
    }
}

class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    private double hoursWorked;
    public Employee(String id, String name, String dept, double salary, String type) {
        this.empId = id;
        this.empName = name;
        this.department = dept;
        this.baseSalary = salary;
        this.empType = type;
        totalEmployees++;
    }
    public Employee(String id, String name, String dept, double hourlyRate, String type, double hoursWorked) {
        this(id, name, dept, hourlyRate, type);
        this.hoursWorked = hoursWorked;
    }
    public double calculateSalary() {
        switch (empType) {
            case "FullTime": return baseSalary + 5000;
            case "PartTime": return baseSalary * hoursWorked;
            case "Contract": return baseSalary;
            default: return 0;
        }
    }
    public double calculateTax() {
        switch (empType) {
            case "FullTime": return calculateSalary() * 0.2;
            case "PartTime": return calculateSalary() * 0.1;
            case "Contract": return calculateSalary() * 0.15;
            default: return 0;
        }
    }
    public void generatePaySlip() {
        System.out.printf("ID: %s, Name: %s, Dept: %s, Type: %s, Salary: %.2f, Tax: %.2f\n",
            empId, empName, department, empType, calculateSalary(), calculateTax());
    }
    public static int getTotalEmployees() {
        return totalEmployees;
    }
}
