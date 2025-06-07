package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = " + doctor_id + " AND wdate '"
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
