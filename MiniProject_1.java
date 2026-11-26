
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;

public class MiniProject_1 {

    static ArrayList<Integer> studentIds = new ArrayList<>();
    static ArrayList<String> studentNames = new ArrayList<>();
    static ArrayList<Integer> studentMarks = new ArrayList<>();
    static LinkedList<String> attendance = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("      STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Remove Student");
            System.out.println("6. Add Student Attendance");
            System.out.println("7. Display Attendance");
            System.out.println("8. Remove Attendance");
            System.out.println("9. Display Student Statistics");
            System.out.println("10. Sort Student Names");
            System.out.println("11. Exit");
            System.out.print("Enter Your Choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateMarks();
                    break;

                case 5:
                    removeStudent();
                    break;

                case 6:
                    addAttendance();
                    break;

                case 7:
                    displayAttendance();
                    break;

                case 8:
                    removeAttendance();
                    break;

                case 9:
                    displayStatistics();
                    break;

                case 10:
                    sortStudents();
                    break;

                case 11:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 11);

        sc.close();
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    static int readMarks() {
        int marks;

        while (true) {
            System.out.print("Enter Student Marks (0-100): ");
            marks = readInt();

            if (marks >= 0 && marks <= 100) {
                return marks;
            }

            System.out.println("Invalid marks. Enter between 0 and 100.");
        }
    }

    static int findStudent(String name) {
        return studentNames.indexOf(name);
    }

    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = readInt();

        if (studentIds.contains(id)) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int marks = readMarks();

        studentIds.add(id);
        studentNames.add(name);
        studentMarks.add(marks);

        System.out.println("Student Added Successfully!");
    }

    static void displayStudents() {

        if (studentNames.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n--------- STUDENT RECORDS ---------");
        System.out.printf("%-12s %-15s %s%n",
                "Student ID", "Student Name", "Marks");

        for (int i = 0; i < studentNames.size(); i++) {
            System.out.printf("%-12d %-15s %d%n",
                    studentIds.get(i),
                    studentNames.get(i),
                    studentMarks.get(i));
        }

        System.out.println("Total Students: " + studentNames.size());
    }

    static void searchStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int index = findStudent(name);

        if (index != -1) {
            System.out.println("\nStudent Found!");
            System.out.println("Student ID: " + studentIds.get(index));
            System.out.println("Name: " + studentNames.get(index));
            System.out.println("Marks: " + studentMarks.get(index));
        } else {
            System.out.println("Student not found.");
        }
    }

    static void updateMarks() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int index = findStudent(name);

        if (index != -1) {
            int newMarks = readMarks();

            studentMarks.set(index, newMarks);

            System.out.println("Student Marks Updated Successfully!");
            System.out.println("Name: " + studentNames.get(index));
            System.out.println("Updated Marks: " + studentMarks.get(index));
        } else {
            System.out.println("Student not found.");
        }
    }

    static void removeStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int index = findStudent(name);

        if (index != -1) {
            studentIds.remove(index);
            studentNames.remove(index);
            studentMarks.remove(index);

            attendance.remove(name);

            System.out.println("Student Removed Successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void addAttendance() {

        if (studentNames.isEmpty()) {
            System.out.println("Add student records first.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        if (!studentNames.contains(name)) {
            System.out.println("Student does not exist.");
            return;
        }

        if (attendance.contains(name)) {
            System.out.println("Student is already marked present.");
            return;
        }

        System.out.println("1. Add at Beginning");
        System.out.println("2. Add at End");
        System.out.print("Enter Your Choice: ");

        int option = readInt();

        if (option == 1) {
            attendance.addFirst(name);
            System.out.println("Attendance Added at Beginning.");
        } else if (option == 2) {
            attendance.addLast(name);
            System.out.println("Attendance Added at End.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    static void displayAttendance() {

        if (attendance.isEmpty()) {
            System.out.println("No students are present.");
            return;
        }

        System.out.println("\n--------- ATTENDANCE LIST ---------");

        for (String name : attendance) {
            System.out.println(name);
        }

        System.out.println("Total Present Students: " + attendance.size());
    }

    static void removeAttendance() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        if (attendance.contains(name)) {
            attendance.remove(name);
            System.out.println("Attendance Removed Successfully!");
        } else {
            System.out.println("Student is not in the attendance list.");
        }
    }

    static void displayStatistics() {

        if (studentMarks.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        int highest = Collections.max(studentMarks);
        int lowest = Collections.min(studentMarks);

        int total = 0;
        int above75 = 0;

        for (int marks : studentMarks) {
            total += marks;

            if (marks > 75) {
                above75++;
            }
        }

        double average = (double) total / studentMarks.size();

        System.out.println("\n--------- STUDENT STATISTICS ---------");
        System.out.println("Total Students: " + studentNames.size());
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        System.out.printf("Average Marks: %.2f%n", average);
        System.out.println("Students Above 75: " + above75);
    }

    static void sortStudents() {

        if (studentNames.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        ArrayList<String> sortedNames = new ArrayList<>(studentNames);

        Collections.sort(sortedNames);

        System.out.println("\nStudent Names in Alphabetical Order:");
        System.out.println(sortedNames);

        Collections.reverse(sortedNames);

        System.out.println("\nStudent Names in Reverse Order:");
        System.out.println(sortedNames);
    }
}
