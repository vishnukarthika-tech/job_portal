// JobService.java

package job_portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JobService {

    static Scanner sc = new Scanner(System.in);

    // POST JOB
    public static void postJob() {

        if (!UserService.role.equals("recruiter")) {

            System.out.println("Only recruiters can post jobs!");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

           

            System.out.print("Company Name: ");
            String company = sc.nextLine();

            System.out.print("Job Title: ");
            String title = sc.nextLine();

            System.out.print("Skills: ");
            String skills = sc.nextLine();

            System.out.print("Salary: ");
            int salary = sc.nextInt();

            

            System.out.print("Location: ");
            String location = sc.nextLine();

            String sql =
                "INSERT INTO jobs(company_name,title,skills,salary,location) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, company);
            ps.setString(2, title);
            ps.setString(3, skills);
            ps.setInt(4, salary);
            ps.setString(5, location);

            ps.executeUpdate();

            System.out.println("Job Posted Successfully!");

        } catch (Exception e) {

            System.out.println(e);
        }
    }
    
    public static void deleteJob() {

        if(!UserService.role.equals("recruiter")) {

            System.out.println("Only recruiters can delete jobs!");
            return;
        }

        try(Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Job ID: ");
            int id = sc.nextInt();

            String sql =
            "DELETE FROM jobs WHERE job_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Job Deleted Successfully!");

            } else {

                System.out.println("Job Not Found!");
            }

        } catch(Exception e) {

            System.out.println(e);
        }
    }
    
    public static void updateJob() {

        if(!UserService.role.equals("recruiter")) {

            System.out.println("Only recruiters can update jobs!");
            return;
        }

        try(Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Job ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("New Title: ");
            String title = sc.nextLine();

            System.out.print("New Skills: ");
            String skills = sc.nextLine();

            System.out.print("New Salary: ");
            int salary = sc.nextInt();

            sc.nextLine();

            System.out.print("New Location: ");
            String location = sc.nextLine();

            String sql =
            "UPDATE jobs SET title=?, skills=?, salary=?, location=? WHERE job_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, skills);
            ps.setInt(3, salary);
            ps.setString(4, location);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Job Updated Successfully!");

            } else {

                System.out.println("Job Not Found!");
            }

        } catch(Exception e) {

            System.out.println(e);
        }
    }
    
    // VIEW JOBS
    public static void viewJobs() {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM jobs";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                    rs.getInt("job_id") + " | " +
                    rs.getString("company_name") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("skills") + " | " +
                    rs.getInt("salary") + " | " +
                    rs.getString("location")
                );
            }
            
        } 
        
        catch (Exception e) {

            System.out.println(e);
        }
    }
        public static void searchJobs() {

            try(Connection con = DBConnection.getConnection()) {

                System.out.print("Enter Skill: ");
                String skill = sc.nextLine();

                String sql =
                "SELECT * FROM jobs WHERE skills LIKE ?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, "%" + skill + "%");

                ResultSet rs = ps.executeQuery();

                while(rs.next()) {

                    System.out.println(
                        rs.getInt("job_id") + " | " +
                        rs.getString("company_name") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("skills") + " | " +
                        rs.getString("salary") + " | " +
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
