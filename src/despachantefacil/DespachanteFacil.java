package despachantefacil;

import java.sql.Connection;
import java.sql.SQLException;

public class DespachanteFacil {

    public static void main(String[] args) {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.connect();  // conexão com o banco
            
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar conectar ao banco de dados:");
            e.printStackTrace();
        }
    }
    
}
