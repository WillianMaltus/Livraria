package br.com.totemti.livraria.controllers.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

	@NotNull
	private String email;
	
	@NotNull
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
