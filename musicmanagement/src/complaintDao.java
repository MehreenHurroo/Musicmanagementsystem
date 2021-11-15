import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class complaintDao {
		private String dbUrl = "jdbc:mysql://localhost:3306/HOSPITAL?useSSL=false";
		private String dbUname = "root";
		private String dbPassword = "put code here";
		private String dbDriver = "com.mysql.cj.jdbc.Driver";
		public void loadDriver(String dbDriver)
		{
			try {
				Class.forName(dbDriver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Connection getConnection()
		{
			Connection con = null;
			try {
				con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
		
		public boolean insert(complaint  complaint)
		{
			loadDriver(dbDriver);
			Connection con = getConnection();
			//String result = "Data entered successfully";
			boolean res =false;
			String sql = "insert into complain values(?,?,?)";
			
			PreparedStatement ps;
			try {
			ps = con.prepareStatement(sql);
			ps.setString(1, complaint.getDate());
			ps.setString(2, complaint.getUsername());
			ps.setString(3, complaint.getComplain());
			ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				res = true;
			}
			return res;
		}
		
}
