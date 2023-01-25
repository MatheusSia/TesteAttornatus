package form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import modelo.Pessoa;
import repository.PessoaRepository;

public class AtualizaPessoaForm {

	@NotEmpty
	@NotNull
	private String nome;

	@NotEmpty
	@NotNull
	private String dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Pessoa atualizar(Long id, PessoaRepository pessoaRepository) {
		@SuppressWarnings("deprecation")
		Pessoa pessoa = pessoaRepository.getOne(id);
		pessoa.setNome(this.nome);
		pessoa.setDataNascimento(this.dataNascimento);
		return pessoa;
	}
}
