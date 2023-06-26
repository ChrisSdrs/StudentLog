public class Student {
    private String studentId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String semester;

    //  Constructor
    public Student(String studentId, String fullName, String email, String phoneNumber, String semester) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.semester = semester;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
