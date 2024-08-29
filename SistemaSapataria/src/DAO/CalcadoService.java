package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Calcado;

public class CalcadoService {

    public void salvar(Calcado calcado) {
        String sql = "INSERT INTO Calcados (codigo_calcado, nome, marca, valor, cor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, calcado.getCodigoCalcado());
            ps.setString(2, calcado.getNome());
            ps.setString(3, calcado.getMarca());
            ps.setDouble(4, calcado.getValor());
            ps.setString(5, calcado.getCor());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void alterarValor(Calcado calcado) {
        String sql = "UPDATE Calcados SET valor = ? WHERE codigo_calcado = ?";
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setDouble(1, calcado.getValor());
            stmt.setInt(2, calcado.getCodigoCalcado());
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Valor do calçado atualizado com sucesso!");
            } else {
                System.out.println("Nenhum calçado encontrado com esse código.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int codigoCalcado) {
        String sql = "DELETE FROM Calcados WHERE codigo_calcado = ?";
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, codigoCalcado);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Calçado excluído com sucesso!");
            } else {
                System.out.println("Nenhum calçado encontrado com esse código.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Calcado> consultarPorMarca(String marca) {
        String sql = "SELECT * FROM Calcados WHERE marca = ?";
        List<Calcado> calcados = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Calcado calcado = new Calcado(
                        rs.getInt("codigo_calcado"),
                        rs.getString("nome"),
                        rs.getString("marca"),
                        rs.getDouble("valor"),
                        rs.getString("cor")
                    );
                    calcados.add(calcado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calcados;
    }

    public List<Calcado> listarTodos() {
        String sql = "SELECT * FROM Calcados";
        List<Calcado> calcados = new ArrayList<>();

        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Calcado calcado = new Calcado(
                    rs.getInt("codigo_calcado"),
                    rs.getString("nome"),
                    rs.getString("marca"),
                    rs.getDouble("valor"),
                    rs.getString("cor")
                );
                calcados.add(calcado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calcados;
    }

    // Método para consultar um calçado pelo código
    public Calcado consultar(int codigoCalcado) {
        String sql = "SELECT * FROM Calcados WHERE codigo_calcado = ?";
        Calcado calcado = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigoCalcado);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    calcado = new Calcado(
                        rs.getInt("codigo_calcado"),
                        rs.getString("nome"),
                        rs.getString("marca"),
                        rs.getDouble("valor"),
                        rs.getString("cor")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calcado;
    }

    public boolean atualizarEstoque(int codigoCalcado, int quantidadeVendida) {
        String sql = "UPDATE Estoque SET quantidade = quantidade - ? WHERE codigo_calcado = ? AND quantidade >= ?";
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, quantidadeVendida);
            stmt.setInt(2, codigoCalcado);
            stmt.setInt(3, quantidadeVendida);
    
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
