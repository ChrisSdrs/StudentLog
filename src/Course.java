public class Course {
    private String courseId;
    private String title;
    private String semester;

    /**
     * Constructs a Course object with the specified course ID, title, and semester.
     *
     * @param courseId the ID of the course
     * @param title    the title of the course
     * @param semester the semester in which the course is offered
     */
    public Course(String courseId, String title, String semester) {
        this.courseId = courseId;
        this.title = title;
        this.semester = semester;
    }

    /**
     * Default constructor for the Course class.
     * Creates an empty Course object with no initial values for the attributes.
     */
    public Course() {
    }

    /**
     * Returns a string representation of the Course object.
     *
     * @return a string representation of the Course object
     */
    @Override
    public String toString() {
        return "Course: ID=" + courseId + ", Title=" + title + ", Semester=" + semester;
    }

    // Accessor methods (getters and setters)

    /**
     * Returns the ID of the course.
     *
     * @return the ID of the course
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course.
     *
     * @param courseId the ID of the course
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns the title of the course.
     *
     * @return the title of the course
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title the title of the course
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the semester in which the course is offered.
     *
     * @return the semester of the course
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester in which the course is offered.
     *
     * @param semester the semester of the course
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
}
