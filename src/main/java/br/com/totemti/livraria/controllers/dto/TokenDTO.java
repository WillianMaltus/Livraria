package br.com.totemti.livraria.controllers.dto;

public class TokenDTO {

	private String tipo;
	
	private String token;

	public TokenDTO(String tipo, String token) {
		super();
		this.tipo = tipo;
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
