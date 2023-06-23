import java.io.*;
import java.util.*;

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

    public Student findStudentById(String studentId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            // Student
            if (line.startsWith("Student")) {
                String[] parts = line.split(":|,");  // Split using ':' or ',' as separators
                String id = parts[1].substring(4).trim();  // Index 1 contains the ID

                if (id.equals(studentId)) {
                    String name = parts[2].substring(6).trim();  // Index 2 contains the Name
                    String email = parts[3].substring(7).trim();  // Index 3 contains the Email
                    String phoneNumber = parts[4].substring(7).trim();  // Index 4 contains the Phone number
                    int semester;
                    try {
                        semester = Integer.parseInt(parts[5].substring(10).trim());  // Index 5 contains the Semester
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing student semester: " + e.getMessage());
                        e.printStackTrace();
                        continue; // Skip this line if semester is not a valid integer
                    }
                    return new Student(id, name, email, phoneNumber, semester);
                }
            }
        }
        return null; // Student with the given ID not found
    }

    public Teacher findTeacherById(String teacherId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            // Teacher
            if (line.startsWith("Teacher")) {
                String[] parts = line.split(":|,");  // Split using ':' or ',' as separators
                String id;
                id = parts[1].substring(4).trim();  // Index 1 contains the ID

                if (id.equals(teacherId)) {
                    String name = parts[2].substring(6).trim();  // Index 2 contains the Name
                    String email = parts[3].substring(7).trim();  // Index 3 contains the Email
                    String phoneNumber = parts[4].substring(7).trim();  // Index 4 contains the Phone number
                    String specialization = parts[5].substring(16).trim();  // Index 5 contains the Specialization
                    return new Teacher(id, name, email, phoneNumber, specialization);
                }
            }
        }
        return null; // Teacher with the given ID not found
    }

    public Course findCourseById(String courseId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            if (line.startsWith("Course")) {
                String[] parts = line.split(":|,");  // Split using ':' or ',' as separators
                String id;
                id = parts[1].substring(4).trim();  // Index 1 contains the ID

                if (id.equals(courseId)) {
                    String title = parts[2].substring(7).trim();  // Index 3 contains the Title
                    int semester;
                    try {
                        semester = Integer.parseInt(parts[3].substring(10).trim());  // Index 5 contains the Semester
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing student semester: " + e.getMessage());
                        e.printStackTrace();
                        continue; // Skip this line if semester is not a valid integer
                    }
                    return new Course(id, title, semester);
                }
            }
        }
        return null; // Course with the given ID not found
    }

//    // Add student
//    public void addStudent(Student student) {
//        students.add(student);
//    }
//
//    // Add  teacher
//    public void addTeacher(Teacher teacher) {
//        teachers.add(teacher);
//    }
//
//    // Add course
//    public void addCourse(Course course) {
//        courses.add(course);
//    }

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

//    public Teacher findTeacherById(String teacherId) {
//        for (Teacher teacher : teachers) {
//            if (teacher.getTeacherId().equals(teacherId)) {
//                return teacher;
//            }
//        }
//        return null;
//    }

//    public Course findCourseById(String courseId) {
//        for (Course course : courses) {
//            if (course.getCourseId().equals(courseId)) {
//                return course;
//            }
//        }
//        return null;
//    }

//    public Student findStudentById(String studentId) {
//        for (Student student : students) {
//            if (student.getStudentId().equals(studentId)) {
//                return student;
//            }
//        }
//        return null;
//    }

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

    public void addStudent(Scanner scanner) {
        System.out.print("Student ID: ");
        String studentId = scanner.next();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Full name: ");
        String studentName = scanner.next() + scanner.nextLine();
        System.out.print("E-mail: ");
        String studentEmail = scanner.nextLine();
        System.out.print("Phone Number: ");
        String studentPhone = scanner.nextLine();
        System.out.print("Semester: ");
        int semester = scanner.nextInt();

        Student newStudent = new Student(studentId, studentName, studentEmail, studentPhone, semester);

        // Read existing data from the file
        String dataToAdd = loadData("data.txt");

        // Check if the student ID already exists
        if (findStudentById(newStudent.getStudentId(), dataToAdd) != null) {
            System.out.println("Student with the same ID already exists.\n");
            return;
        }

        // Append new student details to the data
        dataToAdd += newStudent.toString() + "\n";

        // Write the updated data back to the file
        saveData("data.txt", dataToAdd.split("\n"));

        System.out.println("Student added successfully.\n");
    }

    public void deleteStudentById(Scanner scanner) {
        System.out.print("Enter the student ID to delete: ");
        String studentId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("data.txt");

        // Delete the student from the data
        String[] updatedData = deleteStudent(studentId, data);

        // Write the updated data back to the file
        saveData("data.txt", updatedData);

        System.out.println("Student with ID " + studentId + " has been deleted.");
    }

    public static String[] deleteStudent(String studentId, String[] data) {
        StringBuilder newData = new StringBuilder();
        for (String line : data) {
            if (line.startsWith("Student")) {
                String[] parts = line.split(":|,");
                String id = parts[1].substring(4).trim();
                if (!id.equals(studentId)) {
                    newData.append(line).append("\n");
                }
            } else {
                newData.append(line).append("\n");
            }
        }
        return newData.toString().split("\n");
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

    public void addTeacher(Scanner scanner) {
        System.out.print("Teacher ID: ");
        String teacherId = scanner.next();
        System.out.print("Full name: ");
        String teacherName = scanner.next() + scanner.nextLine();
        System.out.print("E-mail: ");
        String teacherEmail = scanner.next();
        System.out.print("Phone Number: ");
        String teacherPhone = scanner.next();
        System.out.print("Specialty: ");
        String teacherSpecialty = scanner.next();

        Teacher newTeacher = new Teacher(teacherId, teacherName, teacherEmail, teacherPhone, teacherSpecialty);

        // Read existing data from the file
        String dataToAdd = loadData("data.txt");

        // Check if the teacher ID already exists
        if (findTeacherById(newTeacher.getTeacherId(), dataToAdd) != null) {
            System.out.println("Teacher with the same ID already exists.\n");
            return;
        }

        // Append new teacher details to the data
        dataToAdd += newTeacher.toString() + "\n";

        // Write the updated data back to the file
        saveData("data.txt", dataToAdd.split("\n"));

        System.out.println("Teacher added successfully.\n");
    }

    public void deleteTeacherById(Scanner scanner) {
        System.out.print("Enter the teacher ID to delete: ");
        String teacherId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("data.txt");

        // Delete the teacher from the data
        String[] updatedData = deleteTeacher(teacherId, data);

        // Write the updated data back to the file
        saveData("data.txt", updatedData);

        System.out.println("Teacher with ID " + teacherId + " has been deleted.");
    }

    public static String[] deleteTeacher(String teacherId, String[] data) {
        StringBuilder newData = new StringBuilder();
        for (String line : data) {
            if (line.startsWith("Teacher")) {
                String[] parts = line.split(":|,");
                String id = parts[1].substring(4).trim();
                if (!id.equals(teacherId)) {
                    newData.append(line).append("\n");
                }
            } else {
                newData.append(line).append("\n");
            }
        }
        return newData.toString().split("\n");
    }

    public void displayCourse(Course course) {
        if (course != null) {
            System.out.println("Course Details:");
            System.out.println("ID: " + course.getCourseId());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Semester: " + course.getSemester());
            System.out.println("------------------------");
        } else {
            System.out.println("Teacher not found with the given ID.\n");
        }
    }

    public void addCourse(Scanner scanner) {
        System.out.print("Course ID: ");
        String courseId = scanner.next();
        System.out.print("Title: ");
        String courseTitle = scanner.next() + scanner.nextLine();
        System.out.print("Semester: ");
        int courseSemester = scanner.nextInt();

        Course newCourse = new Course(courseId, courseTitle, courseSemester);

        // Read existing data from the file
        String dataToAdd = loadData("data.txt");

        // Check if the course ID already exists
        if (findTeacherById(newCourse.getCourseId(), dataToAdd) != null) {
            System.out.println("Course with the same ID already exists.\n");
            return;
        }

        // Append new course details to the data
        dataToAdd += newCourse.toString() + "\n";

        // Write the updated data back to the file
        saveData("data.txt", dataToAdd.split("\n"));

        System.out.println("Course added successfully.\n");
    }

    public void deleteCourseById(Scanner scanner) {
        System.out.print("Enter the course ID to delete: ");
        String courseId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("data.txt");

        // Delete the course from the data
        String[] updatedData = deleteCourse(courseId, data);

        // Write the updated data back to the file
        saveData("data.txt", updatedData);

        System.out.println("Course with ID " + courseId + " has been deleted.");
    }

    public static String[] deleteCourse(String courseId, String[] data) {
        StringBuilder newData = new StringBuilder();
        for (String line : data) {
            if (line.startsWith("Course")) {
                String[] parts = line.split(":|,");
                String id = parts[1].substring(4).trim();
                if (!id.equals(courseId)) {
                    newData.append(line).append("\n");
                }
            } else {
                newData.append(line).append("\n");
            }
        }
        return newData.toString().split("\n");
    }

    public void saveData(String filename, String[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void saveStudentGradesToFile(Map<Student, Map<Course, Double>> studentGrades, String filename) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//            for (Map.Entry<Student, Map<Course, Double>> entry : studentGrades.entrySet()) {
//                Student student = entry.getKey();
//                Map<Course, Double> grades = entry.getValue();
//
//                // Write student details
//                writer.write(student.toString());
//                writer.newLine();
//
//                // Write course grades
//                for (Map.Entry<Course, Double> gradeEntry : grades.entrySet()) {
//                    Course course = gradeEntry.getKey();
//                    Double grade = gradeEntry.getValue();
//                    writer.write(course.toString() + "," + grade);
//                    writer.newLine();
//                }
//            }
//            System.out.println("Student grades saved to file: " + filename);
//        } catch (IOException e) {
//            System.out.println("An error occurred while saving student grades: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

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

    public static String[] loadDataAsArray(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
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


//        // Adding students to the university management system
//        addStudent(student1);
//        addStudent(student2);
//        addStudent(student3);
//
//        // Adding teachers to the university management system
//        addTeacher(teacher1);
//        addTeacher(teacher2);
//        addTeacher(teacher3);
//
//        // Adding courses to the university management system
//        addCourse(course1);
//        addCourse(course2);
//        addCourse(course3);

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
