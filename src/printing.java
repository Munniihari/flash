import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Example1 {
		public static void main(String args[]) throws Exception {
			String url = "jdbc:mysql://192.168.35.160:3306/trainee19";
			String uname = "trainee19";
			String pword ="trainee@Alac";
			String statement ="Select * from friends";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pword);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(statement);
			String UserData = "";
			while(rs.next()) {
				UserData = rs.getInt(1)+" : "+rs.getString(2)+" : "+rs.getString(3);
				System.out.println(UserData);
				
			}
			con.close();
			st.close();
		}
}
