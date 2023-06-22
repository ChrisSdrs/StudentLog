import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversityManagementSystem ums = new UniversityManagementSystem();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Assign Course to Teacher");
            System.out.println("5. Assign Course to Student");
            System.out.println("6. Enter Grade for Student");
            System.out.println("7. Display Average Grade per Student");
            System.out.println("8. Display Average Grade per Course");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Full name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String studentEmail = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String studentPhone = scanner.nextLine();
                    System.out.print("Semester: ");

                    int studentSemester = scanner.nextInt();

                    Student student = new Student(studentId, studentName, studentEmail, studentPhone, studentSemester);
                    ums.addStudent(student);
                    System.out.println("The student has been added successfully!");
                    break;
                case 2:
                    // Add teacher
                    System.out.print("Teacher ID: ");
                    int teacherId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Full name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String teacherPhone = scanner.nextLine();
                    System.out.print("Specialty: ");
                    String teacherSpecialty = scanner.nextLine();

                    Teacher teacher = new Teacher(teacherId, teacherName, teacherEmail, teacherPhone, teacherSpecialty);
                    ums.addTeacher(teacher);
                    System.out.println("The teacher has been added successfully!");
                    break;
                case 3:
                    // Add Course
                    System.out.print("Course ID: ");
                    int courseId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Title: ");
                    String courseTitle = scanner.nextLine();
                    System.out.print("Semester: ");
                    int courseSemester = scanner.nextInt();

                    Course course = new Course(courseId, courseTitle, courseSemester);
                    ums.addCourse(course);
                    System.out.println("The course has been added successfully.");

                    break;
                case 4:
                    // Assign Course to Teacher
                    System.out.print("Teacher ID: ");
                    teacherId = scanner.nextInt();
                    System.out.print("Course ID: ");
                    courseId = scanner.nextInt();

                    teacher = ums.findTeacherById(teacherId);
                    course = ums.findCourseById(courseId);

                    if (teacher != null && course != null) {
                        ums.assignCourseToTeacher(teacher, course);
                        System.out.println("The course has been assigned to the teacher.");
                    } else {
                        System.out.println("Invalid teacher ID or course ID.");
                    }
                    break;
                case 5:
                    // Ανάθεση μαθήματος σε φοιτητή
                    System.out.print("Student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Course ID: ");
                    courseId = scanner.nextInt();

                    student = ums.findStudentById(studentId);
                    course = ums.findCourseById(courseId);

                    if (student != null && course != null) {
                        ums.assignCourseToStudent(student, course);
                        System.out.println("The course has been assigned to the student.");
                    } else {
                        System.out.println("Invalid student ID or course ID.");
                    }
                    break;
                case 6:
                    // Καταχώρηση βαθμολογίας σε φοιτητή
                    System.out.print("Student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Course ID: ");
                    courseId = scanner.nextInt();
                    System.out.print("Grade: ");
                    double grade = scanner.nextDouble();

                    student = ums.findStudentById(studentId);
                    course = ums.findCourseById(courseId);

                    if (student != null && course != null) {
                        ums.addGrade(student, course, grade);
                        System.out.println("The grade has been recorded successfully.");
                    } else {
                        System.out.println("Invalid student ID or course ID.");
                    }
                    break;
                case 7:
                    // Display average grade per student
                    System.out.print("Student ID: ");
                    studentId = scanner.nextInt();

                    student = ums.findStudentById(studentId);

                    if (student != null) {
                        double average = ums.calculateStudentAverage(student);
                        System.out.println("The average grade for the student is: " + average);
                    } else {
                        System.out.println("Invalid student ID.");
                    }
                    break;
                case 8:
                    // Display average grade per course
                    System.out.print("Course ID: ");
                    courseId = scanner.nextInt();

                    course = ums.findCourseById(courseId);

                    if (course != null) {
                        double average = ums.calculateCourseAverage(course);
                        System.out.println("The average grade for the course is: " + average);
                    } else {
                        System.out.println("Invalid course ID.");
                    }
                    break;
                case 0:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }
}
