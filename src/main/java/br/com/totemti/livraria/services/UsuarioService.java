package br.com.totemti.livraria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.totemti.livraria.exceptions.RegistroNaoEncontradoException;
import br.com.totemti.livraria.models.Usuario;
import br.com.totemti.livraria.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario buscar(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("usuario.naoEncontrado"));
	}
	
	public Usuario buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> new RegistroNaoEncontradoException("usuario.naoEncontrado"));
	}
}
