package form;

import jakarta.validation.constraints.Pattern;
import modelo.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PessoaForm {

	@NotNull
	@NotEmpty
	private String nome;

	@Email
	private String email;

	@NotNull
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	private String dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Pessoa converter() {
		return new Pessoa(nome, email, dataNascimento);
	}

}
