import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class deleting {
		public static void main(String args[]) throws Exception {
			Scanner sc = new Scanner(System.in);
			Connection con =null;
			PreparedStatement st = null;
			String url ="jdbc:mysql://192.168.35.160:3306/trainee19";
			String user ="trainee19";
			String password = "trainee@Alac";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("\n Enter an Id to delete from friends table:");
			int iid = sc.nextInt();
			String query1 = "delete from friends where id="+iid;
			st= con.prepareStatement(query1);
			int n = st.executeUpdate();
			System.out.println(n+"row/s effected.");
			con.close();
			st.close();
		}
}
