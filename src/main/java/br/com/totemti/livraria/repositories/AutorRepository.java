package br.com.totemti.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.totemti.livraria.models.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
