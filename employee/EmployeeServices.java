package employee;

import java.time.LocalDate;
import java.util.*;
import common.DataStore;
import employee.Employee;
import employee.AttendanceService;
import employee.LeaveRequestServices;
import project.ProjectServices;
import project.Project;

public class EmployeeServices{

  public static void update(Employee ele, int choice,String val) {
        if (choice == 1)        ele.setEmpName(val);
        if (choice == 2) {    
           String[] skills = ele.getSkills();
           skills = Arrays.copyOf(skills, skills.length+1);
           skills[skills.length - 1] = val;
           ele.setSkills(skills);
          };
        if (choice == 3)    ele.setPassword(val);;
        if (choice == 4) ele.setAccntNumber(val);
    }

    
  
    public static void displayProfile(Employee ele) {
      
        System.out.println("--------------------------------------------------");
        System.out.println("Employee ID  : " + ele.getEmpId());
        System.out.println("Name         : " + ele.getEmpName());

        if(ele.getProjectId()==0){
           System.out.println("You are currently not assigned to any Projects");
        }
        else{
          System.out.println("Project ID   : " + ele.getProjectId());
        }
        
        System.out.println("Skills : ");
        String[] skills = ele.getSkills();
        for(int i=0;i<skills.length;i++){
          System.out.println(skills[i]);
        }
        System.out.println("DOB          : " + ele.getDob());
        System.out.println("Account #  : " + ele.getAccntNumber() );
        System.out.println("--------------------------------------------------");
    }

    public static void displayAllEmployees(){
        for(int i = 0; i < DataStore.empCount; i++){
            Employee ele = DataStore.employees[i];
            if(ele != null){
                EmployeeServices.displayProfile(ele);
            }
        }
    }
    
    

    public static void viewProject(int pid)
    {
      if(pid==0){
        System.out.println("--------------------------------------------------");
           System.out.println("You are currently not assigned to any Projects");
           System.out.println("--------------------------------------------------");
        }
        else{
          System.out.println("--------------------------------------------------");
         System.out.println("Project Details:");
          Project res = ProjectServices.searchProject(pid);
          if(res!=null){
            ProjectServices.display(res);
          }
        }
    
    }

    public static void viewTeamStructure(int pid)
    {
      if(pid==0){
           System.out.println("You are currently not assigned to any Projects");
        }
        else{
         System.out.println("Team Details:");
         ProjectServices.viewProjectTeamStructure(pid);
        }
    
    }


    public static void applyLeave(int empId,int mgId, LocalDate dol, String reason,LocalDate applyDate){
      LeaveRequestServices.applyLeave(empId, mgId, dol, reason, applyDate);
    }

    public static void viewLeaveStatus(int empId,int mgId, LocalDate dol, String reason,LocalDate applyDate){
      LeaveRequestServices.empLeaveStatus(empId);
    }

    public static void timeSheetEntry(int empId,LocalDate date,int workingHours){
      AttendanceService.addWorkingHours(empId,date,workingHours);
    }

    public static void viewTimeSheetEntry(int empId,LocalDate fromDate){
      AttendanceService.viewTimeSheet(empId,fromDate);
    }

    public static void displayAvailableResources() {
        System.out.println("Available Employees (not assigned to projects):");
        boolean found = false;
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getProjectId() == 0) {
                displayProfile(DataStore.employees[i]);
                found = true;
            }
        }
        if(!found) {
            System.out.println("No available resources found.");
        }
    }

    public static void searchEmployeesBySkill(String skill) {
        System.out.println("Employees with skill: " + skill);
        boolean found = false;
        for(int i = 0; i < DataStore.empCount; i++) {
            String[] skills = DataStore.employees[i].getSkills();
            for(int j = 0; j < skills.length; j++) {
                if(skills[j].equalsIgnoreCase(skill)) {
                    displayProfile(DataStore.employees[i]);
                    found = true;
                    break;
                }
            }
        }
        if(!found) {
            System.out.println("No employees found with skill: " + skill);
        }
    }

    public static void assignEmployeeToProject(int empId, int projectId, int managerId) {
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getEmpId() == empId) {
                DataStore.employees[i].setProjectId(projectId);
                DataStore.employees[i].setMgId(managerId);
                System.out.println("Employee " + empId + " assigned to project " + projectId);
                return;
            }
        }
        System.out.println("Employee not found with ID: " + empId);
    }

    public static void addPerformanceReview(int empId, int rating, String feedback, int managerId) {
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getEmpId() == empId) {
                String performance = "Rating: " + rating + "/5, Feedback: " + feedback + " (Manager: " + managerId + ")";
                DataStore.employees[i].setPerformance(performance);
                System.out.println("Performance review added for employee " + empId);
                return;
            }
        }
        System.out.println("Employee not found with ID: " + empId);
    }

    public static void viewPerformanceReview(int empId) {
        for(int i = 0; i < DataStore.empCount; i++) {
            if(DataStore.employees[i].getEmpId() == empId) {
                String performance = DataStore.employees[i].getPerformance();
                if(performance != null && !performance.isEmpty()) {
                    System.out.println("Performance Review: " + performance);
                } else {
                    System.out.println("No performance review found for employee " + empId);
                }
                return;
            }
        }
        System.out.println("Employee not found with ID: " + empId);
    }

}
