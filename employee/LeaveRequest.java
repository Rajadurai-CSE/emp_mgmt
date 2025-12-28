package employee;

import java.time.LocalDate;

public class LeaveRequest {
    private int leaveId;
    private int empId;
    private int mgId;
    private LocalDate dol;
    private String reason;
    private String status = "pending";
    private LocalDate applyDate;
    private String rejectReason;
    private LocalDate endDate;
    LeaveRequest(int leaveId,int id, int mgId, LocalDate dol, String reason,LocalDate applyDate,LocalDate endDate) {
        this.leaveId = leaveId;
        this.empId = id;
        this.mgId = mgId;
        this.dol = dol;
        this.reason = reason;
        this.applyDate = applyDate;
        this.endDate = endDate;
    }

    public int getLeaveRequestId(){
        return leaveId;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public String getRejectReason(){
        return rejectReason;
    }
    public void setRejectReason(String reason){
        this.rejectReason = reason;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

     public LocalDate getApplyDate() {
        return applyDate;
    }

    // Getter and Setter for mgId
    public int getMgId() {
        return mgId;
    }

    public void setMgId(int mgId) {
        this.mgId = mgId;
    }

    // Getter and Setter for dol
    public LocalDate getDol() {
        return dol;
    }

    public void setDol(LocalDate dol) {
        this.dol = dol;
    }

    // Getter and Setter for reason
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}