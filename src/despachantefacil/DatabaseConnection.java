/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despachantefacil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost:5432/despachante";
    private final String user = "postgres";
    private final String password = "123";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao PostgreSQL com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
        }

        return conn;
    }
}
