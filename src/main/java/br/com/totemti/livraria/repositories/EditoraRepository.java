package br.com.totemti.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.totemti.livraria.models.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

}
