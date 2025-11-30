package br.com.academia.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Cone {
    private static final String URL = "jdbc:mysql://localhost:3306/db_acad?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Yudi4661@";

    private static java.sql.Connection conn;

    static {
        try {
            // Carrega driver moderno do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do MySQL não encontrado.");
            e.printStackTrace();
        }
    }

    public static synchronized java.sql.Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }

    public static synchronized void close() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
