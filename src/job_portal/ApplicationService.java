// ApplicationService.java

package job_portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ApplicationService {

    static Scanner sc = new Scanner(System.in);

    // APPLY JOB
    public static void applyJob() {
    	if(!UserService.role.equals("seeker")) {

    	    System.out.println("Only seekers can apply jobs!");
    	    return;
    	}
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Job ID: ");
            int jobId = sc.nextInt();

            String sql =
                "INSERT INTO applications(user_id,job_id,status) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, UserService.userId);
            ps.setInt(2, jobId);
            ps.setString(3, "Applied");

            ps.executeUpdate();

            System.out.println("Applied Successfully!");

        } catch (Exception e) {

            System.out.println(e);
        }
    }
    public static void viewMyApplications() {

        try(Connection con = DBConnection.getConnection()) {

            String sql =
            "SELECT * FROM applications WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, UserService.userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                System.out.println(
                    rs.getInt("app_id") + " | " +
                    rs.getInt("job_id") + " | " +
                    rs.getString("status")
                );
            }

        } catch(Exception e) {

            System.out.println(e);
        }
    }
        public static void updateApplicationStatus() {

        	if(!UserService.role.equals("recruiter")) {

        	    System.out.println("Only recruiters can update status!");
        	    return;
        	}
            try(Connection con = DBConnection.getConnection()) {

                System.out.print("Enter Application ID: ");
                int appId = sc.nextInt();

                sc.nextLine();

                System.out.print("Enter New Status: ");
                String status = sc.nextLine();

                String sql =
                "UPDATE applications SET status=? WHERE app_id=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, status);
                ps.setInt(2, appId);

                int rows = ps.executeUpdate();

                if(rows > 0) {

                    System.out.println("Status Updated Successfully!");

                } else {

                    System.out.println("Application Not Found!");
                }

            } catch(Exception e) {

                System.out.println(e);
            }
        
    }
        public static void saveJob() {

            try(Connection con = DBConnection.getConnection()) {

                System.out.print("Enter Job ID to Save: ");

                int jobId = sc.nextInt();

                String sql =
                "INSERT INTO saved_jobs(user_id, job_id) VALUES(?, ?)";

                PreparedStatement ps =
                con.prepareStatement(sql);

                ps.setInt(1, UserService.userId);

                ps.setInt(2, jobId);

                int rows = ps.executeUpdate();

                if(rows > 0) {

                    System.out.println("Job Saved Successfully!");

                } else {

                    System.out.println("Failed to Save Job!");
                }

            } catch(Exception e) {

                System.out.println(e);
            }
        }
        public static void viewSavedJobs() {

            try(Connection con = DBConnection.getConnection()) {

                String sql =
                "SELECT jobs.job_id, jobs.company_name, jobs.title, jobs.location " +
                "FROM jobs " +
                "JOIN saved_jobs ON jobs.job_id = saved_jobs.job_id " +
                "WHERE saved_jobs.user_id=?";

                PreparedStatement ps =
                con.prepareStatement(sql);

                ps.setInt(1, UserService.userId);

                ResultSet rs = ps.executeQuery();

                while(rs.next()) {

                    System.out.println(

                        rs.getInt("job_id") + " | " +

                        rs.getString("company_name") + " | " +

                        rs.getString("title") + " | " +

                        rs.getString("location")
                    );
                }

            } catch(Exception e) {

                System.out.println(e);
            }
        }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
