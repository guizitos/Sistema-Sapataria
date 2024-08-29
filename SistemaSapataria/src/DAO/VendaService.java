package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Venda;

public class VendaService {

    public void salvar(Venda venda) {
        String sql = "INSERT INTO Vendas (data_venda, codigo_calcado, codigo_vendedor, quantidade_vendida, valor_total) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = Conexao.getConexao();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, venda.getDataVenda());
            stmt.setInt(2, venda.getCodigoCalcado());
            stmt.setInt(3, venda.getCodigoVendedor());
            stmt.setInt(4, venda.getQuantidadeVendida());
            stmt.setDouble(5, venda.getValorTotal());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Venda> listarTodas() {
        String sql = "SELECT * FROM Vendas";
        List<Venda> vendas = new ArrayList<>();

        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda(
                    rs.getInt("codigo_venda"),
                    rs.getDate("data_venda"),
                    rs.getInt("codigo_calcado"),
                    rs.getInt("codigo_vendedor"),
                    rs.getInt("quantidade_vendida"),
                    rs.getDouble("valor_total")
                );
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }
}
