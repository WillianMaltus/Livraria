package br.com.totemti.livraria.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.totemti.livraria.controllers.dto.AutorDTO;
import br.com.totemti.livraria.controllers.dto.LivroDTO;
import br.com.totemti.livraria.controllers.dto.form.LivroFormDTO;
import br.com.totemti.livraria.models.Editora;
import br.com.totemti.livraria.models.Livro;
import br.com.totemti.livraria.services.AutorService;
import br.com.totemti.livraria.services.EditoraService;
import br.com.totemti.livraria.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

	private ModelMapper modelMapper;
	private LivroService livroService;
	private AutorService autorService;
	private EditoraService editoraService;
	
	@Autowired
	public LivroController(ModelMapper modelMapper, LivroService livroService, AutorService autorService, EditoraService editoraService) {
		super();
		this.modelMapper = modelMapper;
		this.livroService = livroService;
		this.autorService = autorService;
		this.editoraService = editoraService;
	}
	
	@Cacheable(value = "livros")
	@GetMapping
	public ResponseEntity<Page<LivroDTO>> index(@PageableDefault(sort = "nome", size = 2) Pageable pageable) {
		Page livros = livroService
				.listar(pageable)
				.map(livro -> modelMapper.map(livro, LivroDTO.class));
		
		return ResponseEntity.ok(livros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> show(@PathVariable Long id){
		Livro livro = livroService.buscar(id);
		LivroDTO livroDTO = modelMapper.map(livro, LivroDTO.class);
		
		return ResponseEntity.ok(livroDTO);
	}
	
	@CacheEvict(value = "livros", allEntries = true)
	@Transactional
	@PostMapping
	public ResponseEntity<LivroDTO> stored(@RequestBody @Valid LivroFormDTO livroFormDTO, UriComponentsBuilder uriComponentsBuilder){
		Livro livro = modelMapper.map(livroFormDTO, Livro.class);
		atribuirEditora(livro, livroFormDTO);
		atribuirAutores(livro, livroFormDTO);
		
		LivroDTO novoLivro = modelMapper.map(livroService.salvar(livro), LivroDTO.class);
		
		URI uri = uriComponentsBuilder.path("/livros/{id}").buildAndExpand(novoLivro.getId()).toUri();
		
		return ResponseEntity.created(uri).body(novoLivro);
	}
	
	@CacheEvict(value = "livros", allEntries = true)
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody @Valid LivroFormDTO livroFormDTO){
		Livro livro = modelMapper.map(livroFormDTO, Livro.class);
		livro.setId(id);
		atribuirEditora(livro, livroFormDTO);
		atribuirAutores(livro, livroFormDTO);
		
		LivroDTO novoLivro = modelMapper.map(livroService.salvar(livro), LivroDTO.class);
		
		return ResponseEntity.ok(novoLivro);
	}
	
	@CacheEvict(value = "livros", allEntries = true)
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		livroService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	

	private void atribuirEditora(Livro livro, LivroFormDTO livroFormDTO) {
		Editora editora = editoraService.buscar(livroFormDTO.getEditora());
		livro.setEditora(editora);
	}

	private void atribuirAutores(Livro livro, LivroFormDTO livroFormDTO) {
		if(livroFormDTO.getAutores() != null && !livroFormDTO.getAutores().isEmpty()) {
			for(Long autor : livroFormDTO.getAutores()) {
				livro.incluirAutor(autorService.buscar(autor));
			}
		}
		
	}
}
