package controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import controllerDto.PessoaDto;
import form.AtualizaPessoaForm;
import form.PessoaForm;
import modelo.Pessoa;
import repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public Page<PessoaDto> listar(
			@PageableDefault(sort = "dataNascimento", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Pessoa> pessoa = pessoaRepository.findAll(paginacao);
		return PessoaDto.converter(pessoa);
	}

	@GetMapping("/{email}")
	public ResponseEntity<PessoaDto> buscaPessoaEmail(@PathVariable String email) {
		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(new PessoaDto(pessoa.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<PessoaDto> cadastro(@Valid @RequestBody PessoaForm form, UriComponentsBuilder uriBuilder) {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		for (Pessoa pessoa : pessoas) {
			if (form.getEmail().equals(pessoa.getEmail())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		Pessoa pessoa = form.converter();
		pessoaRepository.save(pessoa);

		URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaPessoaForm form) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if (optional.isPresent()) {
			Pessoa pessoa = form.atualizar(id, pessoaRepository);
			return ResponseEntity.ok(new PessoaDto(pessoa));
		}
		return ResponseEntity.notFound().build();

	}

}
