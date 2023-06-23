/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despachantefacil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQuery {
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public void someQuery() {
        String SQL = "SELECT * FROM some_table";

        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                System.out.println(rs.getString("column_name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
