package despachantefacil.models.dao;

import despachantefacil.DatabaseConnection;
import despachantefacil.models.Cliente;
import despachantefacil.models.Endereco;
import despachantefacil.models.enums.SexoENUM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    
    public List<Cliente> getClientes() {
    String SQL = "SELECT * FROM Cliente";
    List<Cliente> clientes = new ArrayList<>();
    
    try (Connection conn = dbConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)) {
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setContato1(rs.getString("contato1"));
            cliente.setContato2(rs.getString("contato2"));
            cliente.setObservacoes(rs.getString("observacoes"));
            cliente.setFilePath(rs.getString("filePath"));
            
            clientes.add(cliente);
        }
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
    }
    
    return clientes;
}
    
    public void updateCliente(Cliente cliente) {
    String SQL = "UPDATE Cliente SET nome = ?, cpf = ?, endereco_id = ?, sexo = ?, contato1 = ?, contato2 = ?, observacoes = ?, filePath = ? WHERE id = ?";

    // atualização do Endereco
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    enderecoDAO.updateEndereco(cliente.getEndereco());

    // agora, atualização do Cliente
    try (Connection conn = dbConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(SQL)) {

        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getCpf());
        pstmt.setInt(3, cliente.getEndereco().getId());
        pstmt.setString(4, cliente.getSexoENUM().name());
        pstmt.setString(5, cliente.getContato1());
        pstmt.setString(6, cliente.getContato2());
        pstmt.setString(7, cliente.getObservacoes());
        pstmt.setString(8, cliente.getFilePath());
        pstmt.setInt(9, cliente.getId());
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
    }
}

    public Cliente getClienteById(int id) {
    String SQL = "SELECT * FROM Cliente WHERE id = ?";
    String enderecoSQL = "SELECT * FROM Endereco WHERE id = ?";
    Cliente cliente = null;
    
    try (Connection conn = dbConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setContato1(rs.getString("contato1"));
            cliente.setContato2(rs.getString("contato2"));
            cliente.setObservacoes(rs.getString("observacoes"));
            cliente.setFilePath(rs.getString("filePath"));
            String sexoStr = rs.getString("sexo");
            if (sexoStr != null) {
                cliente.setSexoENUM(SexoENUM.valueOf(sexoStr));
            }

            int enderecoId = rs.getInt("endereco_id");
            if (!rs.wasNull()) {
                PreparedStatement pstmtEndereco = conn.prepareStatement(enderecoSQL);
                pstmtEndereco.setInt(1, enderecoId);
                ResultSet rsEndereco = pstmtEndereco.executeQuery();
                if (rsEndereco.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setId(rsEndereco.getInt("id"));
                    endereco.setLogradouro(rsEndereco.getString("logradouro"));
                    endereco.setBairro(rsEndereco.getString("bairro"));
                    endereco.setCidade(rsEndereco.getString("cidade"));
                    endereco.setCep(rsEndereco.getString("cep"));
                    endereco.setUf(rsEndereco.getString("uf"));
                    endereco.setNumero(rsEndereco.getInt("numero"));
                    cliente.setEndereco(endereco);
                } else {
                    System.out.println("Endereco com id " + enderecoId + " não encontrado.");
                }
            } else {
                System.out.println("Cliente com id " + id + " não tem endereco_id.");
            }
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return cliente;
}

    public int saveCliente(Cliente cliente) throws SQLException {
            String SQL = "INSERT INTO Cliente (nome, cpf, endereco_id, sexo, contato1, contato2, observacoes, filePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setInt(3, cliente.getEndereco().getId());
            pstmt.setString(4, cliente.getSexoENUM().name());
            pstmt.setString(5, cliente.getContato1());
            pstmt.setString(6, cliente.getContato2());
            pstmt.setString(7, cliente.getObservacoes());
            pstmt.setString(8, cliente.getFilePath());
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");

        }
        return 0;
    }
}
