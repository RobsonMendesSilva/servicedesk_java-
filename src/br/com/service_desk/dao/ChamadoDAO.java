package br.com.service_desk.dao;

import java.sql.*;
import java.util.*;
import br.com.service_desk.model.Chamado;
import br.com.service_desk.util.ConnectionFactory;

public class ChamadoDAO {

    public void inserir(Chamado c) throws SQLException {
        String sql = "INSERT INTO chamados (titulo, descricao, solicitante, status, usuario_id) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitulo());
            stmt.setString(2, c.getDescricao());
            stmt.setString(3, c.getSolicitante());
            stmt.setString(4, c.getStatus());
            stmt.setInt(5, c.getUsuarioId());
            stmt.executeUpdate();
        }
    }

    public List<Chamado> listar() throws SQLException {
        List<Chamado> lista = new ArrayList<>();
        String sql = "SELECT * FROM chamados ORDER BY id DESC";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Chamado c = new Chamado();
                c.setId(rs.getInt("id"));
                c.setTitulo(rs.getString("titulo"));
                c.setDescricao(rs.getString("descricao"));
                c.setSolicitante(rs.getString("solicitante"));
                c.setStatus(rs.getString("status"));
                c.setUsuarioId(rs.getInt("usuario_id"));
                lista.add(c);
            }
        }
        return lista;
    }

    public void atualizar(Chamado c) throws SQLException {
        String sql = "UPDATE chamados SET titulo=?, descricao=?, solicitante=?, status=? " +
                     "WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitulo());
            stmt.setString(2, c.getDescricao());
            stmt.setString(3, c.getSolicitante());
            stmt.setString(4, c.getStatus());
            stmt.setInt(5, c.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM chamados WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}