import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private int teacherId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String specialization;
    private List<Course> courses;

    //  Constructor
    public Teacher(int teacherId, String fullName, String email, String phoneNumber, String specialization) {
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.courses = new ArrayList<>();
    }

    // Accessor methods (getters and setters)
    public int getTeacherId() {
        return teacherId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
