package common;

import employee.Employee;
import project.Project;
import employee.LeaveRequest;
import employee.Attendance;
import manager.Manager;

public class DataStore {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin@123";
    public static Employee[] employees = new Employee[100];
    public static Project[] projects = new Project[50];
    public static LeaveRequest[] leaves = new LeaveRequest[100];
    public static Attendance[] attendances = new Attendance[100];
    public static Manager[] managers = new Manager[50];
    public static int empCount = 0;
    public static int projectCount = 0;
    public static int leaveCount = 0;
    public static int attendanceCount = 0;
    public static int managerCount = 0;

    public static String getAdminUsername(){return ADMIN_USERNAME;}
    public static String getAdminPassword(){return ADMIN_PASSWORD;}
}