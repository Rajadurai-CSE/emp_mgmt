package employee;

import java.time.LocalDate;

public class Employee {
    private int empId;
    private String empName;
    private String[] skills = new String[0];
    private String secret;
    private int projectId ;
    private String password;     // sensitive — never printed
    private LocalDate dob;          // YYYY-MM-DD
    private String accntNumber;  // masked on display
    private String performance;
    private int mgId;
    private boolean accountRegistered = false;

    public Employee(int empId, String empName, String secret, LocalDate dob) {
        this.empId = empId;
        this.empName = empName;
        this.secret = secret;
        this.dob = dob;
    }

    // --- Getters ---
    public int getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String[] getSkills() { return skills; }
    public int getProjectId() { return projectId; }
    public LocalDate getDob() { return dob; }
    public String getSecret(){return secret;}
    public String getPasswd(){return password;}
    public String getAccntNumber() { return accntNumber; }
    public int getMgId(){return mgId;}
    public boolean getAccntRegistered(){return accountRegistered;}
    public String getPerformance(){return performance;}
    // --- Setters (for requested fields) ---
    public void setEmpName(String empName) { this.empName = empName; }
    public void setAccntRegistered(boolean status) { this.accountRegistered = status; }
    public void setSecretToNull() { this.secret = null; }
    public void setSkills(String[] skills) { this.skills = skills; }
    public void setPassword(String password) { this.password = password; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setProjectId(int projectId) { this.projectId = projectId; }
    public void setAccntNumber(String accntNumber) { this.accntNumber = accntNumber; }
    public void setMgId(int mgId){this.mgId = mgId;}
    public void setPerformance(String performance){this.performance = performance;}
}