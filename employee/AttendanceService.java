package employee;

import java.time.LocalDate;
import common.DataStore;
import employee.Attendance;

public class AttendanceService {
  
  public static void addWorkingHours(int empId,LocalDate date,int workingHours){
   int count = DataStore.attendanceCount;
   DataStore.attendances[count] = new Attendance(empId,date,workingHours);
   count++;
   DataStore.attendanceCount = count;
  }
  public static void viewTimeSheet(int empId,LocalDate fromDate){
   
   for(int i=0;i<DataStore.attendanceCount;i++){
    if(DataStore.attendances[i].getEmpId()==empId && fromDate.compareTo(DataStore.attendances[i].getDate())<=0){
      System.out.println(DataStore.attendances[i].getDate()+":"+DataStore.attendances[i].getWorkingHours());
    }
   }
  }
  
}
