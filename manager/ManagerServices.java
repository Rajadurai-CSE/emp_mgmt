package manager;

import java.time.LocalDate;
import java.util.Arrays;
import common.DataStore;
import employee.Employee;
import employee.EmployeeServices;
import employee.LeaveRequest;
import project.Project;
import project.ProjectServices;

public class ManagerServices{

    public void displayAllEmployees(){
            EmployeeServices.displayAllEmployees();
    }
    
    public void createProject(int projectId, String projectName, String projectDesc, int projectManager, int projectTL, LocalDate projectTimeLine, String[] reqSkills, String status)
    {
        Project p = new Project(projectId, projectName, projectDesc,projectManager, projectTL, projectTimeLine, reqSkills, status);
        int project_count = DataStore.projectCount;
        DataStore.projects[project_count] = p;
        project_count++;
        DataStore.projectCount = project_count;

    }

    public void viewProject(int projectId)
    {
        
        ProjectServices.viewProject(projectId);
    
    }

    public void vieweProjects(int mgId)
    {
        ProjectServices.viewProjects(mgId);
    }

    public void assignProject(int empId, int projectId ){
        for(int i = 0; i < DataStore.empCount; i++){
            Employee ele = DataStore.employees[i];
            if(ele != null && ele.getEmpId() == empId){
                ele.setProjectId(projectId);
                break;
            }
        }

    }

    public void searchEmpbySkills(String skill){
        for(int i = 0; i < DataStore.empCount; i++){
            Employee ele = DataStore.employees[i];
            if(ele == null){
                continue;
            }
            String[] skillsArr = ele.getSkills();
            for(int j = 0; j < skillsArr.length; j++){
                if(skillsArr[j] != null && skillsArr[j].equalsIgnoreCase(skill)){
                    EmployeeServices.displayProfile(ele);
                    break;
                }
            }
        }
        }

    public void projectStatus(int projectId){
        Project project = ProjectServices.searchProject(projectId);
        if(project!=null){
            System.out.println(project.getStatus());
        }
        else{
            System.out.println("Project not found");
        }
    }

    public static void updateProjectStatus(int projectId,String newStatus){
        ProjectServices.updateProjectStatus(projectId, newStatus);

    }

   public static void viewAvailableResources(){
        for(int i = 0; i < DataStore.empCount; i++){
            Employee ele = DataStore.employees[i];
            if(ele != null && ele.getProjectId() == 0){
                EmployeeServices.displayProfile(ele);
            }
        }
    }

    public static LeaveRequest[] LeaveApproval(){
        LeaveRequest[] leaves = new LeaveRequest[0];
        for(int i = 0; i < DataStore.leaveCount; i++){
            LeaveRequest leave = DataStore.leaves[i];
            if(leave != null && leave.getStatus() != null && leave.getStatus().equalsIgnoreCase("pending")){
                leaves = Arrays.copyOf(leaves, leaves.length+1);
                leaves[leaves.length-1] = leave;
            }

        }
        return leaves;
    }
}
    

    
    
