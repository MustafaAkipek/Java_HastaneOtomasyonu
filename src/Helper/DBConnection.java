package Helper;

import java.sql.*;

public class DBConnection {
    Connection c = null;

    public DBConnection() {}

    public Connection connDb() {
        try {
            // SQL Server JDBC sürücüsünü yüklüyoruz
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Docker'daki MSSQL'e bağlantı stringi
            this.c = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:14330;databaseName=hospital;encrypt=false;",
                "sa", "MeUsTiAn0301!" // Kullanıcı adı ve şifreni yaz
            );

            return c;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
}
