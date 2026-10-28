package br.com.service_desk.view;

import java.awt.*;
import javax.swing.*;

import br.com.service_desk.dao.UsuarioDAO;
import br.com.service_desk.model.Usuario;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtLogin;
    private JPasswordField txtSenha;

    public LoginFrame() {
        setTitle("Service Desk - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Título
        JLabel titulo = new JLabel("Acesso ao Sistema");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 6, 15, 6);
        form.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(6, 6, 6, 6);

        // Login
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Login:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtLogin = new JTextField(15);
        form.add(txtLogin, gbc);

        // Senha
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        form.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtSenha = new JPasswordField(15);
        form.add(txtSenha, gbc);

        // Botões
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar");

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botoes.add(btnCadastrar);
        botoes.add(btnEntrar);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 6, 0, 6);
        form.add(botoes, gbc);

        add(form);
        pack();
        setLocationRelativeTo(null);

        btnEntrar.addActionListener(e -> autenticar());
        btnCadastrar.addActionListener(e -> {
            CadastroUsuarioFrame cadastro = new CadastroUsuarioFrame();
            cadastro.setLocationRelativeTo(this);
            cadastro.setVisible(true);
        });

        getRootPane().setDefaultButton(btnEntrar);
    }

    private void autenticar() {
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());
        try {
            Usuario u = new UsuarioDAO().autenticar(login, senha);
            if (u != null) {
                new ServiceDeskFrame(u).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}