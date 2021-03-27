package br.com.totemti.livraria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.totemti.livraria.exceptions.RegistroNaoEncontradoException;
import br.com.totemti.livraria.models.Editora;
import br.com.totemti.livraria.repositories.EditoraRepository;

@Service
public class EditoraService {
	
	private EditoraRepository editoraRepository;
	
	@Autowired
	public EditoraService(EditoraRepository editoraRepository) {
		this.editoraRepository = editoraRepository;
	}
	
	public Page<Editora> listar(Pageable pageable){
		return editoraRepository.findAll(pageable);
	}

	public Editora buscar(Long id) {
		return editoraRepository
				.findById(id)
				.orElseThrow(() -> new RegistroNaoEncontradoException("editora.naoEncontrada"));
	}
	
}
