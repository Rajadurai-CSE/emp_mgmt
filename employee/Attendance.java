package employee;

import java.time.LocalDate;

public class Attendance {
    private int empId;
    private LocalDate date;
    private int workingHours;

    public Attendance(int empId, LocalDate date, int workingHours) {
        this.empId = empId;
        this.date = date;
        this.workingHours = workingHours;
    }

    // Getter and Setter for empId
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    // Getter and Setter for date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and Setter for workingHours
    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
}
