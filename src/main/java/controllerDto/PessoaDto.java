package controllerDto;

import org.springframework.data.domain.Page;
import modelo.Pessoa;

public class PessoaDto {

	private String nome;
	private String email;
	private String dataNascimento;

	public PessoaDto(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.email = pessoa.getEmail();
		this.dataNascimento = pessoa.getNome();

	}

	public static Page<PessoaDto> converter(Page<Pessoa> pessoa) {
		return pessoa.map(PessoaDto::new);
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

}
