package form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import modelo.Endereço;
import repository.EndereçoRepository;

public class AtualizaEndereçoForm {

	@NotEmpty
	@NotNull
	private Boolean endereçoPrincipal;

	@NotEmpty
	@NotNull
	private String logradouro;

	@NotEmpty
	@NotNull
	private Long cep;

	@NotEmpty
	@NotNull
	private Long numero;

	@NotEmpty
	@NotNull
	private String cidade1;

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

	public Endereço atualizar(Long id, EndereçoRepository endereçoRepository) {
		@SuppressWarnings("deprecation")
		Endereço endereço = endereçoRepository.getOne(id);
		endereço.setEndereçoPrincipal(this.endereçoPrincipal);
		endereço.setLogradouro(this.logradouro);
		endereço.setCep(this.cep);
		endereço.setNumero(this.numero);
		endereço.setCidade1(this.cidade1);
		return endereço;
	}

}
