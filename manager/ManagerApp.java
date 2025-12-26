package manager;

import java.time.LocalDate;
import java.util.Scanner;
import common.DataStore;
import common.RandomSeed;
import manager.Manager;
import employee.EmployeeServices;
import employee.LeaveRequestServices;
import employee.Employee;
import project.ProjectServices;

public class ManagerApp {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Manager Services");
    char ch ='y';
    while(ch=='y'){
      System.out.println("1. Login with your credentials 2. Exit");
      int input_ = sc.nextInt();
      if(input_ == 1){
        Manager curr_mg = null;
        System.out.println("Enter ManagerID:");
        int mgId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Password:");
        String passwd = sc.nextLine();
        boolean login = false;
        for(int i=0;i<DataStore.managerCount;i++){
          if(DataStore.managers[i] != null && DataStore.managers[i].getMgId() == mgId && DataStore.managers[i].getPasswd() != null && DataStore.managers[i].getPasswd().equals(passwd)){
            curr_mg = DataStore.managers[i];
            login = true;
          }
        }
        if(login == false){
          System.out.println("Invalid Credentials. Try Again!!");
          break;
        }
        else{
          System.out.println("Welcome "+mgId);
          System.out.println("Services:");
          int option = 0;
          sc.nextLine();
          while(option!=11){
            System.out.println("1.Create New Project  2.View Managed Projects 3. View Project Status 4.Update Project Status 5.View All Employees 6.View Available Resources 7.Search Emp by Skill 8.Assign Emp to Project 9. Manage Leaves 10.Performance Review 11.Logout");

            System.out.println("Enter option");
            
            option = sc.nextInt();
            sc.nextLine();

            if(option == 11){
              System.out.println("Logging out!!");
              break;
            }
            else if(option == 1){
              System.out.println("--------------------------------------------------");
              System.out.println("Create New Project");
              System.out.println("Enter Project Name:");
              String projectName = sc.nextLine();
              System.out.println("Enter Project Description:");
              String description = sc.nextLine();
              System.out.println("Enter Start Date (YYYY MM DD):");
              int year = sc.nextInt();
              int month = sc.nextInt();
              int day = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter End Date (YYYY MM DD):");
              int endYear = sc.nextInt();
              int endMonth = sc.nextInt();
              int endDay = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Required Skills (comma separated):");
              String skillsInput = sc.nextLine();
              String[] requiredSkills = skillsInput.split(",");
              
              ProjectServices.createProject(projectName, description, 
                java.time.LocalDate.of(year, month, day), 
                java.time.LocalDate.of(endYear, endMonth, endDay), 
                requiredSkills, curr_mg.getMgId());
              System.out.println("Project created successfully!");
              System.out.println("--------------------------------------------------");
            }

            else if(option == 2){
              System.out.println("--------------------------------------------------");
              System.out.println("Your Managed Projects:");
              ProjectServices.viewManagerProjects(curr_mg.getMgId());
              System.out.println("--------------------------------------------------");
            }

            else if(option == 3){
              System.out.println("--------------------------------------------------");
              System.out.println("Enter Project ID to view status:");
              int projectId = sc.nextInt();
              sc.nextLine();
              ProjectServices.viewProjectStatus(projectId);
              System.out.println("--------------------------------------------------");
            }
            else if(option ==4){
              System.out.println("--------------------------------------------------");
              System.out.println("Enter Project ID to update status:");
              int projectId = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter new status (Planning/In Progress/Completed/On Hold):");
              String status = sc.nextLine();
              ProjectServices.updateProjectStatus(projectId, status);
              System.out.println("Project status updated!");
              System.out.println("--------------------------------------------------");
            }

            else if(option ==5){
              System.out.println("--------------------------------------------------");
              System.out.println("All Employees:");
              EmployeeServices.displayAllEmployees();
              System.out.println("--------------------------------------------------");
            }

            else if(option ==6){
              System.out.println("--------------------------------------------------");
              System.out.println("Available Resources:");
              EmployeeServices.displayAvailableResources();
              System.out.println("--------------------------------------------------");
            }
            else if(option == 7){
              System.out.println("--------------------------------------------------");
              System.out.println("Enter skill to search:");
              String skill = sc.nextLine();
              EmployeeServices.searchEmployeesBySkill(skill);
              System.out.println("--------------------------------------------------");
            }
            else if(option == 8){
              System.out.println("--------------------------------------------------");
              System.out.println("Assign Employee to Project");
              System.out.println("Enter Employee ID:");
              int empId = sc.nextInt();
              System.out.println("Enter Project ID:");
              int projectId = sc.nextInt();
              sc.nextLine();
              EmployeeServices.assignEmployeeToProject(empId, projectId, curr_mg.getMgId());
              System.out.println("Employee assigned to project!");
              System.out.println("--------------------------------------------------");
            }
            
            else if(option == 9){
              System.out.println("--------------------------------------------------");
              System.out.println("Manage Leave Requests");
              LeaveRequestServices.viewPendingLeavesForManager(curr_mg.getMgId());
              System.out.println("Enter Leave Request ID to approve/reject (0 to skip):");
              int leaveId = sc.nextInt();
              if(leaveId != 0) {
                sc.nextLine();
                System.out.println("Approve or Reject? (A/R):");
                String decision = sc.nextLine();
                if(decision.equalsIgnoreCase("A")) {
                  LeaveRequestServices.approveLeave(leaveId, curr_mg.getMgId());
                } else if(decision.equalsIgnoreCase("R")) {
                  System.out.println("Enter rejection reason:");
                  String reason = sc.nextLine();
                  LeaveRequestServices.rejectLeave(leaveId, curr_mg.getMgId(), reason);
                }
              }
              System.out.println("--------------------------------------------------");
            }
            else if(option == 10){
              System.out.println("--------------------------------------------------");
              System.out.println("Performance Review");
              System.out.println("Enter Employee ID:");
              int empId = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Performance Rating (1-5):");
              int rating = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Feedback:");
              String feedback = sc.nextLine();
              
              EmployeeServices.addPerformanceReview(empId, rating, feedback, curr_mg.getMgId());
              System.out.println("Performance review added successfully!");
              System.out.println("--------------------------------------------------");
            }
          }
        }

      } 
      else{
        break;
      }
    }

  }
}
