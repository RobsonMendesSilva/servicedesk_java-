package br.com.service_desk.view;

import java.awt.*;
import javax.swing.*;

import br.com.service_desk.dao.UsuarioDAO;
import br.com.service_desk.model.Usuario;

@SuppressWarnings("serial")
public class CadastroUsuarioFrame extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmaSenha;
    private JCheckBox chkAtivo;

    public CadastroUsuarioFrame() {
        setTitle("Service Desk - Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Título
        JLabel titulo = new JLabel("Novo Usuário");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 6, 15, 6);
        form.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(6, 6, 6, 6);

        int row = 1;
        txtNome = addCampo(form, gbc, row++, "Nome:");
        txtEmail = addCampo(form, gbc, row++, "E-mail:");
        txtLogin = addCampo(form, gbc, row++, "Login:");
        txtSenha = addCampoSenha(form, gbc, row++, "Senha:");
        txtConfirmaSenha = addCampoSenha(form, gbc, row++, "Confirmar senha:");

        // Checkbox "Ativo"
        chkAtivo = new JCheckBox("Ativo", true);
        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.weightx = 1.0;
        form.add(chkAtivo, gbc);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 6, 0, 6);
        form.add(botoes, gbc);

        add(form);
        pack();
        setLocationRelativeTo(null);

        btnSalvar.addActionListener(evt -> salvar());
        btnCancelar.addActionListener(evt -> dispose());
        getRootPane().setDefaultButton(btnSalvar);
    }

    /** Adiciona um par "label + campo texto" e retorna o campo criado. */
    private JTextField addCampo(JPanel form, GridBagConstraints gbc, int row, String label) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        form.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField campo = new JTextField(18);
        form.add(campo, gbc);
        return campo;
    }

    /** Adiciona um par "label + campo senha" e retorna o campo criado. */
    private JPasswordField addCampoSenha(JPanel form, GridBagConstraints gbc, int row, String label) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        form.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JPasswordField campo = new JPasswordField(18);
        form.add(campo, gbc);
        return campo;
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());
        String confirma = new String(txtConfirmaSenha.getPassword());
        boolean ativo = chkAtivo.isSelected();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nome, login e senha são obrigatórios.",
                    "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!senha.equals(confirma)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não conferem.",
                    "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            UsuarioDAO dao = new UsuarioDAO();

            if (dao.loginJaExiste(login)) {
                JOptionPane.showMessageDialog(this,
                        "Este login já está cadastrado.",
                        "Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Usuario u = new Usuario();
            u.setNome(nome);
            u.setEmail(email);
            u.setLogin(login);
            u.setSenha(senha);
            u.setAtivo(ativo);

            dao.inserir(u);

            JOptionPane.showMessageDialog(this,
                    "Usuário cadastrado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}