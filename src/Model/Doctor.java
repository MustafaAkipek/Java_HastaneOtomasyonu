package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {
	Statement st = null; // Sql sorgusunu çalıştırmak için kullanılan Java sınıfı
	ResultSet rs = null; // SQL komutundan dönen verileri tutar ve erişim sağlar
	Connection con = conn.connDb(); // veri tabanı bağlantısı nesnesi döner
	PreparedStatement preparedStatement = null;

	public Doctor() {
		super();
	}

	public Doctor(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public boolean addWhour(int doctor_id, String doctor_name, String wdate) throws Exception {
		int key = 0;
		int count = 0;
		
		String query = "INSERT INTO whour" + "(doctor_id, doctor_name, wdate) VALUES" + "(?, ?, ?)";
		try {
			st = con.createStatement(); // Bu nesne sayesinde SQL sorguları doğrudan çalıştırılabilir (yani PreparedStatement değil).
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = " + doctor_id + " AND wdate = '"
					+ wdate + "'"); // Sorgu çalıştırılıyor ve sonucu ResultSet (rs) içine alınıyor.
			
			while(rs.next()) {
				count++;
				break;
			}
			
			if(count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key == 1)
			return true;
		else
			return false;
	}
	
	public ArrayList<Whour> getWhourList(int doctor_id){
		ArrayList<Whour> list = new ArrayList<>();
		
		Whour obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = " + doctor_id);
			while(rs.next()) 
			{
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean deleteWhour(int id) throws SQLException { 
		
		String query = "DELETE FROM whour WHERE id = ?";
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

}
