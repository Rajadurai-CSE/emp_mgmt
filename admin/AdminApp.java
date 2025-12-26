package admin;

import java.time.LocalDate;
import java.util.Scanner;
import common.DataStore;
import common.RandomSeed;
import employee.Employee;
import employee.EmployeeServices;
import employee.LeaveRequestServices;
import employee.AttendanceService;
import manager.Manager;
import project.Project;
import project.ProjectServices;

public class AdminApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Admin Services");
        
        while(true) {
            System.out.println("1. Login with admin credentials");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            
            int input_;
            try {
                input_ = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            
            if(input_ == 2) {
                break;
            }
            
            if(input_ == 1) {
                System.out.println("Enter Admin ID:");
                int adminId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Password:");
                String passwd = sc.nextLine();
                
                // Simple admin authentication (you can modify this as needed)
                if(adminId == 1 && passwd.equals("admin")) {
                    System.out.println("Welcome Admin!");
                    adminMenu(sc);
                } else {
                    System.out.println("Invalid Credentials. Try Again!!");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private static void adminMenu(Scanner sc) {
        int option = 0;
        while(option != 11) {
            System.out.println("\n=== Admin Services ===");
            System.out.println("1. Add New Employee");
            System.out.println("2. Edit Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Add New Manager");
            System.out.println("6. View All Managers");
            System.out.println("7. Generate Attendance Report");
            System.out.println("8. Generate Leave Report");
            System.out.println("9. Generate Project Allocation Report");
            System.out.println("10. View System Statistics");
            System.out.println("11. Logout");
            System.out.print("Enter option: ");
            
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            
            switch(option) {
                case 1:
                    addNewEmployee(sc);
                    break;
                case 2:
                    editEmployee(sc);
                    break;
                case 3:
                    deleteEmployee(sc);
                    break;
                case 4:
                    viewAllEmployees();
                    break;
                case 5:
                    addNewManager(sc);
                    break;
                case 6:
                    viewAllManagers();
                    break;
                case 7:
                    generateAttendanceReport(sc);
                    break;
                case 8:
                    generateLeaveReport(sc);
                    break;
                case 9:
                    generateProjectReport();
                    break;
                case 10:
                    viewSystemStatistics();
                    break;
                case 11:
                    System.out.println("Logging out!!");
                    break;
                default:
                    System.out.println("Invalid Option. Try Again!!");
            }
        }
    }
    
    private static void addNewEmployee(Scanner sc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Add New Employee");
        System.out.println("Enter Employee ID:");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Employee Name:");
        String empName = sc.nextLine();
        System.out.println("Enter Date of Birth (YYYY MM DD):");
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Secret Code for Registration:");
        String secret = sc.nextLine();
        System.out.println("Enter Manager ID:");
        int mgId = sc.nextInt();
        sc.nextLine();
        
        Employee emp = new Employee(empId, empName, secret, LocalDate.of(year, month, day));
        emp.setMgId(mgId);
        
        // Add to DataStore
        if(DataStore.empCount < DataStore.employees.length) {
            DataStore.employees[DataStore.empCount] = emp;
            DataStore.empCount++;
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Employee storage is full!");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void editEmployee(Scanner sc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Edit Employee");
        System.out.println("Enter Employee ID:");
        int empId = sc.nextInt();
        sc.nextLine();
        
        boolean found = false;
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getEmpId() == empId) {
                found = true;
                Employee emp = DataStore.employees[i];
                EmployeeServices.displayProfile(emp);
                
                System.out.println("Enter 1.Change Name 2.Add Skills 3.Change Bank Account Number 4.Change Manager");
                int choice = sc.nextInt();
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
                } else if(choice == 4) {
                    System.out.println("Enter new manager ID:");
                    int newMgId = sc.nextInt();
                    sc.nextLine();
                    emp.setMgId(newMgId);
                }
                
                System.out.println("Employee updated successfully!");
                break;
            }
        }
        
        if(!found) {
            System.out.println("Employee not found!");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void deleteEmployee(Scanner sc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Delete Employee");
        System.out.println("Enter Employee ID:");
        int empId = sc.nextInt();
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
    
    private static void viewAllEmployees() {
        System.out.println("--------------------------------------------------");
        System.out.println("All Employees:");
        EmployeeServices.displayAllEmployees();
        System.out.println("--------------------------------------------------");
    }
    
    private static void addNewManager(Scanner sc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Add New Manager");
        System.out.println("Enter Manager ID:");
        int mgId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Manager Name:");
        String mgName = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        
        Manager manager = new Manager(mgId, mgName, password);
        
        if(DataStore.managerCount < DataStore.managers.length) {
            DataStore.managers[DataStore.managerCount] = manager;
            DataStore.managerCount++;
            System.out.println("Manager added successfully!");
        } else {
            System.out.println("Manager storage is full!");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void viewAllManagers() {
        System.out.println("--------------------------------------------------");
        System.out.println("All Managers:");
        for(int i = 0; i < DataStore.managerCount; i++) {
            Manager mgr = DataStore.managers[i];
            System.out.println("Manager ID: " + mgr.getMgId());
            System.out.println("Name: " + mgr.getMgName());
            System.out.println("-------------------");
        }
        System.out.println("--------------------------------------------------");
    }
    
    private static void generateAttendanceReport(Scanner sc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Attendance Report");
        System.out.println("Enter Employee ID (0 for all):");
        int empId = sc.nextInt();
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
    
    private static void generateLeaveReport(Scanner sc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Leave Report");
        System.out.println("Enter Employee ID (0 for all):");
        int empId = sc.nextInt();
        sc.nextLine();
        
        if(empId == 0) {
            // Generate for all employees
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
    }
    
    private static void viewSystemStatistics() {
        System.out.println("--------------------------------------------------");
        System.out.println("System Statistics");
        System.out.println("Total Employees: " + DataStore.empCount);
        System.out.println("Total Managers: " + DataStore.managerCount);
        System.out.println("Total Projects: " + DataStore.projectCount);
        System.out.println("Total Leave Requests: " + DataStore.leaveCount);
        System.out.println("Total Attendance Records: " + DataStore.attendanceCount);
        
        // Count employees by project
        System.out.println("\nProject Distribution:");
        for(int i = 0; i < DataStore.projectCount; i++) {
            int projectId = DataStore.projects[i].getProjectId();
            int count = 0;
            for(int j = 0; j < DataStore.empCount; j++) {
                if(DataStore.employees[j].getProjectId() == projectId) {
                    count++;
                }
            }
            System.out.println("Project " + projectId + ": " + count + " employees");
        }
        System.out.println("--------------------------------------------------");
    }
}
