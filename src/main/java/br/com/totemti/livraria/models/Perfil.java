package br.com.totemti.livraria.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfis")
public class Perfil extends Entidade implements GrantedAuthority{

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return nome;
	}

}
