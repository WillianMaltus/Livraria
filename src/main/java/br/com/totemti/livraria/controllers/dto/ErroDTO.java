package br.com.totemti.livraria.controllers.dto;

public class ErroDTO {
	
	private String erro;
	private String propriedade;
	
	public ErroDTO(String erro, String propriedade) {
		super();
		this.erro = erro;
		this.propriedade = propriedade;
	}
	
	public String getPropriedade() {
		return propriedade;
	}

	public ErroDTO(String erro) {
		this.erro = erro; 
	}

	public String getErro() {
		return erro;
	}
}
