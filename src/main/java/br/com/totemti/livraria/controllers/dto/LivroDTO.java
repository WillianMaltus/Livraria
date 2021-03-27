package br.com.totemti.livraria.controllers.dto;

import java.util.List;

public class LivroDTO {

	private Long id;
	private String nome;
	private String descricao;
	private EditoraDTO editora;
	private List<AutorDTO> autores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EditoraDTO getEditora() {
		return editora;
	}

	public void setEditora(EditoraDTO editora) {
		this.editora = editora;
	}

	public List<AutorDTO> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorDTO> autores) {
		this.autores = autores;
	}
}
