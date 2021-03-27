package br.com.totemti.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.totemti.livraria.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
