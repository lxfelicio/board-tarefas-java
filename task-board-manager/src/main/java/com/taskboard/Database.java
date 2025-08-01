package com.taskboard;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/taskboard_db";
    private static final String USER = "root";
    private static final String PASS = "password";
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static void initialize() {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            
            stmt.execute("CREATE TABLE IF NOT EXISTS boards (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL)");
                
            stmt.execute("CREATE TABLE IF NOT EXISTS columns (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "board_id INT NOT NULL, " +
                "name VARCHAR(50) NOT NULL, " +
                "type ENUM('INICIAL','PENDENTE','FINAL','CANCELAMENTO') NOT NULL, " +
                "`order` INT NOT NULL, " +
                "FOREIGN KEY (board_id) REFERENCES boards(id))");
                
            stmt.execute("CREATE TABLE IF NOT EXISTS cards (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "column_id INT NOT NULL, " +
                "title VARCHAR(100) NOT NULL, " +
                "description TEXT, " +
                "creation_date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "is_blocked BOOLEAN DEFAULT FALSE, " +
                "block_reason TEXT, " +
                "FOREIGN KEY (column_id) REFERENCES columns(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
