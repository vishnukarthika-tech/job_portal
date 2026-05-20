// DBConnection.java
package job_portal;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/job_portal",
                "root",
                "vishnu"
            );

        } catch (Exception e) {

            System.out.println(e);
        }

        return con;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
