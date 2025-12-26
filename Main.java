import java.util.Scanner;
import common.RandomSeed;
import admin.AdminApp;
import manager.ManagerApp;
import employee.EmployeeApp;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RandomSeed.genRandom();
        
        System.out.println("=================================");
        System.out.println("   Employee Management System");
        System.out.println("=================================");
        
        while(true) {
            System.out.println("\nSelect your role:");
            System.out.println("1. Admin");
            System.out.println("2. Manager");
            System.out.println("3. Employee");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            
            switch(choice) {
                case 1:
                    AdminApp.main(new String[]{});
                    break;
                case 2:
                    ManagerApp.main(new String[]{});
                    break;
                case 3:
                    EmployeeApp.main(new String[]{});
                    break;
                case 4:
                    System.out.println("Thank you for using Employee Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
