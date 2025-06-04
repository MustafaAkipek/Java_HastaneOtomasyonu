package Helper;

import java.sql.Connection;

public class main {
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        Connection conn = db.connDb();

        if (conn != null) {
            System.out.println("✅ Veritabanı bağlantısı başarılı!");
        } else {
            System.out.println("❌ Veritabanına bağlanılamadı.");
        }
    }
}
