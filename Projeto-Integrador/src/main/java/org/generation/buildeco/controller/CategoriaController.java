package org.generation.buildeco.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.buildeco.model.CategoriaModel;
import org.generation.buildeco.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping
	public ResponseEntity<List<CategoriaModel>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> GetById(@PathVariable Long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<CategoriaModel>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<CategoriaModel> post(@Valid @RequestBody CategoriaModel categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}

	@PutMapping
	public ResponseEntity<CategoriaModel> put(@Valid @RequestBody CategoriaModel categoria) {
		return repository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<CategoriaModel> categoria = repository.findById(id);
		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		repository.deleteById(id);
	}
}
