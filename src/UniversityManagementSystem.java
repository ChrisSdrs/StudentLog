import java.io.*;
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

    public Object findById(String givenId, String searchKey, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            // Student
            if (line.startsWith(searchKey) && searchKey.equals("Student")) {
                String[] parts = line.split(":|,");  // Split using ':' or ',' as separators
                String id;
                id = parts[1].substring(4).trim();  // Index 1 contains the ID

                if (id.equals(givenId)) {
                    String name = parts[2].substring(6).trim();  // Index 2 contains the Name
                    String email = parts[3].substring(7).trim();  // Index 3 contains the Email
                    String phoneNumber = parts[4].substring(8).trim();  // Index 4 contains the Phone number
                    int semester;
                    try {
                        semester = Integer.parseInt(parts[5].substring(10).trim());  // Index 5 contains the Semester
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing student semester: " + e.getMessage());
                        e.printStackTrace();
                        continue; // Skip this line if grade is not a valid integer
                    }
                    return new Student(id, name, email, phoneNumber, semester);
                }
            }
            // Teacher
            if (line.startsWith(searchKey) && searchKey.equals("Teacher")) {
                String[] parts = line.split(":|,");  // Split using ':' or ',' as separators
                String id;
                id = parts[1].substring(4).trim();  // Index 1 contains the ID

                if (id.equals(givenId)) {
                    String name = parts[2].substring(6).trim();  // Index 2 contains the Name
                    String email = parts[3].substring(7).trim();  // Index 3 contains the Email
                    String phoneNumber = parts[4].substring(8).trim();  // Index 4 contains the Phone number
                    String specialization = parts[5].substring(16).trim();  // Index 5 contains the Specialization
                    return new Teacher(id, name, email, phoneNumber, specialization);
                }
            }
        }
        return null; // Student with the given ID not found
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

    public Teacher findTeacherById(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(teacherId)) {
                return teacher;
            }
        }
        return null;
    }

    public Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
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

    public void displayStudent(Student student) {
        if (student != null) {
            System.out.println("Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getFullName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone Number: " + student.getPhoneNumber());
            System.out.println("Semester: " + student.getSemester());
            System.out.println("------------------------");
        } else {
            System.out.println("Student not found with the given ID.\n");
        }
    }

    public void displayTeacher(Teacher teacher) {
        if (teacher != null) {
            System.out.println("Teacher Details:");
            System.out.println("ID: " + teacher.getTeacherId());
            System.out.println("Name: " + teacher.getFullName());
            System.out.println("Email: " + teacher.getEmail());
            System.out.println("Phone Number: " + teacher.getPhoneNumber());
            System.out.println("Specialization: " + teacher.getSpecialization());
            System.out.println("------------------------");
        } else {
            System.out.println("Teacher not found with the given ID.\n");
        }
    }

    public static void saveData(String filename, String[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadData(String filename) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public void dataInject() {
        // Creating sample data for students
        Student student1 = new Student("1", "John Doe", "john.doe@example.com", "1234567890", 3);
        Student student2 = new Student("2", "Jane Smith", "jane.smith@example.com", "9876543210", 4);
        Student student3 = new Student("3", "David Johnson", "david.johnson@example.com", "5555555555", 2);

        // Creating sample data for teachers
        Teacher teacher1 = new Teacher("1", "Smith Jones", "prof.smith@example.com", "1111111111", "Computer Science");
        Teacher teacher2 = new Teacher("2", "Johnson Smiths", "prof.johnson@example.com", "2222222222", "Mathematics");
        Teacher teacher3 = new Teacher("3", "Lee Evans", "prof.lee@example.com", "3333333333", "Physics");

        // Creating sample data for courses
        Course course1 = new Course("1", "Introduction to Programming", 2);
        Course course2 = new Course("2", "Calculus", 3);
        Course course3 = new Course("3", "Physics Mechanics", 1);


        // Convert the data to string representation
        String[] data = {
                student1.toString(),
                student2.toString(),
                student3.toString(),
                teacher1.toString(),
                teacher2.toString(),
                teacher3.toString(),
                course1.toString(),
                course2.toString(),
                course3.toString()
        };

        // Save the data to a file
        saveData("data.txt", data);


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
