import java.io.*;
import java.util.*;

public class UniversityManagementSystem {
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
                    String semester = parts[5].substring(10).trim();  // Index 5 contains the Semester

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
                    String semester = parts[3].substring(10).trim();  // Index 5 contains the Semester

                    return new Course(id, title, semester);
                }
            }
        }
        return null; // Course with the given ID not found
    }

    // Colorize console output
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public void displayStudent(Student student) {
        if (student != null) {
            System.out.println("\nStudent Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getFullName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone Number: " + student.getPhoneNumber());
            System.out.println("Semester: " + student.getSemester());
            System.out.println("------------------------");
        } else {
            System.out.println(YELLOW + "Student not found with the given ID.\n" + RESET);
        }
    }

    public void addStudent(Scanner scanner) {
        // Read existing data from the file
        String studentsData = loadData("Students.txt");

        System.out.print("Student ID: ");
        String studentId = scanner.next();
//        scanner.nextLine(); // Consume the newline character
        // Check if the student ID already exists
        if (findStudentById(studentId, studentsData) != null) {
            System.out.println(YELLOW + "Student with the same ID already exists.\n" + RESET);
            return;
        }
        System.out.print("Full name: ");
        String studentName = scanner.next() + scanner.nextLine();
        System.out.print("E-mail: ");
        String studentEmail = scanner.next();
        System.out.print("Phone number: ");
        String studentPhone = scanner.next();
        System.out.print("Semester: ");
        String semester = scanner.next();

        Student newStudent = new Student(studentId, studentName, studentEmail, studentPhone, semester);

        // Append new student details to the data
        studentsData += newStudent.toString() + "\n";

        // Write the updated data back to the file
        saveData("Students.txt", studentsData.split("\n"));

        System.out.println(GREEN + "Student added successfully.\n" + RESET);
    }

    public void editStudentById(Scanner scanner) {
        System.out.print("Enter the student ID to edit: ");
        String studentId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("Students.txt");

        String[] newData = new String[data.length];
        boolean isEdited = false;

        for (int i = 0; i < data.length; i++) {
            String line = data[i];
            if (line.startsWith("Student")) {
                String[] parts = line.split(":|,");
                String id = parts[1].substring(4).trim();
                if (id.equals(studentId)) {
                    // Student found, modify the line here if needed
                    String name = parts[2].substring(6).trim();
                    String email = parts[3].substring(7).trim();
                    String phoneNumber = parts[4].substring(7).trim();
                    String semester = parts[5].substring(10).trim();  // Index 5 contains the Semester

                    System.out.println("Enter student details: ");
                    System.out.println("Full name: " + name);
                    System.out.print("Set new full name: ");
                    String newStudentName = scanner.next() + scanner.nextLine();
                    System.out.println("E-mail: " + email);
                    System.out.print("Set new e-mail: ");
                    String newStudentEmail = scanner.next();
                    System.out.println("Phone number: " + phoneNumber);
                    System.out.print("Set new phone number: ");
                    String newStudentPhone = scanner.next();
                    System.out.println("Semester: " + semester);
                    System.out.print("Set new semester: ");
                    String newSemester = scanner.next();

                    // Create the modified student line
                    Student editedStudent = new Student(studentId, newStudentName, newStudentEmail, newStudentPhone, newSemester);

                    newData[i] = editedStudent.toString();
                    isEdited = true;
                } else {
                    newData[i] = line;
                }
            } else {
                newData[i] = line;
            }
        }

        if (!isEdited) {
            System.out.println(YELLOW + "Student with ID " + studentId + " does not exist." + RESET);
        } else {
            saveData("Students.txt", newData);
            System.out.println(GREEN + "Student details updated successfully.\n" + RESET);
        }
    }


    public void deleteStudentById(Scanner scanner) {
        System.out.print("Enter the student ID to delete: ");
        String studentId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("Students.txt");

        // Delete the student from the data
        String[] updatedData = deleteStudent(studentId, data);

        // Delete grades assigned for the student
        deleteGradesById(studentId, true);

        // Write the updated data back to the file
        saveData("Students.txt", updatedData);

        System.out.println(RED + "Student with ID " + studentId + " has been deleted.\n" + RESET);
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
            System.out.println(YELLOW + "Teacher not found with the given ID.\n" + RESET);
        }
    }

    public void addTeacher(Scanner scanner) {
        System.out.print("Teacher ID: ");
        String teacherId = scanner.next();
        System.out.print("Full name: ");
        String teacherName = scanner.next() + scanner.nextLine();
        System.out.print("E-mail: ");
        String teacherEmail = scanner.next();
        System.out.print("Phone number: ");
        String teacherPhone = scanner.next();
        System.out.print("Specialization: ");
        String teacherSpecialization = scanner.next() + scanner.nextLine();

        Teacher newTeacher = new Teacher(teacherId, teacherName, teacherEmail, teacherPhone, teacherSpecialization);

        // Read existing data from the file
        String dataToAdd = loadData("Teachers.txt");

        // Check if the teacher ID already exists
        if (findTeacherById(newTeacher.getTeacherId(), dataToAdd) != null) {
            System.out.println(YELLOW + "Teacher with the same ID already exists.\n" + RESET);
            return;
        }

        // Append new teacher details to the data
        dataToAdd += newTeacher.toString() + "\n";

        // Write the updated data back to the file
        saveData("Teachers.txt", dataToAdd.split("\n"));

        System.out.println(GREEN + "Teacher added successfully.\n" + RESET);
    }

    public void editTeacherById(Scanner scanner) {
        System.out.print("Enter the teacher ID to edit: ");
        String teacherId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("Teachers.txt");

        String[] newData = new String[data.length];
        boolean isEdited = false;

        for (int i = 0; i < data.length; i++) {
            String line = data[i];
            if (line.startsWith("Teacher")) {
                String[] parts = line.split(":|,");
                String id = parts[1].substring(4).trim();
                if (id.equals(teacherId)) {
                    // Teacher found, modify the line here if needed
                    String name = parts[2].substring(6).trim();
                    String email = parts[3].substring(7).trim();
                    String phoneNumber = parts[4].substring(7).trim();
                    String specialization = parts[5].substring(16).trim();  // Index 5 contains the specialization

                    System.out.println("Enter teacher details: ");
                    System.out.println("Full name: " + name);
                    System.out.print("Set new full name: ");
                    String newTeacherName = scanner.next() + scanner.nextLine();
                    System.out.println("E-mail: " + email);
                    System.out.print("Set new e-mail: ");
                    String newTeacherEmail = scanner.next();
                    System.out.println("Phone number: " + phoneNumber);
                    System.out.print("Set new phone number: ");
                    String newTeacherPhone = scanner.next();
                    System.out.println("Specialization: " + specialization);
                    System.out.print("Set new specialization: ");
                    String newSpecialization = scanner.next() + scanner.nextLine();

                    // Create the modified teacher line
                    Teacher editedTeacher = new Teacher(teacherId, newTeacherName, newTeacherEmail, newTeacherPhone, newSpecialization);

                    newData[i] = editedTeacher.toString();
                    isEdited = true;
                } else {
                    newData[i] = line;
                }
            } else {
                newData[i] = line;
            }
        }

        if (!isEdited) {
            System.out.println(YELLOW + "Teacher with ID " + teacherId + " does not exist.\n" + RESET);
        } else {
            saveData("Teachers.txt", newData);
            System.out.println(GREEN + "Teacher details updated successfully.\n" + RESET);
        }
    }

    public void deleteTeacherById(Scanner scanner) {
        System.out.print("Enter the teacher ID to delete: ");
        String teacherId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("Teachers.txt");

        // Delete the teacher from the data
        String[] updatedData = deleteTeacher(teacherId, data);

        // Delete tuitions assigned for the teacher
        deleteTuitionsById(teacherId, true);

        // Write the updated data back to the file
        saveData("Teachers.txt", updatedData);

        System.out.println(RED + "Teacher with ID " + teacherId + " has been deleted." + RESET);
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
            System.out.println("\nCourse Details:");
            System.out.println("ID: " + course.getCourseId());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Semester: " + course.getSemester());
            System.out.println("------------------------");
        } else {
            System.out.println(YELLOW + "Course not found with the given ID.\n" + RESET);
        }
    }

    public void addCourse(Scanner scanner) {
        System.out.print("Course ID: ");
        String courseId = scanner.next();
        System.out.print("Title: ");
        String courseTitle = scanner.next() + scanner.nextLine();
        System.out.print("Semester: ");
        String courseSemester = scanner.next();

        Course newCourse = new Course(courseId, courseTitle, courseSemester);

        // Read existing data from the file
        String savedData = loadData("Courses.txt");

        // Check if the course ID already exists
        if (findCourseById(newCourse.getCourseId(), savedData) != null) {
            System.out.println(YELLOW + "Course with the same ID already exists.\n" + RESET);
            return;
        }

        // Append new course details to the data
        savedData += newCourse.toString() + "\n";

        // Write the updated data back to the file
        saveData("Courses.txt", savedData.split("\n"));

        System.out.println(GREEN + "Course added successfully.\n" + RESET);
    }

    public void editCourseById(Scanner scanner) {
        System.out.print("Enter the course ID to edit: ");
        String courseId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("Courses.txt");

        String[] newData = new String[data.length];
        boolean isEdited = false;

        for (int i = 0; i < data.length; i++) {
            String line = data[i];
            if (line.startsWith("Course")) {
                String[] parts = line.split(":|,");
                String id = parts[1].substring(4).trim();
                if (id.equals(courseId)) {
                    // Course found, modify the line here if needed
                    String title = parts[2].substring(7).trim();
                    String semester = parts[3].substring(10).trim();

                    System.out.println("Enter course details: ");
                    System.out.println("Title: " + title);
                    System.out.print("Set new title: ");
                    String newTitle = scanner.next() + scanner.nextLine();
                    System.out.println("Semester: " + semester);
                    System.out.print("Set new semester: ");
                    String newSemester = scanner.next();

                    // Create the modified course line
                    Course editedCourse = new Course(courseId, newTitle, newSemester);

                    newData[i] = editedCourse.toString();
                    isEdited = true;
                } else {
                    newData[i] = line;
                }
            } else {
                newData[i] = line;
            }
        }

        if (!isEdited) {
            System.out.println("Course with ID " + courseId + " does not exist.");
        } else {
            saveData("Courses.txt", newData);
            System.out.println(GREEN + "Course details updated successfully." + RESET);
        }
    }

    public void deleteCourseById(Scanner scanner) {
        System.out.print("Enter the course ID to delete: ");
        String courseId = scanner.next();

        // Read the contents of the file
        String[] data = loadDataAsArray("Courses.txt");

        // Delete the course from the data
        String[] updatedData = deleteCourse(courseId, data);

        // Delete grades assigned to the course
        deleteGradesById(courseId, false);

        // Delete tuitions assigned to the course
        deleteTuitionsById(courseId, false);

        // Write the updated data back to the file
        saveData("Courses.txt", updatedData);

        System.out.println(RED + "Course with ID " + courseId + " has been deleted.\n" + RESET);
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

    public void assignCourseToStudent(String studentId, String courseId) {
        /// Check if the student exists
        Student student = findStudentById(studentId, loadData("Students.txt"));
        if (student == null) {
            System.out.println(YELLOW + "Student not found." + RESET);
            return;
        }

        // Check if the course exists
        Course course = findCourseById(courseId, loadData("Courses.txt"));
        if (course == null) {
            System.out.println(YELLOW + "Course not found." + RESET);
            return;
        }

        // Check if the assignment exists
        if (assignmentExists(studentId, courseId, loadData("Grades.txt"))) {
            System.out.println(YELLOW + "Assignment already exists." + RESET);
            return;
        }

        // Create the course assignment entry
        String assignment = "Grade: SID=" + studentId + ", CID=" + courseId + ", Grade=";

        // Append the assignment to the "Grades.txt" file
        String[] currentData = loadDataAsArray("Grades.txt");
        String[] newData = new String[currentData.length + 1];
        System.arraycopy(currentData, 0, newData, 0, currentData.length);
        newData[currentData.length] = assignment;
        saveData("Grades.txt", newData);
        System.out.println(GREEN + "Course assignment saved successfully.\n" + RESET);
    }

    public void assignCourseToTeacher(String teacherId, String courseId) {
        /// Check if the student exists
        Teacher teacher = findTeacherById(teacherId, loadData("Teachers.txt"));
        if (teacher == null) {
            System.out.println(YELLOW + "Teacher not found." + RESET);
            return;
        }

        // Check if the course exists
        Course course = findCourseById(courseId, loadData("Courses.txt"));
        if (course == null) {
            System.out.println(YELLOW + "Course not found." + RESET);
            return;
        }

        // Check if the assignment exists
        if (assignmentExists(teacherId, courseId, loadData("Grades.txt"))) {
            System.out.println(YELLOW + "Assignment already exists." + RESET);
            return;
        }

        // Create the course assignment entry
        String assignment = "Tuition: TID=" + teacherId + ", CID=" + courseId;

        // Append the assignment to the "Tuitions.txt" file
        String[] currentData = loadDataAsArray("Tuitions.txt");
        String[] newData = new String[currentData.length + 1];
        System.arraycopy(currentData, 0, newData, 0, currentData.length);
        newData[currentData.length] = assignment;
        saveData("Tuitions.txt", newData);
        System.out.println(GREEN + "Course assignment saved successfully.\n" + RESET);
    }

    public void deleteTuitionsById(String id, boolean isTeacherId) {
        // Load existing tuitions data
        String[] tuitionsData = loadDataAsArray("Tuitions.txt");

        // Create a list to store the updated tuitions data
        List<String> updatedData = new ArrayList<>();

        // Iterate over the grades data and exclude tuitions with the specified ID
        for (String line : tuitionsData) {
            if (isTeacherId && line.contains("TID=" + id)) {
                // Skip the tuition if it matches the student ID
                continue;
            } else if (!isTeacherId && line.contains("CID=" + id)) {
                // Skip the tuition if it matches the course ID
                continue;
            }
            updatedData.add(line);
        }
        // Save the updated tuitions data
        saveData("Tuitions.txt", updatedData.toArray(new String[0]));
    }

    public void addGradeToStudent(String studentId, String courseId, double grade) {
        // Check if the student exists
        Student student = findStudentById(studentId, loadData("Students.txt"));
        if (student == null) {
            System.out.println(YELLOW + "Student not found." + RESET);
            return;
        }

        // Check if the course exists
        Course course = findCourseById(courseId, loadData("Courses.txt"));
        if (course == null) {
            System.out.println(YELLOW + "Course not found." + RESET);
            return;
        }

        // Load existing grades data
        String[] gradesData = loadDataAsArray("Grades.txt");

        // Find the assignment for the specified student ID and course ID
        int assignmentIndex = -1;
        for (int i = 0; i < gradesData.length; i++) {
            String line = gradesData[i];
            if (line.startsWith("Grade: SID=" + studentId + ", CID=" + courseId)) {
                assignmentIndex = i;
                break;
            }
        }

        // Update or add the new grade
        String newAssignment = "Grade: SID=" + studentId + ", CID=" + courseId + ", Grade=" + grade;
        if (assignmentIndex != -1) {
            // Overwrite the existing assignment with the new grade
            gradesData[assignmentIndex] = newAssignment;
            System.out.println(GREEN + "Grade updated successfully.\n" + RESET);
        } else {
            System.out.println(YELLOW + "Assignment not found.\n" + RESET);
            return;
        }

        // Save the updated grades data
        saveData("Grades.txt", gradesData);
    }

    public void deleteGradesById(String id, boolean isStudentId) {
        // Load existing grades data
        String[] gradesData = loadDataAsArray("Grades.txt");

        // Create a list to store the updated grades data
        List<String> updatedData = new ArrayList<>();

        // Iterate over the grades data and exclude grades with the specified ID
        for (String line : gradesData) {
            if (isStudentId && line.contains("SID=" + id)) {
                // Skip the grade if it matches the student ID
                continue;
            } else if (!isStudentId && line.contains("CID=" + id)) {
                // Skip the grade if it matches the course ID
                continue;
            }
            updatedData.add(line);
        }
        // Save the updated grades data
        saveData("Grades.txt", updatedData.toArray(new String[0]));

    }

    public void displayAverageGradeForStudent(String studentId, String[] data) {
        double totalGrades = 0;
        int gradeCount = 0;
        for (String line : data) {
            if (line.startsWith("Grade") && line.contains("SID=" + studentId)) {
                String[] parts = line.split(":|,");
                for (String part : parts) {
                    if (part.trim().startsWith("Grade=")) {
                        double grade;
                        try {
                            grade = Double.parseDouble(part.substring(7).trim());
                            totalGrades += grade;
                            gradeCount++;
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing grade: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        if (gradeCount > 0) {
            double averageGrade = (double) totalGrades / gradeCount;
            System.out.println(gradeCount + (gradeCount > 1 ? " grade assignments" : " grade assignment") + " found for student with ID: " + studentId);
            System.out.println("Average grade is: " + averageGrade + "\n");
        } else {
            System.out.println(YELLOW + "No grades found for the specified student ID.\n" + RESET);
        }
    }

    public void displayAverageGradeForCourse(String courseId, String[] data) {
        double totalGrades = 0;
        int gradeCount = 0;
        for (String line : data) {
            if (line.startsWith("Grade") && line.contains("CID=" + courseId)) {
                String[] parts = line.split(":|,");
                for (String part : parts) {
                    if (part.trim().startsWith("Grade=")) {
                        double grade;
                        try {
                            grade = Double.parseDouble(part.substring(7).trim());
                            totalGrades += grade;
                            gradeCount++;
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing grade: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        if (gradeCount > 0) {
            double averageGrade = (double) totalGrades / gradeCount;
            System.out.println(gradeCount + (gradeCount > 1 ? " grade assignments" : " grade assignment") + " found for course with ID: " + courseId);
            System.out.println("Average grade is: " + averageGrade + "\n");
        } else {
            System.out.println(YELLOW + "No grades found for the specified course ID." + RESET);
        }
    }

    public boolean assignmentExists(String personId, String courseId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            if (line.startsWith("Grade")) {
                String[] parts = line.split(":|,");
                String sid = parts[1].substring(5).trim(); // Student ID
                String cid = parts[2].substring(5).trim(); // Course ID

                if (sid.equals(personId) && cid.equals(courseId)) {
                    return true;
                }
            }
            if (line.startsWith("Tuition")) {
                String[] parts = line.split(":|,");
                String tid = parts[1].substring(5).trim(); // Teacher ID
                String cid = parts[2].substring(5).trim(); // Course ID

                if (tid.equals(personId) && cid.equals(courseId)) {
                    return true;
                }
            }
        }
        return false; // Assignment not found for the specified student ID and course ID
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

    public String[] loadDataAsArray(String filename) {
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
        Student student1 = new Student("1", "John Doe", "john.doe@example.com", "1234567890", "3");
        Student student2 = new Student("2", "Jane Smith", "jane.smith@example.com", "9876543210", "4");
        Student student3 = new Student("3", "David Johnson", "david.johnson@example.com", "5555555555", "2");

        // Creating sample data for teachers
        Teacher teacher1 = new Teacher("1", "Smith Jones", "prof.smith@example.com", "1111111111", "Computer Science");
        Teacher teacher2 = new Teacher("2", "Johnson Smiths", "prof.johnson@example.com", "2222222222", "Mathematics");
        Teacher teacher3 = new Teacher("3", "Lee Evans", "prof.lee@example.com", "3333333333", "Physics");

        // Creating sample data for courses
        Course course1 = new Course("1", "Introduction to Programming", "2");
        Course course2 = new Course("2", "Calculus", "3");
        Course course3 = new Course("3", "Physics Mechanics", "1");

        // Creating sample data for grades
        String grade1 = "Grade: SID=1, CID=1, Grade=5.2";
        String grade2 = "Grade: SID=1, CID=2, Grade=7.8";
        String grade3 = "Grade: SID=3, CID=2, Grade=9.4";

        // Creating sample data for tuitions
        String tuition1 = "Tuition: TID=1, CID=1";
        String tuition2 = "Tuition: TID=2, CID=3";
        String tuition3 = "Tuition: TID=3, CID=2";

        // Convert the data to string representation
        String[] studentData = {
                student1.toString(),
                student2.toString(),
                student3.toString()
        };

        String[] teacherData = {
                teacher1.toString(),
                teacher2.toString(),
                teacher3.toString()
        };

        String[] courseData = {
                course1.toString(),
                course2.toString(),
                course3.toString()
        };

        String[] gradeData = {
                grade1,
                grade2,
                grade3
        };

        String[] tuitionData = {
                tuition1,
                tuition2,
                tuition3
        };

        // Save the data to a file
        saveData("Students.txt", studentData);
        saveData("Teachers.txt", teacherData);
        saveData("Courses.txt", courseData);
        saveData("Grades.txt", gradeData);
        saveData("Tuitions.txt", tuitionData);
    }

}
