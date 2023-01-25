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
import controllerDto.EndereçoDto;
import form.AtualizaEndereçoForm;
import form.EndereçoForm;
import modelo.Endereço;
import modelo.Pessoa;
import repository.EndereçoRepository;

@RestController
@RequestMapping("/endereços")
public class EndereçoController {

	@Autowired
	private EndereçoRepository endereçoRepository;

	@GetMapping
	public Page<EndereçoDto> listar(
			@PageableDefault(sort = "logradouro", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Endereço> endereços = endereçoRepository.findAll(paginacao);
		return EndereçoDto.converter(endereços);

	}

	@GetMapping("/{pessoa}")
	public ResponseEntity<EndereçoDto> buscaPessoaEmail(@PathVariable Pessoa pessoa) {
		Optional<Endereço> pessoa1 = endereçoRepository.findByPessoa(pessoa);
		if (pessoa1.isPresent()) {
			return ResponseEntity.ok(new EndereçoDto(pessoa1.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/endereçoPrincipal")
	public List<Endereço> endereçoPrincipal() {
		return endereçoRepository.endereçoPrincipal();
	}

	@PostMapping
	public ResponseEntity<EndereçoDto> cadastro(@Valid @RequestBody EndereçoForm form,
			UriComponentsBuilder uriBuilder) {
		try {
			Endereço endereço = form.converter();
			endereçoRepository.save(endereço);

			URI uri = uriBuilder.path("/vacinacoes/{id}").buildAndExpand(endereço.getId()).toUri();
			return ResponseEntity.created(uri).body(new EndereçoDto(endereço));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Endereço> endereço = endereçoRepository.findById(id);
		if (endereço.isPresent()) {
			endereçoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EndereçoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaEndereçoForm form) {
		Optional<Endereço> optional = endereçoRepository.findById(id);
		if (optional.isPresent()) {
			Endereço endereço = form.atualizar(id, endereçoRepository);
			return ResponseEntity.ok(new EndereçoDto(endereço));
		}
		return ResponseEntity.notFound().build();

	}

}
