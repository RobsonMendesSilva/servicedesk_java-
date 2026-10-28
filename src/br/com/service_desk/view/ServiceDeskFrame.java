package br.com.service_desk.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.service_desk.dao.ChamadoDAO;
import br.com.service_desk.model.Chamado;
import br.com.service_desk.model.Usuario;



public class ServiceDeskFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Usuario usuarioLogado;
	private final ChamadoDAO dao = new ChamadoDAO();
	private JTextField txtTitulo, txtSolicitante;
	private JTextArea txtDescricao;
	private JComboBox<String> cbStatus;
	private JTable tabela;
	private DefaultTableModel modeloTabela;
	private int idSelecionado = -1;

	public ServiceDeskFrame(Usuario u) {
		this.usuarioLogado = u;
		setTitle("Service Desk - " + u.getNome());
		setSize(850, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));
// Formulário
		JPanel form = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		form.add(new JLabel("Título:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		txtTitulo = new JTextField(20);
		form.add(txtTitulo, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		form.add(new JLabel("Solicitante:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		txtSolicitante = new JTextField(20);
		form.add(txtSolicitante, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		form.add(new JLabel("Status:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		cbStatus = new JComboBox<>(new String[] { "Aberto", "Em Andamento", "Resolvido" });
		form.add(cbStatus, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		form.add(new JLabel("Descrição:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		txtDescricao = new JTextArea(4, 20);
		form.add(new JScrollPane(txtDescricao), gbc);
// Botões
		JPanel botoes = new JPanel(new FlowLayout());
		JButton btnAbrir = new JButton("Abrir Chamado");
		JButton btnAtualizar = new JButton("Atualizar");
		JButton btnExcluir = new JButton("Excluir");
		JButton btnLimpar = new JButton("Limpar");
		botoes.add(btnAbrir);
		botoes.add(btnAtualizar);
		botoes.add(btnExcluir);
		botoes.add(btnLimpar);
// Tabela
		modeloTabela = new DefaultTableModel(new String[] { "ID", "Título", "Solicitante", "Status" }, 0) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tabela = new JTable(modeloTabela);
		JPanel topo = new JPanel(new BorderLayout());
		topo.add(form, BorderLayout.CENTER);
		topo.add(botoes, BorderLayout.SOUTH);
		add(topo, BorderLayout.NORTH);
		add(new JScrollPane(tabela), BorderLayout.CENTER);
// Eventos
		btnAbrir.addActionListener(e -> abrir());
		btnAtualizar.addActionListener(e -> atualizar());
		btnExcluir.addActionListener(e -> excluir());
		btnLimpar.addActionListener(e -> limpar());
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
				int r = tabela.getSelectedRow();
				idSelecionado = (int) modeloTabela.getValueAt(r, 0);
				txtTitulo.setText((String) modeloTabela.getValueAt(r, 1));
				txtSolicitante.setText((String) modeloTabela.getValueAt(r, 2));
				cbStatus.setSelectedItem(modeloTabela.getValueAt(r, 3));
			}
		});
		carregar();
	}

	private void carregar() {
		try {
			modeloTabela.setRowCount(0);
			List<Chamado> lista = dao.listar();
			for (Chamado c : lista) {
				modeloTabela.addRow(new Object[] { c.getId(), c.getTitulo(), c.getSolicitante(), c.getStatus() });
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
		}
	}

	private void abrir() {
		String titulo = txtTitulo.getText().trim();
		String solicitante = txtSolicitante.getText().trim();
		if (titulo.isEmpty() || solicitante.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Título e Solicitante são obrigatórios.");
			return;
		}
		try {
			Chamado c = new Chamado(titulo, txtDescricao.getText(), solicitante, (String) cbStatus.getSelectedItem(),
					usuarioLogado.getId());
			dao.inserir(c);
			limpar();
			carregar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
		}
	}

	private void atualizar() {
		if (idSelecionado == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um chamado na tabela.");
			return;
		}
		try {
			Chamado c = new Chamado(txtTitulo.getText().trim(), txtDescricao.getText(), txtSolicitante.getText().trim(),
					(String) cbStatus.getSelectedItem(), usuarioLogado.getId());
			c.setId(idSelecionado);
			dao.atualizar(c);
			limpar();
			carregar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
		}
	}

	private void excluir() {
		if (idSelecionado == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um chamado na tabela.");
			return;
		}
		int ok = JOptionPane.showConfirmDialog(this, "Excluir chamado ID " + idSelecionado + "?", "Confirmar",
				JOptionPane.YES_NO_OPTION);
		if (ok != JOptionPane.YES_OPTION)
			return;
		try {
			dao.excluir(idSelecionado);
			limpar();
			carregar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
		}
	}

	private void limpar() {
		txtTitulo.setText("");
		txtSolicitante.setText("");
		txtDescricao.setText("");
		cbStatus.setSelectedIndex(0);
		idSelecionado = -1;
		tabela.clearSelection();
	}
}