public class SchoolManager {
    public static void main(String[] args) {
        Subject math = new Subject("S001", "Mathematics", 100, 35);
        Student[] students = new Student[2];
        students[0] = new Student("ST001", "Alice", 10, new double[]{90, 85, 80, 95, 88});
        students[1] = new Student("ST002", "Bob", 10, new double[]{60, 70, 65, 55, 58});
        Teacher teacher = new Teacher("T001", "Mrs. Smith", "Mathematics");
        teacher.assignGrades(students[0], math, 90);
        teacher.assignGrades(students[1], math, 60);
        for (Student s : students) s.displayResult();
        teacher.displayTeacherInfo();
        System.out.println("Top Student: " + Student.getTopStudent(students).studentName);
        System.out.println("Class Average: " + Student.getClassAverage(students));
        System.out.println("Pass Percentage: " + Student.getPassPercentage(students) + "%");
    }
}

class Student {
    private String studentId;
    public String studentName;
    private int grade;
    private double[] marks;
    private double totalMarks;
    private double percentage;
    public Student(String id, String name, int grade, double[] marks) {
        this.studentId = id;
        this.studentName = name;
        this.grade = grade;
        this.marks = marks;
        calculateTotal();
        calculatePercentage();
    }
    public void calculateTotal() {
        totalMarks = 0;
        for (double m : marks) totalMarks += m;
    }
    public void calculatePercentage() {
        percentage = totalMarks / marks.length;
    }
    public void displayResult() {
        System.out.printf("ID: %s, Name: %s, Grade: %d, Total: %.2f, Percentage: %.2f, Pass: %b\n",
            studentId, studentName, grade, totalMarks, percentage, isPass());
    }
    public boolean isPass() {
        for (double m : marks) if (m < 35) return false;
        return true;
    }
    public static Student getTopStudent(Student[] students) {
        Student top = students[0];
        for (Student s : students) if (s.percentage > top.percentage) top = s;
        return top;
    }
    public static double getClassAverage(Student[] students) {
        double sum = 0;
        for (Student s : students) sum += s.percentage;
        return sum / students.length;
    }
    public static double getPassPercentage(Student[] students) {
        int passCount = 0;
        for (Student s : students) if (s.isPass()) passCount++;
        return (passCount * 100.0) / students.length;
    }
}

class Teacher {
    private String teacherId;
    private String teacherName;
    private String subject;
    private int studentsHandled;
    public Teacher(String id, String name, String subject) {
        this.teacherId = id;
        this.teacherName = name;
        this.subject = subject;
    }
    public void assignGrades(Student student, Subject subject, double marks) {
        studentsHandled++;
    }
    public void displayTeacherInfo() {
        System.out.printf("ID: %s, Name: %s, Subject: %s, Students Handled: %d\n",
            teacherId, teacherName, subject, studentsHandled);
    }
}

class Subject {
    private String subjectCode;
    private String subjectName;
    private int maxMarks;
    private int passMarks;
    public Subject(String code, String name, int max, int pass) {
        this.subjectCode = code;
        this.subjectName = name;
        this.maxMarks = max;
        this.passMarks = pass;
    }
}
