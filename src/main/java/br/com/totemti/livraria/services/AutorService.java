package br.com.totemti.livraria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.totemti.livraria.exceptions.RegistroNaoEncontradoException;
import br.com.totemti.livraria.models.Autor;
import br.com.totemti.livraria.repositories.AutorRepository;

@Service
public class AutorService {
	
	private AutorRepository autorRepository;
	
	@Autowired
	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
	
	public Page<Autor> listar(Pageable pageable){
		return autorRepository.findAll(pageable);
	}

	public Autor buscar(Long id) {
		return autorRepository
				.findById(id)
				.orElseThrow(() -> new RegistroNaoEncontradoException("autor.naoEncontrado"));
	}
	
}
