import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class DAO_Example {
	public static void main(String args[]) throws Exception{
		System.out.println("Enter id to get info:");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		FriendDAO obj = new FriendDAO();
		obj.connection();
		Friend friend = obj.getFriend(id);
		System.out.println(friend.toString());
		System.out.println("\n");
		System.out.println("Enter id, name and class to insert into Table");
		int iid = sc.nextInt();
		String name = sc.next();
		String section = sc.next();
		Friend friend2 = obj.SetFriend(iid,name,section);
	}
}
class FriendDAO { 
	String url ="jdbc:mysql://192.168.35.160:3306/trainee19";
	String user ="trainee19";
	String password = "trainee@Alac";
	Connection con =null;
	Statement stt = null;
	ResultSet rs = null;
	PreparedStatement st = null;
	public void connection() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Connected.\n");
		}
	}

public  Friend getFriend(int iid)  {
			try {
				String query = "select name, class from friends where id="+iid;
				Friend fr = new Friend();
				fr.id = iid;
				stt = con.createStatement();
				rs = stt.executeQuery(query);
				rs.next();
				 fr.name = rs.getString("name");
				 fr.section = rs.getString("class");
			 return fr;
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
			return null;
	}
	
public Friend SetFriend(int iid, String name, String section) throws Exception {
	try {
		String query ="insert into friends values(?,?,?)";
		Friend f = new Friend();
		st = con.prepareStatement(query);
		st.setInt(1, iid);
		st.setString(2, name);
		st.setString(3, section);
		int count = st.executeUpdate();
		System.out.println("\n"+count+" row/s effected.");
	}
	catch(Exception e) {
		System.out.println(e);
	}
	finally {
		System.out.println("\nSuccessfully Inserted.");
		con.close();
		st.close();
	}
	return null;
}
}
class Friend{
	int id;
	String name;
	String section;
	@Override
	public String toString() {
		System.out.println("");
		return "[id= " + id + ", name= " + name + ", section= " + section + "]";
	}
	
}