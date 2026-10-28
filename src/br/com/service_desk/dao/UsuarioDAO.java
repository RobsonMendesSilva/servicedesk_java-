package br.com.service_desk.dao;

import java.sql.*;
import br.com.service_desk.model.Usuario;
import br.com.service_desk.util.ConnectionFactory;

public class UsuarioDAO {

    public Usuario autenticar(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ? AND ativo = TRUE";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setLogin(rs.getString("login"));
                    return u;
                }
            }
        }
        return null;
    }

    /**
     * Insere um novo usuário no banco.
     */
    public void inserir(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, login, senha, ativo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getLogin());
            stmt.setString(4, u.getSenha());
            stmt.setBoolean(5, true); // ativo por padrão

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    u.setId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Verifica se já existe um usuário com o login informado.
     */
    public boolean loginJaExiste(String login) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE login = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}