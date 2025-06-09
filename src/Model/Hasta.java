package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User{
	Statement st = null; // Sql sorgusunu çalıştırmak için kullanılan Java sınıfı
	ResultSet rs = null; // SQL komutundan dönen verileri tutar ve erişim sağlar
	Connection con = conn.connDb(); // veri tabanı bağlantısı nesnesi döner
	PreparedStatement preparedStatement = null;

	public Hasta() {
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}
	
	public boolean register(String tcno, String password, String name) throws Exception {
		int key = 0;
		boolean duplicate = false;
		
		String query = "INSERT INTO users" + "(tcno, password, name, type) VALUES" + "(?, ?, ?, ?)";
		try {
			st = con.createStatement(); // Bu nesne sayesinde SQL sorguları doğrudan çalıştırılabilir (yani PreparedStatement değil).
			rs = st.executeQuery("SELECT * FROM users WHERE tcno = '" + tcno + "'"); // Sorgu çalıştırılıyor ve sonucu ResultSet (rs) içine alınıyor.
			
			while(rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu TC numarasına ait bir kayıt bulunmaktadır!");
				break;
			}
			
			if(!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key == 1)
			return true;
		else
			return false;
	}
	
	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String appDate) throws Exception {
		int key = 0;
		String query = "INSERT INTO appointments" + "(doctor_id, doctor_name, hasta_id, hasta_name, app_date) VALUES" + "(?, ?, ?, ?, ?)";
		
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, appDate);
			preparedStatement.executeUpdate();
			key = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key == 1)
			return true;
		else
			return false;
	}
	
	public boolean updateWhourStatus(int doctor_id, String wDate) throws Exception {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";
		
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wDate);
			preparedStatement.executeUpdate();
			key = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key == 1)
			return true;
		else
			return false;
	} 
}
