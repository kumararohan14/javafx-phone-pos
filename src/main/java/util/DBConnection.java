package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection Instance;
    private static Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root", "12345");

    }
    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if(Instance==null){
            return Instance=new DBConnection();
        }
        return Instance;

    }
}
