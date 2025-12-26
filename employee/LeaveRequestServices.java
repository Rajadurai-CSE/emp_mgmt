package employee;

import java.time.LocalDate;
import common.DataStore;
import employee.LeaveRequest;
public class LeaveRequestServices {

  public static void applyLeave(int empId,int mgId, LocalDate dol, String reason,LocalDate applyDate){
    int count = DataStore.leaveCount;
    DataStore.leaves[count] = new LeaveRequest(empId,mgId, dol,reason,applyDate);
    count++;
    DataStore.leaveCount = count;
    System.out.println("Sucessfully Applied Leave!!");
  }
  
  public static void empLeaveStatus(int empId){
    for(int i=0;i<DataStore.leaveCount;i++){

      if(DataStore.leaves[i].getEmpId() == empId){
        System.out.println("--------------------------------------------------");
        System.out.println("Date of Leave: "+DataStore.leaves[i].getDol());
        System.out.println("Apply Date: "+DataStore.leaves[i].getApplyDate());
        System.out.println("Status: "+DataStore.leaves[i].getStatus());
        System.out.println("--------------------------------------------------");
      }
    }
  }

  public static void viewPendingLeavesForManager(int managerId) {
    boolean found = false;
    for(int i = 0; i < DataStore.leaveCount; i++) {
      if(DataStore.leaves[i] != null && DataStore.leaves[i].getMgId() == managerId && DataStore.leaves[i].getStatus() != null && DataStore.leaves[i].getStatus().equalsIgnoreCase("pending")) {
        System.out.println("--------------------------------------------------");
        System.out.println("Leave Request ID: " + (i + 1));
        System.out.println("Employee ID: " + DataStore.leaves[i].getEmpId());
        System.out.println("Date of Leave: " + DataStore.leaves[i].getDol());
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

  public static void approveLeave(int leaveId, int managerId) {
    int index = leaveId - 1;
    if(index >= 0 && index < DataStore.leaveCount && DataStore.leaves[index].getMgId() == managerId) {
      DataStore.leaves[index].setStatus("Approved");
      System.out.println("Leave request approved successfully!");
    } else {
      System.out.println("Invalid leave request ID or access denied.");
    }
  }

  public static void rejectLeave(int leaveId, int managerId, String reason) {
    int index = leaveId - 1;
    if(index >= 0 && index < DataStore.leaveCount && DataStore.leaves[index].getMgId() == managerId) {
      DataStore.leaves[index].setStatus("Rejected");
      System.out.println("Leave request rejected. Reason: " + reason);
    } else {
      System.out.println("Invalid leave request ID or access denied.");
    }
  }
}
