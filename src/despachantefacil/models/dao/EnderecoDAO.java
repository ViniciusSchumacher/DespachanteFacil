package despachantefacil.models.dao;

import despachantefacil.DatabaseConnection;
import despachantefacil.models.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoDAO {
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public int saveEndereco(Endereco endereco) throws SQLException {
        String SQL = "INSERT INTO Endereco(logradouro, numero, bairro, cep, cidade, uf) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getUf());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return 0;
    }
    
    public void updateEndereco(Endereco endereco) {
    String SQL = "UPDATE Endereco SET logradouro = ?, numero = ?, bairro = ?, cidade = ?, uf = ?, cep = ? WHERE id = ?";

    try (Connection conn = dbConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(SQL)) {

        pstmt.setString(1, endereco.getLogradouro());
        pstmt.setInt(2, endereco.getNumero());
        pstmt.setString(3, endereco.getBairro());
        pstmt.setString(4, endereco.getCidade());
        pstmt.setString(5, endereco.getUf());
        pstmt.setString(6, endereco.getCep());
        pstmt.setInt(7, endereco.getId());
        pstmt.executeUpdate();

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    
}
