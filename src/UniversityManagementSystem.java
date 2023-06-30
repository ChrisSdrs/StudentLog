import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniversityManagementSystem {

    /**
     * Finds a student in the provided data based on the student ID.
     *
     * @param studentId The ID of the student to find.
     * @param data      The data containing student information.
     * @return The found Student object, or null if not found.
     */
    public Student findStudentById(String studentId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            // Student
            if (line.startsWith("Student")) {
                String[] parts = line.split("[:,]");  // Split using ':' or ',' as separators
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

    /**
     * Finds a teacher in the provided data based on the teacher ID.
     *
     * @param teacherId The ID of the teacher to find.
     * @param data      The data containing teacher information.
     * @return The found Teacher object, or null if not found.
     */
    public Teacher findTeacherById(String teacherId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            // Teacher
            if (line.startsWith("Teacher")) {
                String[] parts = line.split("[:,]");  // Split using ':' or ',' as separators
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

    /**
     * Finds a course in the provided data based on the course ID.
     *
     * @param courseId The ID of the course to find.
     * @param data     The data containing course information.
     * @return The found Course object, or null if not found.
     */
    public Course findCourseById(String courseId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            if (line.startsWith("Course")) {
                String[] parts = line.split("[:,]");  // Split using ':' or ',' as separators
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

    /**
     * Displays the details of a student.
     *
     * @param student The Student object to display.
     */
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

    /**
     * Adds a new student to the system.
     *
     * @param scanner The Scanner object for user input.
     */
    public void addStudent(Scanner scanner) {
        // Read existing data from the file
        String studentsData = loadData("Students.txt");

        System.out.print("Student ID: ");
        String studentId = validateNumber(scanner.next(), "", scanner);
        // Check if the student ID already exists
        while (findStudentById(studentId, studentsData) != null) {
            System.out.println(YELLOW + "Student with the same ID already exists." + RESET);
            System.out.print("Enter new student ID: ");
            studentId = scanner.next();
        }
        System.out.print("Full name: ");
        String studentName = validateName(scanner.next() + scanner.nextLine(), scanner);
        System.out.print("E-mail: ");
        String studentEmail = validateEmail(scanner.next(), scanner);
        System.out.print("Phone number: ");
        String studentPhone = validateNumber(scanner.next(), "phone", scanner);
        System.out.print("Semester: ");
        String semester = validateNumber(scanner.next(), "semester", scanner);

        Student newStudent = new Student();
        newStudent.setStudentId(studentId);
        newStudent.setFullName(studentName);
        newStudent.setEmail(studentEmail);
        newStudent.setPhoneNumber(studentPhone);
        newStudent.setSemester(semester);

        // Append new student details to the data
        studentsData += newStudent + "\n";

        // Write the updated data back to the file
        saveData("Students.txt", studentsData.split("\n"));

        System.out.println(GREEN + "Student added successfully.\n" + RESET);
    }

    /**
     * Edits the details of a student based on the student ID.
     *
     * @param scanner The Scanner object for user input.
     */
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
                String[] parts = line.split("[:,]");
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
                    String newStudentName = validateName(scanner.next() + scanner.nextLine(), scanner);
                    System.out.println("E-mail: " + email);
                    System.out.print("Set new e-mail: ");
                    String newStudentEmail = validateEmail(scanner.next(), scanner);
                    System.out.println("Phone number: " + phoneNumber);
                    System.out.print("Set new phone number: ");
                    String newStudentPhone = validateNumber(scanner.next(), "phone", scanner);
                    System.out.println("Semester: " + semester);
                    System.out.print("Set new semester: ");
                    String newSemester = validateNumber(scanner.next(), "semester", scanner);

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

    /**
     * Deletes a student from the system based on the student ID.
     *
     * @param scanner The Scanner object for user input.
     */
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

    /**
     * Deletes a student from the given data array based on the student ID.
     *
     * @param studentId The ID of the student to delete.
     * @param data      The array containing the student data.
     * @return An array with the student removed.
     */
    public static String[] deleteStudent(String studentId, String[] data) {
        StringBuilder newData = new StringBuilder();
        for (String line : data) {
            if (line.startsWith("Student")) {
                String[] parts = line.split("[:,]");
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

    /**
     * Displays the details of a teacher.
     *
     * @param teacher The Teacher object to display.
     */
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

    /**
     * Adds a new teacher to the system.
     *
     * @param scanner The Scanner object for user input.
     */
    public void addTeacher(Scanner scanner) {
        // Read existing data from the file
        String teachersData = loadData("Teachers.txt");

        System.out.print("Teacher ID: ");
        String teacherId = validateNumber(scanner.next(), "", scanner);
        // Check if the teacher ID already exists
        while (findTeacherById(teacherId, teachersData) != null) {
            System.out.println(YELLOW + "Teacher with the same ID already exists." + RESET);
            System.out.print("Enter new teacher ID: ");
            teacherId = scanner.next();
        }
        System.out.print("Full name: ");
        String teacherName = validateName(scanner.next() + scanner.nextLine(), scanner);
        System.out.print("E-mail: ");
        String teacherEmail = validateEmail(scanner.next(), scanner);
        System.out.print("Phone number: ");
        String teacherPhone = validateNumber(scanner.next(), "phone", scanner);
        System.out.print("Specialization: ");
        String teacherSpecialization = validateName(scanner.next() + scanner.nextLine(), scanner);

        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherId(teacherId);
        newTeacher.setFullName(teacherName);
        newTeacher.setEmail(teacherEmail);
        newTeacher.setPhoneNumber(teacherPhone);
        newTeacher.setSpecialization(teacherSpecialization);

        // Read existing data from the file
        String dataToAdd = loadData("Teachers.txt");

        // Append new teacher details to the data
        dataToAdd += newTeacher + "\n";

        // Write the updated data back to the file
        saveData("Teachers.txt", dataToAdd.split("\n"));

        System.out.println(GREEN + "Teacher added successfully.\n" + RESET);
    }

    /**
     * Edits the details of a teacher based on the teacher ID.
     *
     * @param scanner The Scanner object for user input.
     */
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
                String[] parts = line.split("[:,]");
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
                    String newTeacherName = validateName(scanner.next() + scanner.nextLine(), scanner);
                    System.out.println("E-mail: " + email);
                    System.out.print("Set new e-mail: ");
                    String newTeacherEmail = validateEmail(scanner.next(), scanner);
                    System.out.println("Phone number: " + phoneNumber);
                    System.out.print("Set new phone number: ");
                    String newTeacherPhone = validateNumber(scanner.next(), "phone", scanner);
                    System.out.println("Specialization: " + specialization);
                    System.out.print("Set new specialization: ");
                    String newSpecialization = validateName(scanner.next() + scanner.nextLine(), scanner);

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

    /**
     * Deletes a teacher from the system based on the teacher ID.
     *
     * @param scanner The Scanner object for user input.
     */
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

    /**
     * Deletes a teacher from the given data array based on the teacher ID.
     *
     * @param teacherId The ID of the teacher to delete.
     * @param data      The array containing the teacher data.
     * @return An array with the teacher removed.
     */
    public static String[] deleteTeacher(String teacherId, String[] data) {
        StringBuilder newData = new StringBuilder();
        for (String line : data) {
            if (line.startsWith("Teacher")) {
                String[] parts = line.split("[:,]");
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

    /**
     * Displays the details of a course.
     *
     * @param course The Course object to display.
     */
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

    /**
     * Adds a new course to the system.
     *
     * @param scanner The Scanner object for user input.
     */
    public void addCourse(Scanner scanner) {
        System.out.print("Course ID: ");
        String courseId = validateNumber(scanner.next(), "", scanner);
        System.out.print("Title: ");
        String courseTitle = scanner.next() + scanner.nextLine();
        System.out.print("Semester: ");
        String courseSemester = validateNumber(scanner.next(), "semester", scanner);

        Course newCourse = new Course();
        newCourse.setCourseId(courseId);
        newCourse.setTitle(courseTitle);
        newCourse.setSemester(courseSemester);

        // Read existing data from the file
        String savedData = loadData("Courses.txt");

        // Check if the course ID already exists
        if (findCourseById(newCourse.getCourseId(), savedData) != null) {
            System.out.println(YELLOW + "Course with the same ID already exists." + RESET);
            return;
        }

        // Append new course details to the data
        savedData += newCourse + "\n";

        // Write the updated data back to the file
        saveData("Courses.txt", savedData.split("\n"));

        System.out.println(GREEN + "Course added successfully.\n" + RESET);
    }

    /**
     * Edits the details of a course based on the course ID.
     *
     * @param scanner The Scanner object for user input.
     */
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
                String[] parts = line.split("[:,]");
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
                    String newSemester = validateNumber(scanner.next(), "semester", scanner);

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

    /**
     * Deletes a course from the system based on the course ID.
     *
     * @param scanner The Scanner object for user input.
     */
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

    /**
     * Deletes a course from the given data array based on the course ID.
     *
     * @param courseId The ID of the course to delete.
     * @param data     The array containing the course data.
     * @return An array with the course removed.
     */
    public static String[] deleteCourse(String courseId, String[] data) {
        StringBuilder newData = new StringBuilder();
        for (String line : data) {
            if (line.startsWith("Course")) {
                String[] parts = line.split("[:,]");
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

    /**
     * Assigns a course to a student.
     *
     * @param studentId The ID of the student.
     * @param courseId  The ID of the course.
     */
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

    /**
     * Assigns a course to a teacher.
     *
     * @param teacherId The ID of the teacher.
     * @param courseId  The ID of the course.
     */
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

    /**
     * Deletes tuitions from the "Tuitions.txt" file based on the specified ID.
     *
     * @param id          The ID (teacher ID or course ID) to delete tuitions for.
     * @param isTeacherId A flag indicating whether the ID corresponds to a teacher.
     */
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

    /**
     * Adds a grade for a student in a course.
     *
     * @param studentId The ID of the student.
     * @param courseId  The ID of the course.
     * @param grade     The grade to be added.
     */
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

    /**
     * Deletes grades from the "Grades.txt" file based on the specified ID.
     *
     * @param id          The ID (student ID or course ID) to delete grades for.
     * @param isStudentId A flag indicating whether the ID corresponds to a student.
     */
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

    /**
     * Displays the average grade for a student.
     *
     * @param studentId The ID of the student.
     * @param data      The array containing the grade data.
     */
    public void displayAverageGradeForStudent(String studentId, String[] data) {
        double totalGrades = 0;
        int gradeCount = 0;
        for (String line : data) {
            if (line.startsWith("Grade") && line.contains("SID=" + studentId)) {
                String[] parts = line.split("[:,]");
                for (String part : parts) {
                    if (part.trim().startsWith("Grade=")) {
                        double grade;
                        String gradeStr = part.substring(7).trim();
                        if (gradeStr.equals("")) {
                            break;
                        }
                        grade = Double.parseDouble(gradeStr);
                        totalGrades += grade;
                        gradeCount++;
                        break;
                    }
                }
            }
        }

        if (gradeCount > 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String averageGrade = decimalFormat.format(totalGrades / gradeCount);
            System.out.println(gradeCount + (gradeCount > 1 ? " grade assignments" : " grade assignment") + " found for student with ID: " + studentId);
            System.out.println("Average grade is: " + averageGrade + "\n");
        } else {
            System.out.println(YELLOW + "No grades found for the specified student ID.\n" + RESET);
        }
    }

    /**
     * Displays the average grade for a course.
     *
     * @param courseId The ID of the course.
     * @param data     The array containing the grade data.
     */
    public void displayAverageGradeForCourse(String courseId, String[] data) {
        double totalGrades = 0;
        int gradeCount = 0;
        for (String line : data) {
            if (line.startsWith("Grade") && line.contains("CID=" + courseId)) {
                String[] parts = line.split("[:,]");
                for (String part : parts) {
                    if (part.trim().startsWith("Grade=")) {
                        double grade;
                        String gradeStr = part.substring(7).trim();
                        if (gradeStr.equals("")) {
                            break;
                        }
                        grade = Double.parseDouble(gradeStr);
                        totalGrades += grade;
                        gradeCount++;
                        break;
                    }
                }
            }
        }

        if (gradeCount > 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String averageGrade = decimalFormat.format(totalGrades / gradeCount);
            System.out.println(gradeCount + (gradeCount > 1 ? " grade assignments" : " grade assignment") + " found for course with ID: " + courseId);
            System.out.println("Average grade is: " + averageGrade + "\n");
        } else {
            System.out.println(YELLOW + "No grades found for the specified course ID." + RESET);
        }
    }

    /**
     * Checks if an assignment (grade or tuition) exists for the specified person ID and course ID.
     *
     * @param personId The ID of the person (student ID or teacher ID).
     * @param courseId The ID of the course.
     * @param data     The data to search for the assignment.
     * @return {@code true} if the assignment exists, {@code false} otherwise.
     */
    public boolean assignmentExists(String personId, String courseId, String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            if (line.startsWith("Grade")) {
                String[] parts = line.split("[:,]");
                String sid = parts[1].substring(5).trim(); // Student ID
                String cid = parts[2].substring(5).trim(); // Course ID

                if (sid.equals(personId) && cid.equals(courseId)) {
                    return true;
                }
            }
            if (line.startsWith("Tuition")) {
                String[] parts = line.split("[:,]");
                String tid = parts[1].substring(5).trim(); // Teacher ID
                String cid = parts[2].substring(5).trim(); // Course ID

                if (tid.equals(personId) && cid.equals(courseId)) {
                    return true;
                }
            }
        }
        return false; // Assignment not found for the specified student ID and course ID
    }

    /**
     * Saves the provided data to a file with the specified filename.
     *
     * @param filename the name of the file to save the data to
     * @param data     the array of data to be saved
     */
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

    /**
     * Loads the data from the file with the specified filename.
     *
     * @param filename the name of the file to load the data from
     * @return the loaded data as a string
     */
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

    /**
     * Loads the data from the file with the specified filename into an array of strings.
     *
     * @param filename the name of the file to load the data from
     * @return the loaded data as an array of strings
     */
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

    /*
     *   Validation methods
     */

    /**
     * Validates a name input and prompts the user to enter a valid name if it is invalid.
     *
     * @param name    the name to validate
     * @param scanner the scanner object used for user input
     * @return the validated name
     */
    public String validateName(String name, Scanner scanner) {
        while (!isValidName(name)) {
            System.out.print("Invalid name. Please enter a valid name: ");
            name = scanner.next() + scanner.nextLine();
        }

        return name;
    }

    /**
     * Checks if a name is valid.
     *
     * @param name the name to check
     * @return true if the name is valid, false otherwise
     */
    public boolean isValidName(String name) {
        return name.matches("[A-Z][a-z ]+[A-Z]?[a-z]*");
    }

    /**
     * Validates an email input and prompts the user to enter a valid email if it is invalid.
     *
     * @param email   the email to validate
     * @param scanner the scanner object used for user input
     * @return the validated email
     */
    public String validateEmail(String email, Scanner scanner) {
        while (!isValidEmail(email)) {
            System.out.print("Invalid email. Please enter a valid email: ");
            email = scanner.next();
        }
        return email;
    }

    /**
     * Checks if an email is valid.
     *
     * @param email the email to check
     * @return true if the email is valid, false otherwise
     */
    public boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[a-z]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates a number input and prompts the user to enter a valid number if it is invalid.
     *
     * @param number  the number to validate
     * @param type    the type of the number (e.g., "phone", "semester")
     * @param scanner the scanner object used for user input
     * @return the validated number
     */
    public String validateNumber(String number, String type, Scanner scanner) {
        while (!isValidNumber(number, type)) {
            System.out.print("Invalid number. Please enter a valid number: ");
            number = scanner.next();
        }
        return number;
    }

    /**
     * Checks if a number is valid.
     *
     * @param number the number to check
     * @param type   the type of the number (e.g., "phone", "semester")
     * @return true if the number is valid, false otherwise
     */
    public boolean isValidNumber(String number, String type) {
        if (number.length() != 10 && type.equals("phone")) {
            return false;
        } else if (number.length() > 2 && type.equals("semester")) {
            return false;
        }

        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Injects sample data for students, teachers, courses, grades, and tuitions into the system.
     */
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

    // Colorize console output
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
}
