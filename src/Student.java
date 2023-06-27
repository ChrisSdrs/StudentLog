public class Student {
    private String studentId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String semester;

    /**
     * Constructs a Student object with the specified student ID, full name, email, phone number, and semester.
     *
     * @param studentId    the ID of the student
     * @param fullName     the full name of the student
     * @param email        the email of the student
     * @param phoneNumber  the phone number of the student
     * @param semester     the semester of the student
     */
    public Student(String studentId, String fullName, String email, String phoneNumber, String semester) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.semester = semester;
    }

    /**
     * Default constructor for the Student class.
     * Creates an empty Student object with no initial values for the attributes.
     */
    public Student() {
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return a string representation of the Student object
     */
    @Override
    public String toString() {
        return "Student: ID=" + studentId + ", Name=" + fullName + ", Email=" + email + ", Phone=" + phoneNumber + ", Semester=" + semester;
    }

    /**
     * Returns the ID of the student.
     *
     * @return the ID of the student
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student.
     *
     * @param studentId the ID of the student
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns the full name of the student.
     *
     * @return the full name of the student
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the student.
     *
     * @param fullName the full name of the student
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the email of the student.
     *
     * @return the email of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     *
     * @param email the email of the student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the student.
     *
     * @return the phone number of the student
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the student.
     *
     * @param phoneNumber the phone number of the student
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the semester of the student.
     *
     * @return the semester of the student
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the student.
     *
     * @param semester the semester of the student
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
}
