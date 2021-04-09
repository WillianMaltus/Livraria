package br.com.totemti.livraria.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.totemti.livraria.controllers.dto.AutorDTO;
import br.com.totemti.livraria.controllers.dto.form.AutorFormDTO;
import br.com.totemti.livraria.models.Autor;
import br.com.totemti.livraria.services.AutorService;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {

	private ModelMapper modelMapper;
	private AutorService autorService;
	
	@Autowired
	public AutorController(AutorService autorService, ModelMapper modelMapper) {
		this.autorService = autorService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping
	public ResponseEntity<Page<AutorDTO>> index(@PageableDefault(sort = "nome", size = 6) Pageable pageable) {
		Page autores = autorService
				.listar(pageable)
				.map(autor -> modelMapper.map(autor, AutorDTO.class));
		
		return ResponseEntity.ok(autores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDTO> show(@PathVariable Long id){
		Autor autor = autorService.buscar(id);
		AutorDTO autorDTO = modelMapper.map(autor, AutorDTO.class);
		
		return ResponseEntity.ok(autorDTO);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorDTO> stored(@RequestBody @Valid AutorFormDTO autorForm){
		Autor autor = modelMapper.map(autorForm, Autor.class);
		AutorDTO novoAutor = modelMapper.map(autorService.salvar(autor), AutorDTO.class);
		URI uri = UriComponentsBuilder.fromPath("autor/{id}").buildAndExpand(novoAutor.getId()).toUri();
		return ResponseEntity.created(uri).body(novoAutor);
	}
}
