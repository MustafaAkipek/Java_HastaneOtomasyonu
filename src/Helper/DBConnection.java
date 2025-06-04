package Helper;

import java.sql.*;

public class DBConnection {
    Connection c = null;

    public DBConnection() {}

    // Connection metodun dönüş tipi
    public Connection connDb() {
        try {
            // SQL Server JDBC sürücüsünü yüklüyoruz
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Docker'daki MSSQL'e bağlantı stringi
            this.c = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:14330;databaseName=hospital;encrypt=false;",
                "sa", "MeUsTiAn0301!"
            );

            return c;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
}
