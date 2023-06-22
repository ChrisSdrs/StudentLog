import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversityManagementSystem {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;
    private Map<Teacher, List<Course>> teacherCourses;
    private Map<Student, Map<Course, Double>> studentGrades;

    public UniversityManagementSystem() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        courses = new ArrayList<>();
        teacherCourses = new HashMap<>();
        studentGrades = new HashMap<>();
    }

    // Add student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Add  teacher
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    // Add course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Assign course to teacher
    public void assignCourseToTeacher(Teacher teacher, Course course) {
        List<Course> assignedCourses = teacherCourses.getOrDefault(teacher, new ArrayList<>());
        assignedCourses.add(course);
        teacherCourses.put(teacher, assignedCourses);
    }

    // Assign course to student
    public void assignCourseToStudent(Student student, Course course) {
        Map<Course, Double> studentCourseGrades = studentGrades.getOrDefault(student, new HashMap<>());
        studentCourseGrades.put(course, 0.0); // Initial grade: 0.0
        studentGrades.put(student, studentCourseGrades);
    }

    // Record grade for student
    public void addGradeToStudent(Student student, Course course, double grade) {
        if (studentGrades.containsKey(student)) {
            Map<Course, Double> studentCourseGrades = studentGrades.get(student);
            if (studentCourseGrades.containsKey(course)) {
                studentCourseGrades.put(course, grade);
            }
        }
    }

    // Calculate student's grade average
    public double calculateAverageGradeForStudent(Student student) {
        if (studentGrades.containsKey(student)) {
            Map<Course, Double> studentCourseGrades = studentGrades.get(student);
            double sum = 0.0;
            int count = 0;
            for (double grade : studentCourseGrades.values()) {
                sum += grade;
                count++;
            }
            if (count > 0) {
                return sum / count;
            }
        }
        return 0.0;
    }

    // Calculate average grade per course
    public double calculateAverageGradeForCourse(Course course) {
        double sum = 0.0;
        int count = 0;
        for (Map<Course, Double> studentCourseGrades : studentGrades.values()) {
            if (studentCourseGrades.containsKey(course)) {
                sum += studentCourseGrades.get(course);
                count++;
            }
        }
        if (count > 0) {
            return sum / count;
        }
        return 0.0;
    }

    public Teacher findTeacherById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    public Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void addGrade(Student student, Course course, double grade) {
        student.addGrade(course, grade);
    }

    public double calculateStudentAverage(Student student) {
        Map<Course, Double> grades = student.getGrades();
        int numOfCourses = grades.size();
        if (numOfCourses == 0) {
            return 0.0; // No grades available
        }

        double totalGrade = 0.0;
        for (double grade : grades.values()) {
            totalGrade += grade;
        }

        return totalGrade / numOfCourses;
    }

    public double calculateCourseAverage(Course course) {
        List<Double> grades = new ArrayList<>();
        for (Student student : students) {
            double grade = student.getGradeForCourse(course);
            if (grade != 0.0) {
                grades.add(grade);
            }
        }

        int numOfStudents = grades.size();
        if (numOfStudents == 0) {
            return 0.0; // No grades available for the course
        }

        double totalGrade = 0.0;
        for (double grade : grades) {
            totalGrade += grade;
        }

        return totalGrade / numOfStudents;
    }

    public void dataInject(){
        // Creating sample data for students
        Student student1 = new Student(1, "John Doe", "john.doe@example.com", "1234567890", 3);
        Student student2 = new Student(2, "Jane Smith", "jane.smith@example.com", "9876543210", 4);
        Student student3 = new Student(3, "David Johnson", "david.johnson@example.com", "5555555555", 2);

        // Creating sample data for teachers
        Teacher teacher1 = new Teacher(1, "Smith Jones", "prof.smith@example.com", "1111111111", "Computer Science");
        Teacher teacher2 = new Teacher(2, "Johnson Smiths", "prof.johnson@example.com", "2222222222", "Mathematics");
        Teacher teacher3 = new Teacher(3, "Lee Evans", "prof.lee@example.com", "3333333333", "Physics");

        // Creating sample data for courses
        Course course1 = new Course(1, "Introduction to Programming", 2);
        Course course2 = new Course(2, "Calculus", 3);
        Course course3 = new Course(3, "Physics Mechanics", 1);

        // Adding students to the university management system
        addStudent(student1);
        addStudent(student2);
        addStudent(student3);

        // Adding teachers to the university management system
        addTeacher(teacher1);
        addTeacher(teacher2);
        addTeacher(teacher3);

        // Adding courses to the university management system
        addCourse(course1);
        addCourse(course2);
        addCourse(course3);

        // Assigning grades for students
        student1.addGrade(course1, 9.0);
        student1.addGrade(course2, 8.5);
        student2.addGrade(course1, 7.5);
        student2.addGrade(course3, 6.0);
        student3.addGrade(course2, 9.5);
        student3.addGrade(course3, 8.0);

        // Assigning courses to teachers
        teacher1.addCourse(course1);
        teacher2.addCourse(course2);
        teacher3.addCourse(course3);
    }

}
