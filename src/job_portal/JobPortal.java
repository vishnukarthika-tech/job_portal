// JobPortal.java

package job_portal;

import java.util.Scanner;

public class JobPortal {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== SMART JOB PORTAL =====");

            // NOT LOGGED IN
            if (UserService.userId == -1) {

                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        UserService.register();
                        break;

                    case 2:
                        UserService.login();
                        break;

                    case 3:
                        System.exit(0);
                        System.out.println("Thank you for using our job portal");
                        
                    default:
                        System.out.println("Invalid Choice!");
                }

            } else {

                // LOGGED IN
            	System.out.println("1. View Jobs");
            	System.out.println("2. Search Jobs");
            	System.out.println("3. Apply Job");
            	System.out.println("4. My Applications");
            	System.out.println("5. Save Job");
            	System.out.println("6. View Saved Jobs");
            	System.out.println("7. Upload Resume");
            	System.out.println("8. Post Job");
            	System.out.println("9. Delete Job");
            	System.out.println("10. Update Job");
            	System.out.println("11. Update Application Status");
           
            	System.out.println("12. Logout");
                
            	System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {

                case 1:
                    JobService.viewJobs();
                    break;

                case 2:
                    JobService.searchJobs();
                    break;

                case 3:
                    ApplicationService.applyJob();
                    break;

                case 4:
                    ApplicationService.viewMyApplications();
                    break;

                case 5:
                    ApplicationService.saveJob();
                    break;

                case 6:
                    ApplicationService.viewSavedJobs();
                    break;

                case 7:
                    UserService.uploadResume();
                    break;

                case 8:
                    JobService.postJob();
                    break;

                case 9:
                    JobService.deleteJob();
                    break;

                case 10:
                    JobService.updateJob();
                    break;

                case 11:
                    ApplicationService.updateApplicationStatus();
                    break;

                
                case 12:
                    UserService.logout();
                    break;

                default:
                    System.out.println("Invalid Choice!");
                }
            }
        }
    }
}