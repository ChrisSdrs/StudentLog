public class Teacher {
    private String teacherId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String specialization;

    /**
     * Constructs a Teacher object with the specified teacher ID, full name, email, phone number, and specialization.
     *
     * @param teacherId      the ID of the teacher
     * @param fullName       the full name of the teacher
     * @param email          the email of the teacher
     * @param phoneNumber    the phone number of the teacher
     * @param specialization the specialization of the teacher
     */
    public Teacher(String teacherId, String fullName, String email, String phoneNumber, String specialization) {
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    /**
     * Default constructor for the Teacher class.
     * Creates an empty Teacher object with no initial values for the attributes.
     */
    public Teacher() {
    }

    /**
     * Returns a string representation of the Teacher object.
     *
     * @return a string representation of the Teacher object
     */
    @Override
    public String toString() {
        return "Teacher: ID=" + teacherId + ", Name=" + fullName + ", Email=" + email + ", Phone=" + phoneNumber + ", Specialization=" + specialization;
    }

    /**
     * Returns the ID of the teacher.
     *
     * @return the ID of the teacher
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * Sets the ID of the teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * Returns the full name of the teacher.
     *
     * @return the full name of the teacher
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the teacher.
     *
     * @param fullName the full name of the teacher
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the email of the teacher.
     *
     * @return the email of the teacher
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the teacher.
     *
     * @param email the email of the teacher
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the teacher.
     *
     * @return the phone number of the teacher
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the teacher.
     *
     * @param phoneNumber the phone number of the teacher
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the specialization of the teacher.
     *
     * @return the specialization of the teacher
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the teacher.
     *
     * @param specialization the specialization of the teacher
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
