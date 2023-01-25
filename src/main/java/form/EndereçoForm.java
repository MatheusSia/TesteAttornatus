package form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import modelo.Endereço;
import modelo.Pessoa;

public class EndereçoForm {

	@NotNull
	@NotEmpty
	private Pessoa pessoa;

	@NotNull
	@NotEmpty
	private Boolean endereçoPrincipal;

	@NotNull
	@NotEmpty
	private String logradouro;

	@NotNull
	@NotEmpty
	private Long cep;

	@NotNull
	@NotEmpty
	private Long numero;

	@NotNull
	@NotEmpty
	private String cidade1;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Boolean getEndereçoPrincipal() {
		return endereçoPrincipal;
	}
	
	public void setEndereçoPrincipal(Boolean endereçoPrincipal) {
		this.endereçoPrincipal = endereçoPrincipal;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public Long getCep() {
		return cep;
	}
	
	public void setCep(Long cep) {
		this.cep = cep;
	}
	
	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public String getCidade1() {
		return cidade1;
	}
	
	public void setCidade1(String cidade1) {
		this.cidade1 = cidade1;
	}
	
	public Endereço converter() {
		return new Endereço(pessoa, endereçoPrincipal, logradouro, cep, numero, cidade1);
	}

}
