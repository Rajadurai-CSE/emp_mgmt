package employee;

import java.time.LocalDate;
import java.util.Scanner;
import common.DataStore;

public class EmployeeApp {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Employee Services");
    char ch ='y';
    while(ch=='y'){
      System.out.println("Enter 1.Login 2.Register");
      int input_ = sc.nextInt();
      if(input_ == 1){
        Employee curr_emp = null;
        System.out.println("Enter EmpID:");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Password:");
        String passwd = sc.nextLine();
        boolean login = false;
        for(int i=0;i<DataStore.empCount;i++){
          if(DataStore.employees[i].getEmpId() == empId && DataStore.employees[i].getPasswd() != null && DataStore.employees[i].getPasswd().equals(passwd) && DataStore.employees[i].getPasswd().length()!=0){
            curr_emp = DataStore.employees[i];
            login = true;
          }
        }
        if(login == false){
          System.out.println("Invalid Credentials. Try Again!!");
          break;
        }
        else{
          System.out.println("Welcome "+empId);
          System.out.println("Services:");
          int option = 0;
          sc.nextLine();
          while(option!=9){
            System.out.println("1.View/Edit Profile  2.Apply Leave 3.Track Leave Status 4.View Project Allocation 5.Timesheet 6.View Team Structure 7.View Time Sheet 8.View Performance Review 9.Logout");

            System.out.println("Enter option");
            
            option = sc.nextInt();
            sc.nextLine();

            if(option == 9){
              System.out.println("Logging out!!");
              break;
            }
            else if(option == 1){
              System.out.println("--------------------------------------------------");
              EmployeeServices.displayProfile(curr_emp);
              System.out.println("Enter 1.Change Name 2.Add Skills 3.Change Password 4.Change Bank Account Number");
              int choice = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Value:");
              String val = sc.nextLine();
              EmployeeServices.update(curr_emp, choice, val);
              System.out.println("Updated Successfully");
              EmployeeServices.displayProfile(curr_emp);
              System.out.println("--------------------------------------------------");
              System.out.println("Returning to main menu.");
            }

            else if(option == 2){
              System.out.println("--------------------------------------------------");
              System.out.println("Enter of date of leave");
              System.out.println("Enter day of month");
              int day = sc.nextInt();
              System.out.println("Enter Month");
              int mon = sc.nextInt();
              System.out.println("Enter Year");
              int year = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Reason");
              String reason = sc.nextLine();
              EmployeeServices.applyLeave(empId, curr_emp.getMgId(), LocalDate.of(year,mon,day), reason, LocalDate.now());
              System.out.println("--------------------------------------------------");
            }

            else if(option == 3){
              LeaveRequestServices.empLeaveStatus(empId);
            }
            else if(option ==4){
              EmployeeServices.viewProject(curr_emp.getProjectId());
            }

            else if(option ==5){
              System.out.println("Enter Working Hours");
              int workingHours = sc.nextInt();
              EmployeeServices.timeSheetEntry(empId, LocalDate.now(), workingHours);
            }

            else if(option == 6){
              EmployeeServices.viewTeamStructure(curr_emp.getProjectId());
            }
            else if(option == 7){
              System.out.println("Enter From Date:");
              System.out.println("Enter day of month");
              int day = sc.nextInt();
              System.out.println("Enter month");
              int month = sc.nextInt();
              System.out.println("Enter year");
              int year = sc.nextInt();
              sc.nextLine();
              EmployeeServices.viewTimeSheetEntry(empId, LocalDate.of(year, month, day));
            }
            else if(option == 8){
              System.out.println("--------------------------------------------------");
              EmployeeServices.viewPerformanceReview(empId);
              System.out.println("--------------------------------------------------");
            }
            else{
              System.out.println("Invalid Option");
              System.out.println("Try Again!!");
            }
          }
        }

      } 
      else if(input_ == 2){
        System.out.println("Enter your Employee Id");
        int empId = sc.nextInt();
        boolean empIdFound = false;
        sc.nextLine();
        for(int i=0;i<DataStore.empCount;i++){
          if(DataStore.employees[i].getEmpId() == empId){
            empIdFound = true;
            Employee emp = DataStore.employees[i];
            if(emp.getAccntRegistered() == false){
              System.out.println("Enter your secret");
              String secret = sc.nextLine();
              if(secret.equals(emp.getSecret())){
                System.out.println("Enter new password");
                String passwd = sc.nextLine();
                emp.setPassword(passwd);
                emp.setAccntRegistered(true);
                System.out.println("Success!! You have been successfully registered");
                break;
              }
              else{
                System.out.println("Incorrect Secret!!");
                System.out.println("Try Again");
                break;
              }
            }
            else{
                System.out.println("Account already registered.");
                break;
              }
          }
        }

        if(empIdFound == false){
          System.out.println("User not found!!");
        }
        
      }
      else{
        break;
      }
    }

  }
}
