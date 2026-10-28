package br.com.service_desk.model;

public class Chamado {
	private int id;
	private String titulo;
	private String descricao;
	private String solicitante;
	private String status;
	private int usuarioId;

	public Chamado() {
	}

	public Chamado(String titulo, String descricao, String solicitante, String status, int usuarioId) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.solicitante = solicitante;
		this.status = status;
		this.usuarioId = usuarioId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
}