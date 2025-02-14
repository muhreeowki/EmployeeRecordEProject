# **Employee Record Management System**

---

## **Acknowledgements**

I would like to express our gratitude to all those who contributed to the successful completion of this project. Special thanks to our mentors and peers for their guidance, feedback, and support throughout the development process. I also acknowledge the resources and tools that made this project possible, including Java programming language, IntelliJ IDEA, and various online resources.

---

## **Project Synopsis**

The **Employee Record Management System** is a Java-based application designed to manage employee records efficiently. It provides functionalities such as adding, deleting, modifying, searching, displaying, and counting employee records. The system stores all data in a text file (`employees.txt`) using serialization, ensuring data persistence across program executions. The application is user-friendly, with a text-based interface and proper validations for all input fields.

---

## **Project Analysis**

### **Problem Statement**

Managing employee records manually is time-consuming and prone to errors. Organizations need a system to automate the process of maintaining employee data, ensuring accuracy, and providing quick access to records.

### **Objectives**

1. Automate the process of managing employee records.
2. Provide functionalities to add, delete, modify, search, display, and count records.
3. Ensure data persistence by storing records in a file.
4. Validate user inputs to maintain data integrity.
5. Provide a user-friendly interface for easy operation.

### **Scope**

- The system is designed for small to medium-sized organizations.
- It supports basic CRUD (Create, Read, Update, Delete) operations.
- The system is text-based and does not include a graphical user interface (GUI).

---

## **Project Design**

### **System Architecture**

The system follows a modular architecture with the following components:

1. **Main Class (`EmployeeRecordSystem`)**: Handles the main menu and user interactions.
2. **Employee Class**: Represents the employee entity and stores employee details.
3. **File Handling**: Manages reading and writing employee records to a file.
4. **Validation Module**: Ensures all user inputs are valid before processing.

### **Flowchart**

1. Start the application.
2. Load existing employee records from the file.
3. Display the main menu with options (Add, Delete, Modify, Search, Display All, Count, Exit).
4. Perform the selected operation.
5. Save updated records to the file before exiting.

---

## **Database Design / Structure**

### **File Structure**

The system uses a text file (`employees.txt`) to store employee records. Each record is serialized and stored as an object.

### **Employee Record Structure**

Each employee record contains the following fields:

- **Employee Number**: Auto-generated unique identifier.
- **First Name**: Employee's first name.
- **Last Name**: Employee's last name.
- **Age**: Employee's age (between 18 and 65).
- **Basic Salary**: Employee's salary (non-negative).
- **Department**: Employee's department.
- **Date of Joining**: Date in `DD-MMM-YYYY` format.
- **Address**: Employee's address.
- **City**: Employee's city.
- **Phone Number**: 10-digit phone number.

---

## **User Guide**

### **How to Use the Application**

1. **Run the Program**:

   - Compile the program using `javac EmployeeRecordSystem.java`.
   - Run the program using `java EmployeeRecordSystem`.

2. **Main Menu**:

   - The program displays a menu with the following options:
     1. **ADD Employee**: Add a new employee record.
     2. **DELETE Employee**: Delete an existing employee record by employee number.
     3. **MODIFY Employee**: Modify an existing employee record.
     4. **SEARCH Employee**: Search for employee records by first name, last name, or department.
     5. **Display All Records**: Display all employee records.
     6. **COUNT Records**: Display the total number of records.
     7. **Exit**: Save records and exit the program.

3. **Adding an Employee**:

   - Enter the required details (e.g., first name, last name, age, salary, etc.).
   - The system will validate the inputs and add the record if valid.

4. **Deleting an Employee**:

   - Enter the employee number of the record to delete.
   - The system will remove the record if found.

5. **Modifying an Employee**:

   - Enter the employee number of the record to modify.
   - Provide new values for the fields you want to update. Leave fields blank to retain existing values.

6. **Searching for Employees**:

   - Enter search criteria (first name, last name, or department). Leave fields blank to ignore them.
   - The system will display matching records.

7. **Displaying All Records**:

   - The system will display all employee records in a tabular format.

8. **Counting Records**:

   - The system will display the total number of employee records.

9. **Exiting the Program**:
   - The system will save all records to the file and exit.

---

## **Developer’s Guide**

### **Modules and Descriptions**

1. **Main Class (`EmployeeRecordSystem`)**:

   - Handles the main menu and user interactions.
   - Calls appropriate methods based on user input.

2. **Employee Class**:

   - Represents an employee entity.
   - Contains fields for employee details and methods to get/set values.

3. **File Handling**:

   - **`loadEmployees()`**: Loads employee records from the file.
   - **`saveEmployees()`**: Saves employee records to the file.

4. **Validation Module**:

   - Validates user inputs (e.g., age, salary, date format, phone number).

5. **Operations**:
   - **`addEmployee()`**: Adds a new employee record.
   - **`deleteEmployee()`**: Deletes an existing employee record.
   - **`modifyEmployee()`**: Modifies an existing employee record.
   - **`searchEmployee()`**: Searches for employee records.
   - **`displayAllRecords()`**: Displays all employee records.
   - **`countRecords()`**: Displays the total number of records.

### **Code Structure**

- The program uses object-oriented programming principles.
- Employee records are stored in a `List<Employee>`.
- Serialization is used to read/write records to a file.

### **How to Extend the System**

- Add a graphical user interface (GUI) using JavaFX or Swing.
- Implement additional search criteria (e.g., by city or phone number).
- Add support for multiple departments or roles.

---

## **Module Descriptions**

1. **Employee Class**:

   - Represents an employee with attributes like `empNumber`, `firstName`, `lastName`, etc.
   - Includes a `toString()` method for displaying records.

2. **File Handling**:

   - Uses `ObjectInputStream` and `ObjectOutputStream` for serialization.
   - Ensures data persistence across program executions.

3. **Validation Module**:

   - Validates inputs using regular expressions and conditional checks.
   - Ensures data integrity and prevents invalid entries.

4. **Operations**:
   - Each operation (add, delete, modify, search, display, count) is implemented as a separate method.
   - Methods interact with the `List<Employee>` to perform operations.

---

## **Conclusion**

The **Employee Record Management System** is a robust and efficient solution for managing employee records. It simplifies record-keeping, ensures data accuracy, and provides quick access to employee information. The system is easy to use and can be extended to meet additional requirements in the future.
