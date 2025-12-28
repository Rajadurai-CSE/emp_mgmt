package admin;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import common.DataStore;
import employee.Employee;
import employee.EmployeeServices;
import employee.LeaveRequestServices;
import employee.AttendanceService;
import project.Project;
import project.ProjectServices;

public class AdminApp {
    static Scanner sc = new Scanner(System.in);

    private static boolean authenticateAdmin(String username, String password) {
        return DataStore.getAdminUsername().equals(username) && DataStore.getAdminPassword().equals(password);
    }

    public static void main() {
        System.out.println("Admin Services");
        while(true) {
            System.out.println("1. Login with admin credentials");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int input;
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            if(input == 1) {
                System.out.println("Enter Admin Username:");
                String username = sc.nextLine();
                System.out.println("Enter Password:");
                String password = sc.nextLine();

                if(authenticateAdmin(username, password)) {
                    System.out.println("Welcome Admin!");
                    adminMenu();
                } else {
                    System.out.println("Invalid Credentials. Try Again!!\n");
                }
            }
            else if(input == 2) {
                System.out.println("Exiting Admin Menu...");
                break;
            }    
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private static void adminMenu() {
        int option = 0;
        while(option != 9) {
            System.out.println("\n=== Admin Services ===");
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee By Id");
            System.out.println("4. Edit Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Generate Attendance Report");
            System.out.println("7. Generate Leave Report");
            System.out.println("8. Generate Project Allocation Report");
            System.out.println("9. Logout");
            System.out.print("Enter option: ");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch(option) {
                case 1:
                    addNewEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    viewEmployeeById();
                    break;
                case 4:
                    editEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    generateAttendanceReport();
                    break;
                case 7:
                    generateLeaveReport();
                    break;
                case 8:
                    generateProjectReport();
                    break;
                case 9:
                    System.out.println("Logging out!!");
                    break;
                default:
                    System.out.println("Invalid Option. Try Again!!");
            }
        }
    }
    
    private static void addNewEmployee() {
        System.out.println("--------------------------------------------------");
        System.out.println("Add New Employee");
        int empId;
        while (true) {
            System.out.println("Enter Employee ID:");
            String emp_id = sc.nextLine().trim();
            if (emp_id.isEmpty()) {
                System.out.println("Employee ID cannot be empty.");
                continue;
            }
            try {
                empId = Integer.parseInt(emp_id);
            } catch (NumberFormatException e) {
                System.out.println("Employee ID must be a number.");
                continue;
            }
            if (empId <= 0) {
                System.out.println("Employee ID must be a positive number.");
                continue;
            }
            if (DataStore.empCount > 0 && EmployeeServices.findEmployeeById(empId) != null) {
                System.out.println("Employee ID already exists. Try another.");
                continue;
            }
            break;
        }
        String empName;
        while (true) {
            System.out.println("Enter Employee Name:");
            empName = sc.nextLine().trim();
            if (empName.isEmpty()) {
                System.out.println("Employee name cannot be empty.");
                continue;
            }
            if (!empName.matches("[a-zA-Z ]+")) {
                System.out.println("Employee name must contain only alphabets.");
                continue;
            }
            break;
        }
        LocalDate dob = null;
        while (dob == null) {
            try {
                System.out.println("Enter Date of Birth (YYYY-MM-DD):");
                String date = sc.nextLine();
                dob = LocalDate.parse(date);
            if (dob.isAfter(LocalDate.now())) {
                System.out.println("Date of birth cannot be in the future.");
                dob = null;
            }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            }
        }
        String secret;
        while (true) {
            System.out.println("Enter Secret Code for Registration:");
            secret = sc.nextLine().trim();

            if (secret.isEmpty()) {
                System.out.println("Secret code cannot be empty.");
                continue;
            }
            break;
        }
        Employee emp = new Employee(empId, empName, secret, dob);
        
        if(DataStore.empCount < DataStore.employees.length) {
            DataStore.employees[DataStore.empCount++] = emp;
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Employee storage is full!");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void viewAllEmployees() {
        System.out.println("--------------------------------------------------");
        System.out.println("All Employees:");
        System.out.println("--------------------------------------------------");
        EmployeeServices.displayAllEmployees();
        System.out.println("--------------------------------------------------");
    }

    private static void viewEmployeeById(){
        System.out.println("Enter Employee ID:");
        int emp_id;
        try {
            emp_id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Employee ID.");
            return;
        }
        Employee e = EmployeeServices.findEmployeeById(emp_id);
        if (e != null) {
            EmployeeServices.displayProfile(e);
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void editEmployee() {
        System.out.println("--------------------------------------------------");
        System.out.println("Edit Employee");
        
        int empId = 0;
        while (true) {
            try {
                System.out.println("Enter Employee ID:");
                empId = sc.nextInt();     
                break;
            }
            catch (InputMismatchException e) {
                sc.nextLine(); // for removing invalid input, which is in buffer
                System.out.println("Invalid Input for Employee ID  !!!");
            }
        }   
        
        sc.nextLine();
        
        boolean found = false;
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getEmpId() == empId) {
                found = true;
                Employee emp = DataStore.employees[i];
                EmployeeServices.displayProfile(emp);
                
                while (true) {
                    System.out.println("1.Change Name 2.Add Skills 3.Change Bank Account Number 4. Exit");
                    
                    int choice = 0;
                    while (true) {
                        try {
                            System.out.print("Enter choice: ");
                            choice = sc.nextInt(); 
                            break;
                        }
                        catch (InputMismatchException e) {
                            sc.nextLine(); // for removing invalid input, which is in buffer
                            System.out.println("Invalid Input for choice !!!");
                        }
                    }   

                    sc.nextLine();
                    
                    if(choice == 1) {
                        System.out.println("Enter new name:");
                        String name = sc.nextLine();
                        emp.setEmpName(name);
                    } else if(choice == 2) {
                        System.out.println("Enter skill to add:");
                        String skill = sc.nextLine();
                        EmployeeServices.update(emp, 2, skill);
                    } else if(choice == 3) {
                        System.out.println("Enter bank account number:");
                        String accNum = sc.nextLine();
                        emp.setAccntNumber(accNum);
                    }
                    else if(choice == 4) {
                        System.out.println("Exiting...");
                        break;
                    } else {
                        System.out.println("Invalid choice!");
                        continue;
                    }
                    
                    System.out.println("Employee updated successfully!");
                }
                
                break;
            }
        }
        if(!found) {
            System.out.println("Employee not found!");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void deleteEmployee() {
        System.out.println("--------------------------------------------------");
        System.out.println("Delete Employee");
        int empId = 0;
        while (true) {
            try {
                System.out.println("Enter Employee ID:");
                empId = sc.nextInt();     
                break;
            }
            catch (InputMismatchException e) {
                sc.nextLine(); // for removing invalid input, which is in buffer
                System.out.println("Invalid Input for Employee ID  !!!");
            }
        }
        sc.nextLine();
        
        boolean found = false;
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getEmpId() == empId) {
                found = true;
                // Shift remaining employees
                for(int j = i; j < DataStore.empCount - 1; j++) {
                    DataStore.employees[j] = DataStore.employees[j + 1];
                }
                DataStore.empCount--;
                System.out.println("Employee deleted successfully!");
                break;
            }
        }
        if(!found) {
            System.out.println("Employee not found!");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void generateAttendanceReport() {
        System.out.println("--------------------------------------------------");
        System.out.println("Attendance Report");
        
        int empId = 0;
        while (true) {
            try {
                System.out.println("Enter Employee ID (0 for all):");
                empId = sc.nextInt();     
                break;
            }
            catch (InputMismatchException e) {
                sc.nextLine(); // for removing invalid input, which is in buffer
                System.out.println("Invalid Input for Employee ID  !!!");
            }
        }   
        sc.nextLine();
        if(empId == 0) {
            // Generate for all employees
            for(int i = 0; i < DataStore.empCount; i++) {
                int currentEmpId = DataStore.employees[i].getEmpId();
                AttendanceService.viewTimeSheet(currentEmpId, LocalDate.now().minusDays(30));
            }
        } else {
            AttendanceService.viewTimeSheet(empId, LocalDate.now().minusDays(30));
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void generateLeaveReport() {
        System.out.println("--------------------------------------------------");
        System.out.println("Leave Report");
        int empId = 0;
        while (true) {
            try {
                System.out.println("Enter Employee ID (0 for all):");
                empId = sc.nextInt();     
                break;
            }
            catch (InputMismatchException e) {
                sc.nextLine(); // for removing invalid input, which is in buffer
                System.out.println("Invalid Input for Employee ID  !!!");
            }
        }   
        sc.nextLine();
        
        if(empId == 0) {
            for(int i = 0; i < DataStore.empCount; i++) {
                LeaveRequestServices.empLeaveStatus(DataStore.employees[i].getEmpId());
            }
        } else {
            LeaveRequestServices.empLeaveStatus(empId);
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void generateProjectReport() {
        System.out.println("--------------------------------------------------");
        System.out.println("Project Allocation Report");
        for(int i = 0; i < DataStore.projectCount; i++) {
            Project project = DataStore.projects[i];
            ProjectServices.display(project);
            System.out.println("Assigned Employees:");
            int count = 0;
            for(int j = 0; j < DataStore.empCount; j++) {
                if(DataStore.employees[j].getProjectId() == project.getProjectId()) {
                    System.out.println("- " + DataStore.employees[j].getEmpName() + " (ID: " + DataStore.employees[j].getEmpId() + ")");
                    count++;
                }
            }
            System.out.println("Total Employees: " + count);
            System.out.println("-------------------");
        }
        System.out.println("--------------------------------------------------");
        sc.close();
    }
}