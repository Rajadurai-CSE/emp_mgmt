package project;

import java.time.LocalDate;
import common.DataStore;
import project.Project;

public class ProjectServices {

  public static void display(Project project){
    System.out.println("Project ID: " + project.getProjectId());
        System.out.println("Project Name: " + project.getProjectName());
        System.out.println("Description: " + project.getProjectDesc());
        System.out.println("Manager: " + project.getProjectManager());
        System.out.println("Team Lead: " + project.getProjectTL());
        System.out.println("Timeline: " + project.getProjectTimeLine());
        System.out.print("Required Skills: ");
        String[] skills = project.getReqSkills();
        for(int j=0;j<skills.length;j++){
          System.out.println(skills[j]);
        }
  }
  public static void viewProjects(int mgId)
    {
      for(int i=0;i<DataStore.projectCount;i++){
        if(DataStore.projects[i].getProjectManager() == mgId){
          Project project = DataStore.projects[i];
           display(project);
        }
      }
    }

    public static Project searchProject(int pid){
      int project_count = DataStore.projectCount;
      for(int i=0;i<project_count;i++){
        if(DataStore.projects[i].getProjectId() == pid){
          return DataStore.projects[i];
          
        }
      }
      return null;
    }

     public static Project viewProject(int pid){
      int project_count = DataStore.projectCount;
      for(int i=0;i<project_count;i++){
        if(DataStore.projects[i].getProjectId() == pid){
          display(DataStore.projects[i]);
          break;
        }
      }
      return null;
    }

    public static void viewProjectTeamStructure(int pid){
      Project res = searchProject(pid);
      if(res!=null){
        System.out.println("Your Project Manager : "+ res.getProjectManager());
        System.out.println(" ↓↓↓↓↓↓");
        System.out.println("Your Team Leader (Reporting)"+res.getProjectTL());
      }
    }

    public static void updateProjectStatus(int projectId,String newStatus){
      Project p = searchProject(projectId);
      if(p!=null){
        p.setStatus(newStatus);
        System.out.println("Project Status updated successfully!!");
      }
      else{
        System.out.println("Project not found.");
      }

    }

    public static void createProject(String projectName, String description, LocalDate startDate, LocalDate endDate, String[] requiredSkills, int managerId) {
        if(DataStore.projectCount < DataStore.projects.length) {
            Project project = new Project(DataStore.projectCount + 1, projectName, description, startDate, endDate, requiredSkills);
            project.setProjectManager(managerId);
            DataStore.projects[DataStore.projectCount] = project;
            DataStore.projectCount++;
            System.out.println("Project created successfully!");
        } else {
            System.out.println("Project storage is full!");
        }
    }

    public static void viewManagerProjects(int managerId) {
        boolean found = false;
        for(int i = 0; i < DataStore.projectCount; i++) {
            if(DataStore.projects[i].getProjectManager() == managerId) {
                display(DataStore.projects[i]);
                found = true;
            }
        }
        if(!found) {
            System.out.println("No projects found for this manager.");
        }
    }

    public static void viewProjectStatus(int projectId) {
        Project project = searchProject(projectId);
        if(project != null) {
            display(project);
            System.out.println("Status: " + project.getStatus());
        } else {
            System.out.println("Project not found.");
        }
    }

}
