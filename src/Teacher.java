public class Teacher {
    private String teacherId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String specialization;

    //  Constructor
    public Teacher(String teacherId, String fullName, String email, String phoneNumber, String specialization) {
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    @Override
    public String toString () {
        return "Teacher: ID=" + teacherId + ", Name=" + fullName + ", Email=" + email + ", Phone=" + phoneNumber + ", Specialization=" + specialization;
    }

    // Accessor methods (getters and setters)
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
