package despachantefacil.models.dao;

import despachantefacil.DatabaseConnection;
import despachantefacil.models.Veiculo;
import despachantefacil.models.enums.CarroceriaENUM;
import despachantefacil.models.enums.CategoriaENUM;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VeiculoDAO {

    private final DatabaseConnection dbConnection = new DatabaseConnection();
    
    
    public List<Veiculo> getVeiculos() {
    String SQL = "SELECT * FROM Veiculo";
    List<Veiculo> veiculos = new ArrayList<>();
    
    try (Connection conn = dbConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)) {
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(rs.getInt("id"));
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setRenavam(rs.getString("renavam"));
            veiculo.setChassi(rs.getString("chassi"));
            veiculo.setMarcaModelo(rs.getString("marcaModelo"));
            veiculo.setAnoFab(rs.getInt("anoFab"));
            veiculo.setAnoMod(rs.getInt("anoMod"));
            veiculo.setCategoriaENUM(CategoriaENUM.valueOf(rs.getString("categoria")));
            veiculo.setCarroceriaENUM(CarroceriaENUM.valueOf(rs.getString("carroceria")));
            veiculo.setObservacoes(rs.getString("observacoes"));
            
            veiculos.add(veiculo);
        }
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
    }
    
    return veiculos;
}
    
    public Veiculo getVeiculoById(int id) {
    String SQL = "SELECT * FROM Veiculo WHERE id = ?";
    Veiculo veiculo = null;
    
    try (Connection conn = dbConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            veiculo = new Veiculo();
            veiculo.setId(rs.getInt("id"));
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setRenavam(rs.getString("renavam"));
            veiculo.setChassi(rs.getString("chassi"));
            veiculo.setMarcaModelo(rs.getString("marcaModelo"));
            veiculo.setAnoFab(rs.getInt("anoFab"));
            veiculo.setAnoMod(rs.getInt("anoMod"));
            veiculo.setObservacoes(rs.getString("observacoes"));

            String categoriaStr = rs.getString("categoria");
            if (categoriaStr != null) {
                veiculo.setCategoriaENUM(CategoriaENUM.valueOf(categoriaStr));
            }
            
            String carroceriaStr = rs.getString("carroceria");
            if (carroceriaStr != null) {
                veiculo.setCarroceriaENUM(CarroceriaENUM.valueOf(carroceriaStr));
            }
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return veiculo;
}

public void updateVeiculo(Veiculo veiculo) {
    String SQL = "UPDATE Veiculo SET placa = ?, renavam = ?, chassi = ?, marcaModelo = ?, anoFab = ?, anoMod = ?, categoria = ?, carroceria = ?, observacoes = ? WHERE id = ?";

    try (Connection conn = dbConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(SQL)) {

        pstmt.setString(1, veiculo.getPlaca());
        pstmt.setString(2, veiculo.getRenavam());
        pstmt.setString(3, veiculo.getChassi());
        pstmt.setString(4, veiculo.getMarcaModelo());
        pstmt.setInt(5, veiculo.getAnoFab());
        pstmt.setInt(6, veiculo.getAnoMod());
        pstmt.setString(7, veiculo.getCategoriaENUM().name());
        pstmt.setString(8, veiculo.getCarroceriaENUM().name());
        pstmt.setString(9, veiculo.getObservacoes());
        pstmt.setInt(10, veiculo.getId());
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(null, "Veículo atualizado com sucesso!");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
    }
}

    

    public void salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, renavam, chassi, marcaModelo, anoFab, anoMod, categoria, carroceria, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setString(2, veiculo.getRenavam());
            pstmt.setString(3, veiculo.getChassi());
            pstmt.setString(4, veiculo.getMarcaModelo());
            pstmt.setInt(5, veiculo.getAnoFab());
            pstmt.setInt(6, veiculo.getAnoMod());
            pstmt.setString(7, veiculo.getCategoriaENUM().toString());
            pstmt.setString(8, veiculo.getCarroceriaENUM().toString());
            pstmt.setString(9, veiculo.getObservacoes());

            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
        }
    }
}
