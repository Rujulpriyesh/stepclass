import java.util.Scanner;

public class StudentInfoManager {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter Student Name: ");
			String studentName = scanner.nextLine();
			System.out.print("Enter Roll Number: ");
			int rollNumber = Integer.parseInt(scanner.nextLine());
			double[] marks = new double[3];
			String[] subjects = {"Physics", "Chemistry", "Mathematics"};
			for (int i = 0; i < 3; i++) {
				System.out.print("Enter marks for " + subjects[i] + ": ");
				marks[i] = Double.parseDouble(scanner.nextLine());
			}
			Student student = new Student(studentName, rollNumber, marks[0], marks[1], marks[2]);
			student.displayStudentInfo();
			System.out.println("Total Students: " + Student.getTotalStudents());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input! Please enter numeric values for roll number and marks.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
}

class Student {
	private String studentName;
	private int rollNumber;
	private double physics;
	private double chemistry;
	private double mathematics;
	private static int totalStudents = 0;

	public Student(String name, int roll, double phy, double chem, double math) {
		this.studentName = name;
		this.rollNumber = roll;
		this.physics = phy;
		this.chemistry = chem;
		this.mathematics = math;
		totalStudents++;
	}

	public double calculateTotal() {
		return physics + chemistry + mathematics;
	}

	public double calculateAverage() {
		return calculateTotal() / 3.0;
	}

	public char calculateGrade() {
		double avg = calculateAverage();
		if (avg >= 90) return 'A';
		else if (avg >= 80) return 'B';
		else if (avg >= 70) return 'C';
		else if (avg >= 60) return 'D';
		else return 'F';
	}

	public void displayStudentInfo() {
		System.out.println("Name: " + studentName);
		System.out.println("Roll: " + rollNumber);
		System.out.println("Total: " + calculateTotal());
		System.out.println("Average: " + String.format("%.2f", calculateAverage()));
		System.out.println("Grade: " + calculateGrade());
	}

	public static int getTotalStudents() {
		return totalStudents;
	}
}
