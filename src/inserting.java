import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Example2 {
		public static void main(String args[]) throws Exception {
			String url ="jdbc:mysql://192.168.35.160:3306/trainee19";
			String user ="trainee19";
			String password = "trainee@Alac";
			System.out.println("Enter id and name/s and class to insert into DB");
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			String name = sc.next();
			String section = sc.next();
			String query = "insert into friends values(?,?,?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id);
			st.setString(2,name);
			st.setString(3, section);
			int count = st.executeUpdate();
			System.out.println(count+" row/s effected.");
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
