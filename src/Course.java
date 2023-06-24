public class Course {
    private String courseId;
    private String title;
    private String semester;

    // Constructor
    public Course(String courseId, String title, String semester) {
        this.courseId = courseId;
        this.title = title;
        this.semester = semester;
    }

    @Override
    public String toString () {
        return "Course: ID=" + courseId + ", Title=" + title + ", Semester=" + semester;
    }

    // Accessor methods (getters and setters)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
