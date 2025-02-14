package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int empNumber;
    private String firstName;
    private String lastName;
    private int age;
    private double basicSalary;
    private String department;
    private String dateOfJoining;
    private String address;
    private String city;
    private String phoneNumber;

    // Constructor
    public Employee(int empNumber, String firstName, String lastName, int age, double basicSalary, String department,
                    String dateOfJoining, String address, String city, String phoneNumber) {
        this.empNumber = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.basicSalary = basicSalary;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getEmpNumber() { return empNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public double getBasicSalary() { return basicSalary; }
    public String getDepartment() { return department; }
    public String getDateOfJoining() { return dateOfJoining; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setEmpNumber(int empNumber) { this.empNumber = empNumber; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setDepartment(String department) { this.department = department; }
    public void setDateOfJoining(String dateOfJoining) { this.dateOfJoining = dateOfJoining; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return String.format("%-10d %-15s %-15s %-5d %-10.2f %-15s %-15s %-20s %-15s %-15s",
                empNumber, firstName, lastName, age, basicSalary, department, dateOfJoining, address, city, phoneNumber);
    }
}

public class EmployeeRecordSystem {
    private static final String FILE_NAME = "employees.txt";
    private static List<Employee> employees = new ArrayList<>();
    private static int nextEmpNumber = 1;

    public static void main(String[] args) {
        loadEmployees();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee Record System");
            System.out.println("1. ADD Employee");
            System.out.println("2. DELETE Employee");
            System.out.println("3. MODIFY Employee");
            System.out.println("4. SEARCH Employee");
            System.out.println("5. Display All Records");
            System.out.println("6. COUNT Records");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    deleteEmployee(scanner);
                    break;
                case 3:
                    modifyEmployee(scanner);
                    break;
                case 4:
                    searchEmployee(scanner);
                    break;
                case 5:
                    displayAllRecords();
                    break;
                case 6:
                    countRecords();
                    break;
                case 7:
                    saveEmployees();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
            nextEmpNumber = employees.isEmpty() ? 1 : employees.get(employees.size() - 1).getEmpNumber() + 1;
        } catch (FileNotFoundException e) {
            System.out.println("No existing records found. Starting with a new file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Basic Salary: ");
        double basicSalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Date of Joining (DD-MMM-YYYY): ");
        String dateOfJoining = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        if (validateInput(firstName, lastName, age, basicSalary, department, dateOfJoining, address, city, phoneNumber)) {
            Employee emp = new Employee(nextEmpNumber++, firstName, lastName, age, basicSalary, department, dateOfJoining, address, city, phoneNumber);
            employees.add(emp);
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Invalid input. Employee not added.");
        }
    }

    private static boolean validateInput(String firstName, String lastName, int age, double basicSalary, String department,
                                         String dateOfJoining, String address, String city, String phoneNumber) {
        if (firstName.isEmpty() || lastName.isEmpty() || department.isEmpty() || address.isEmpty() || city.isEmpty() || phoneNumber.isEmpty()) {
            System.out.print("Found Empty Fields.");
            return false;
        }
        if (age < 18 || age > 65) {
            System.out.print("Invalid Age.");
            return false;
        }
        if (basicSalary < 0) {
            System.out.print("Salary must be greater than 0.");
            return false;
        }
        if (!Pattern.matches("\\d{2}-[A-Za-z]{3}-\\d{4}", dateOfJoining)) {
            System.out.print("Invalid Date.");
            return false;
        }
        if (!Pattern.matches("\\d{10}", phoneNumber)) {
            System.out.print("Invalid Phone Number.");
            return false;
        }
        return true;
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee Number to delete: ");
        int empNumber = scanner.nextInt();
        scanner.nextLine();
        Employee emp = findEmployeeByNumber(empNumber);
        if (emp != null) {
            employees.remove(emp);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void modifyEmployee(Scanner scanner) {
        System.out.print("Enter Employee Number to modify: ");
        int empNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Employee emp = findEmployeeByNumber(empNumber);
        if (emp != null) {
            System.out.println("Leave fields blank to keep existing values.");
            System.out.print("Enter new First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter new Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter new Age: ");
            String ageInput = scanner.nextLine();
            System.out.print("Enter new Basic Salary: ");
            String basicSalaryInput = scanner.nextLine();
            System.out.print("Enter new Department: ");
            String department = scanner.nextLine();
            System.out.print("Enter new Date of Joining (DD-MMM-YYYY): ");
            String dateOfJoining = scanner.nextLine();
            System.out.print("Enter new Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter new City: ");
            String city = scanner.nextLine();
            System.out.print("Enter new Phone Number: ");
            String phoneNumber = scanner.nextLine();

            // Update only non-blank fields
            if (!firstName.isEmpty()) {
                emp.setFirstName(firstName);
            }
            if (!lastName.isEmpty()) {
                emp.setLastName(lastName);
            }
            if (!ageInput.isEmpty()) {
                int age = Integer.parseInt(ageInput);
                if (age >= 18 && age <= 65) {
                    emp.setAge(age);
                } else {
                    System.out.println("Invalid age. Age must be between 18 and 65. Age not updated.");
                }
            }
            if (!basicSalaryInput.isEmpty()) {
                double basicSalary = Double.parseDouble(basicSalaryInput);
                if (basicSalary >= 0) {
                    emp.setBasicSalary(basicSalary);
                } else {
                    System.out.println("Invalid salary. Salary must be non-negative. Salary not updated.");
                }
            }
            if (!department.isEmpty()) {
                emp.setDepartment(department);
            }
            if (!dateOfJoining.isEmpty()) {
                if (Pattern.matches("\\d{2}-[A-Za-z]{3}-\\d{4}", dateOfJoining)) {
                    emp.setDateOfJoining(dateOfJoining);
                } else {
                    System.out.println("Invalid date format. Date must be in DD-MMM-YYYY format. Date not updated.");
                }
            }
            if (!address.isEmpty()) {
                emp.setAddress(address);
            }
            if (!city.isEmpty()) {
                emp.setCity(city);
            }
            if (!phoneNumber.isEmpty()) {
                if (Pattern.matches("\\d{10}", phoneNumber)) {
                    emp.setPhoneNumber(phoneNumber);
                } else {
                    System.out.println("Invalid phone number. Phone number must be 10 digits. Phone number not updated.");
                }
            }
            System.out.println("Employee modified successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void searchEmployee(Scanner scanner) {
        System.out.print("Enter First Name (or leave blank): ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name (or leave blank): ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Department (or leave blank): ");
        String department = scanner.nextLine();
        List<Employee> results = new ArrayList<>();
        for (Employee emp : employees) {
            if ((firstName.isEmpty() || emp.getFirstName().equalsIgnoreCase(firstName)) &&
                    (lastName.isEmpty() || emp.getLastName().equalsIgnoreCase(lastName)) &&
                    (department.isEmpty() || emp.getDepartment().equalsIgnoreCase(department))) {
                results.add(emp);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No matching records found.");
        } else {
            System.out.println("Search Results:");
            displayRecords(results);
        }
    }

    private static void displayAllRecords() {
        if (employees.isEmpty()) {
            System.out.println("No records found.");
        } else {
            System.out.println("All Employee Records:");
            displayRecords(employees);
        }
    }

    private static void displayRecords(List<Employee> records) {
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-15s %-15s %-20s %-15s %-15s\n",
                "Emp No", "First Name", "Last Name", "Age", "Salary", "Department", "DOJ", "Address", "City", "Phone");
        for (Employee emp : records) {
            System.out.println(emp);
        }
    }

    private static void countRecords() {
        System.out.println("Total number of records: " + employees.size());
    }

    private static Employee findEmployeeByNumber(int empNumber) {
        for (Employee emp : employees) {
            if (emp.getEmpNumber() == empNumber) {
                return emp;
            }
        }
        return null;
    }
}
