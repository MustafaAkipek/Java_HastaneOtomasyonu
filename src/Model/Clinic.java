package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {
	private int id;
	private String name;

	Statement st = null; // Sql sorgusunu çalıştırmak için kullanılan Java sınıfı
	ResultSet rs = null; // SQL komutundan dönen verileri tutar ve erişim sağlar
	DBConnection conn = new DBConnection();
	PreparedStatement preparedStatement = null; // prepareStatement, SQL sorgusunu veritabanına göndermeden önce hazırlayan bir metottur

	public Clinic() {
	};

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<Clinic> getList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		Connection con = conn.connDb(); // veri tabanı bağlantısı nesnesi döner
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinics");
			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
	
	public boolean addClinic(String name) throws SQLException { // database' e ya eklenmiştir ya da eklenmemiştir bu yüzden bool
		
		String query = "INSERT INTO clinics" + "(name) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); // SQL sorgusu için bir şablon (template) oluşturur. Bu şablon daha sonra içine değer yerleştirerek çalıştırılır.
			preparedStatement.setString(1, name); // 1. soru işaretini tcno ile değiştir 
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
	
	public boolean deleteClinic(int id) throws SQLException { 
		
		String query = "DELETE FROM clinics WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
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
	
	public boolean updateClinic(int id, String name) throws SQLException { 
		
		String query = "UPDATE clinics SET name=? WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); // SQL sorgusu için bir şablon (template) oluşturur. Bu şablon daha sonra içine değer yerleştirerek çalıştırılır.
			preparedStatement.setString(1, name); // ilk ? ne string bir değer yerleştirir
			preparedStatement.setInt(2, id);
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
	
	public Clinic getFetch(int id) throws SQLException {
		Connection con = conn.connDb();
		Clinic c = new Clinic();
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM clinics WHERE id = " + id);
		while(rs.next()) {
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			break;
		}
		
		return c;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
