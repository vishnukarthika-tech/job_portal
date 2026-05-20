// UserService.java

package job_portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.*;
import java.io.File;
public class UserService {

    static Scanner sc = new Scanner(System.in);

    public static int userId = -1;
    public static String role = "";

    // REGISTER
    public static void register() {

        try (Connection con = DBConnection.getConnection()) {

            

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();
            
            String checkSql = "SELECT * FROM users WHERE email=?";

            PreparedStatement checkPs = con.prepareStatement(checkSql);

            checkPs.setString(1, email);

            ResultSet rs = checkPs.executeQuery();

            if(rs.next()) {

                System.out.println("Email already exists!");
                return;
            }

            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("Role (seeker/recruiter): ");
            String roleInput = sc.nextLine();

            String sql =
                "INSERT INTO users(name,email,password,role) VALUES (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, roleInput);

            ps.executeUpdate();

            System.out.println("Registered Successfully!");

        } catch (Exception e) {

            System.out.println(e);
        }
    }
    // LOGIN
    public static void login() {
        try (Connection con = DBConnection.getConnection()) {
            
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            String sql =
                "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                userId = rs.getInt("user_id");
                role = rs.getString("role");

                System.out.println("Login Successful!");
                System.out.println("Welcome " + rs.getString("name"));

            } else {

                System.out.println("Invalid Login!");
            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    // LOGOUT
    public static void logout() {

        userId = -1;
        role = "";

        System.out.println("Logged out Successfully!");
    }
    public static void uploadResume() {

        try {

            sc.nextLine(); // 🔥 IMPORTANT FIX

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                String path = file.getAbsolutePath();

                System.out.println("Resume Uploaded!");
                System.out.println("File Path: " + path);

            } else {

                System.out.println("No File Selected");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
