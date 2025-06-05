package Model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bashekim extends User{
	Statement st = null; // Sql sorgusunu çalıştırmak için kullanılan Java sınıfı
	ResultSet rs = null; //  SQL komutundan dönen verileri tutar ve erişim sağlar
	Connection con = conn.connDb(); // veri tabanı bağlantısı nesnesi döner
	PreparedStatement preparedStatement = null;

	// Bashekim nesnesi oluşturulurken User'ın içindeki id, tcno gibi alanlara direkt atanır.
	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type); 
	}
	
	public Bashekim() {}; // arka planda super() çağırır bu yüzden User() {} lazım

	public ArrayList<User> getDoctorList(){
		ArrayList<User> list = new ArrayList<>();
		
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE type = 'doktor' ");
			while(rs.next()) 
			{
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"), rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}	
	
	public boolean addDoctor(String tcno, String password, String name) throws SQLException { // database' e ya eklenmiştir ya da eklenmemiştir bu yüzden bool
		
		String query = "INSERT INTO users" + "(tcno, password, name, type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); // SQL sorgusu için bir şablon (template) oluşturur. Bu şablon daha sonra içine değer yerleştirerek çalıştırılır.
			preparedStatement.setString(1, tcno); // 1. soru işaretini tcno ile değiştir 
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	public boolean deleteDoctor(int id) throws SQLException { 
		
		String query = "DELETE FROM users WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); // SQL sorgusu için bir şablon (template) oluşturur. Bu şablon daha sonra içine değer yerleştirerek çalıştırılır.
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	public boolean upateDoctor(int id, String tcno, String password, String name) throws SQLException { 
		
		String query = "UPDATE users SET name=?, tcno=?, password=? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); // SQL sorgusu için bir şablon (template) oluşturur. Bu şablon daha sonra içine değer yerleştirerek çalıştırılır.
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}

	
}
	
