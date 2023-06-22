import java.util.HashMap;
import java.util.Map;

public class Student {
    private int studentId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int semester;
    private Map<Course, Double> grades;

    //  Constructor
    public Student(int studentId, String fullName, String email, String phoneNumber, int semester) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.semester = semester;
        this.grades = new HashMap<>();
    }

    // Accessor methods (getters and setters)
    public int getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void addGrade(Course course, double grade) {
        grades.put(course, grade);
    }

    public double getGradeForCourse(Course course) {
        return grades.getOrDefault(course, 0.0);
    }

    public Map<Course, Double> getGrades() {
        return grades;
    }
}
