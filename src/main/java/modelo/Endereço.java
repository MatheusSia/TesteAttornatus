package modelo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Endereço {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "pessoa_id")
	private Pessoa pessoa;

	private Boolean endereçoPrincipal;

	private String logradouro;

	private Long cep;

	private Long numero;

	private String cidade1;

	public Endereço(Pessoa pessoa, Boolean endereçoPrincipal, String logradouro, Long cep, Long numero,
			String cidade1) {
		this.pessoa = pessoa;
		this.endereçoPrincipal = endereçoPrincipal;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade1 = cidade1;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
