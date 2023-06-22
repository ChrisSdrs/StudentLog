public class Course {
    private int courseId;
    private String title;
    private int semester;

    // Constructor
    public Course(int courseId, String title, int semester) {
        this.courseId = courseId;
        this.title = title;
        this.semester = semester;
    }

    // Accessor methods (getters and setters)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
