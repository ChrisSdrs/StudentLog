import java.util.HashMap;
import java.util.Map;

public class Student {
    private String studentId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int semester;
    private Map<Course, Double> grades;

    //  Constructor
    public Student(String studentId, String fullName, String email, String phoneNumber, int semester) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.semester = semester;
        this.grades = new HashMap<>();
    }

    @Override
    public String toString () {
        return "Student: ID=" + studentId + ", Name=" + fullName + ", Email=" + email + ", Phone=" + phoneNumber + ", Semester=" + semester;
    }

    // Accessor methods (getters and setters)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setGrades(Map<Course, Double> grades) {
        this.grades = grades;
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