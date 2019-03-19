import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.Blob;

public class BlobExample {
	public static void main(String args[]) throws Exception {
			BlobExample obj = new BlobExample();
			obj.Connection();
			obj.insert();
			obj.getImg();
		
			
		
	}
Connection con = null;
PreparedStatement st = null;
String url ="jdbc:mysql://192.168.35.160:3306/trainee19";
String user ="trainee19";
String password ="trainee@Alac";
File fl = new File("/home/harithap/Downloads/JDBC/one.jpeg");

public void Connection() {
	
	try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		System.out.println("Connected........");
		
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
public void insert() throws Exception{
	try {
	String query ="insert into image values (?, ?)";
	FileInputStream fis = new FileInputStream(fl);
	st = con.prepareStatement(query);
	st.setString(1,"Background");
	st.setBinaryStream(2, fis);
	int count = st.executeUpdate();
	System.out.println(count+" row/s effected.");
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
public void getImg() throws Exception {
	try {
		File fl = new File("/home/harithap/Downloads/JDBC/two.jpeg");
		FileOutputStream fos = new FileOutputStream(fl);
		byte b[];
		Blob blob;
		
		PreparedStatement ps=con.prepareStatement("select * from image"); 
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			blob=(Blob) rs.getBlob("image");
			b=blob.getBytes(1,(int)blob.length());
			fos.write(b);
		}
		
	}
	catch(Exception e) {
		System.out.println(e);
	}
	finally {
			System.out.println("Stored Successfully");
			con.close();
			st.close();
	}
	}

}
