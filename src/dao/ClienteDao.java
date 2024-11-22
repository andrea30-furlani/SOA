package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import singleton.Conexao;

public class ClienteDao {
    private Connection connection;

    public ClienteDao() {
        connection = Conexao.getConexao().conectar();
    }

    public String salvar(Cliente cliente) {
        String sql = "";
        if (cliente.getId() > 0) {
            sql = " UPDATE cliente SET nome = ?, email = ?, telefone = ?, tema_de_interesse = ? WHERE id = ?";

        } else {
            sql = "INSERT INTO cliente (nome, email, telefone, tema_de_interesse) VALUES (?, ?, ?, ?)";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone()); // Corrigido para setString
            ps.setString(4, cliente.getTemadeinteresse());
            if (cliente.getId() > 0)
                ps.setInt(5, cliente.getId());
            ps.execute();
            ps.close();
            return "Cliente gravado!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Houve um erro! Tente novamente!";
        }
    }

    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome")); // Corrigido para getString
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone")); // Corrigido para getString
                cliente.setTemadeinteresse(rs.getString("tema_de_interesse")); // Corrigido para getString
                listaClientes.add(cliente);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public Cliente getPorId(int id) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setTemadeinteresse(rs.getString("tema_de_interesse"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public String excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            return "Cliente excluído!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Houve um erro! Tente novamente!";
        }
    }

}
