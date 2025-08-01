package com.taskboard;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class BoardManager {
    private Board currentBoard;
    private Scanner scanner = new Scanner(System.in);
    
    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar novo Board");
            System.out.println("2. Selecionar Board");
            System.out.println("3. Excluir Board");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (choice) {
                case 1: createBoard(); break;
                case 2: selectBoard(); break;
                case 3: deleteBoard(); break;
                case 4: System.exit(0);
                default: System.out.println("Opção inválida!");
            }
        }
    }
    
    private void createBoard() {
        System.out.print("Nome do Board: ");
        String name = scanner.nextLine();
        
        try (Connection conn = Database.connect()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO boards (name) VALUES (?)", 
                Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, name);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int boardId = rs.getInt(1);
                createDefaultColumns(conn, boardId);
                System.out.println("Board criado com sucesso! ID: " + boardId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createDefaultColumns(Connection conn, int boardId) throws SQLException {
        String[] columns = {"To Do", "Doing", "Done", "Cancelled"};
        String[] types = {"INICIAL", "PENDENTE", "FINAL", "CANCELAMENTO"};
        
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO columns (board_id, name, type, `order`) VALUES (?, ?, ?, ?)");
        
        for (int i = 0; i < columns.length; i++) {
            stmt.setInt(1, boardId);
            stmt.setString(2, columns[i]);
            stmt.setString(3, types[i]);
            stmt.setInt(4, i+1);
            stmt.addBatch();
        }
        
        stmt.executeBatch();
    }
    
    // Métodos restantes (selectBoard, deleteBoard, cardOperations) seguindo mesma lógica
    // Implementação completa disponível no repositório
}
