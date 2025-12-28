package employee;

import java.time.LocalDate;
import common.DataStore;
import employee.LeaveRequest;
public class LeaveRequestServices {

  public static void applyLeave(int empId,int mgId, LocalDate dol, String reason,LocalDate applyDate,LocalDate endDate){
    int count = DataStore.leaveCount;
    DataStore.leaves[count] = new LeaveRequest(count+1,empId,mgId, dol,reason,applyDate,endDate);
    count++;
    DataStore.leaveCount = count;
    System.out.println("Sucessfully Applied Leave!!");
  }
  
  public static void empLeaveStatus(int empId){
    for(int i=0;i<DataStore.leaveCount;i++){

      if(DataStore.leaves[i].getEmpId() == empId){
        System.out.println("--------------------------------------------------");
        System.out.println("Date of Leave: "+DataStore.leaves[i].getLeaveRequestId());
        System.out.println("Date of Leave: "+DataStore.leaves[i].getDol());
        System.out.println("Apply Date: "+DataStore.leaves[i].getApplyDate());
         System.out.println("End Date: " + DataStore.leaves[i].getEndDate());
        
        System.out.println("Status: "+DataStore.leaves[i].getStatus());
         if(DataStore.leaves[i].getStatus().equalsIgnoreCase("rejected")){
          System.out.println("Status: "+DataStore.leaves[i].getRejectReason());
         }
        System.out.println("--------------------------------------------------");
      }
    }
  }

  public static void viewPendingLeavesForManager(int managerId) {
    boolean found = false;
    for(int i = 0; i < DataStore.leaveCount; i++) {
      if(DataStore.leaves[i].getMgId() == managerId && DataStore.leaves[i].getStatus().equalsIgnoreCase("pending")) {
        System.out.println("--------------------------------------------------");
        System.out.println("Leave Request ID: " + DataStore.leaves[i].getLeaveRequestId());
        System.out.println("Employee ID: " + DataStore.leaves[i].getEmpId());
        System.out.println("Date of Leave: " + DataStore.leaves[i].getDol());
         System.out.println("End Date: " + DataStore.leaves[i].getEndDate());
        System.out.println("Reason: " + DataStore.leaves[i].getReason());
        System.out.println("Apply Date: " + DataStore.leaves[i].getApplyDate());
        System.out.println("Status: " + DataStore.leaves[i].getStatus());
        System.out.println("--------------------------------------------------");
        found = true;
      }
    }
    if(!found) {
      System.out.println("No pending leave requests found.");
    }
  }

  public static void approveLeave(int leaveId) {
    boolean found = false;
    for(int i=0;i<DataStore.leaveCount;i++){
      LeaveRequest leave = DataStore.leaves[i];
      if(leave.getLeaveRequestId() == leaveId){
        found = true;
        leave.setStatus("Approved");
        break;
      }
    }
    if(found == false){
      System.out.println("Enter a valid Leave Id");
    }
  }

    public static void rejectLeave(int leaveId,String reason) {
    boolean found = false;
    for(int i=0;i<DataStore.leaveCount;i++){
      LeaveRequest leave = DataStore.leaves[i];
      if(leave.getLeaveRequestId() == leaveId){
        found = true;
        leave.setStatus("Rejected");
        leave.setRejectReason(reason);
        break;
      }
    }
    if(found == false){
      System.out.println("Enter a valid Leave Id");
    }
  }
}