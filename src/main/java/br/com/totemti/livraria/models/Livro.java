package br.com.totemti.livraria.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro extends Entidade{
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_editora", foreignKey = @ForeignKey(name = "fk_livro_editora"))
	private Editora editora;
	
	@ManyToMany
	@JoinTable(name = "autor_livro",
				joinColumns = @JoinColumn(name = "livro_id", 
					foreignKey = @ForeignKey(name = "fk_autor_livro_livro")), 
				inverseJoinColumns = @JoinColumn(name = "autor_id", 
					foreignKey = @ForeignKey(name = "fk_autor_livro_autor")))
	private Set<Autor> autores;

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

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Set<Autor> getAutores() {
		if(autores == null) {
			Collections.emptySet();
		}
		return Collections.unmodifiableSet(autores);
	}

	public void incluirAutor(Autor autor) {
		if(autores == null) {
			autores = new HashSet<>();
		}
		autores.add(autor);
	}
}
