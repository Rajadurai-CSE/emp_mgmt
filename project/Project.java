package project;

import java.time.LocalDate;

public class Project {
    private int projectId;
    private String projectName;
    private String projectDesc;
    private int projectManager;
    private int projectTL;
    private LocalDate projectTimeLine;
    private String[] reqSkills;
    private String status;


    public Project(int projectId, String projectName, String projectDesc,
                   int projectManager, int projectTL, LocalDate projectTimeLine,
                   String[] reqSkills, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectManager = projectManager;
        this.projectTL = projectTL;
        this.projectTimeLine = projectTimeLine;
        this.reqSkills = reqSkills;
        this.status = status;

    }

    public Project(int projectId, String projectName, String projectDesc,
                   LocalDate startDate, LocalDate endDate, String[] reqSkills) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectManager = 0;
        this.projectTL = 0;
        this.projectTimeLine = endDate;
        this.reqSkills = reqSkills;
        this.status = "Planning";
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for projectId
    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    // Getter and Setter for projectName
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // Getter and Setter for projectDesc
    public String getProjectDesc() {
        return projectDesc;
    }
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    // Getter and Setter for projectManager
    public int getProjectManager() {
        return projectManager;
    }
    public void setProjectManager(int projectManager) {
        this.projectManager = projectManager;
    }

    // Getter and Setter for projectTL
    public int getProjectTL() {
        return projectTL;
    }
    public void setProjectTL(int projectTL) {
        this.projectTL = projectTL;
    }

    // Getter and Setter for projectTimeLine
    public LocalDate getProjectTimeLine() {
        return projectTimeLine;
    }
    public void setProjectTimeLine(LocalDate projectTimeLine) {
        this.projectTimeLine = projectTimeLine;
    }

    // Getter and Setter for reqSkills
    public String[] getReqSkills() {
        return reqSkills;
    }
    public void setReqSkills(String[] reqSkills) {
        this.reqSkills = reqSkills;
    }
    
    
    
}