package br.com.totemti.livraria.controllers.dto.form;

import javax.validation.constraints.NotNull;

public class AutorFormDTO {
	
	@NotNull
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
