package controllerDto;

import org.springframework.data.domain.Page;
import modelo.Endereço;

public class EndereçoDto {

	private Long id;

	private Boolean endereçoPrincipal;

	private String logradouro;

	private Long cep;

	private Long numero;

	private String cidade1;

	public EndereçoDto(Endereço endereço) {
		this.id = endereço.getId();
		this.endereçoPrincipal = endereço.getEndereçoPrincipal();
		this.logradouro = endereço.getLogradouro();
		this.cep = endereço.getCep();
		this.numero = endereço.getNumero();
		this.cidade1 = endereço.getCidade1();
	}

	public Long getId() {
		return id;
	}

	public Boolean getEndereçoPrincipal() {
		return endereçoPrincipal;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Long getCep() {
		return cep;
	}

	public Long getNumero() {
		return numero;
	}

	public String getCidade1() {
		return cidade1;
	}

	public static Page<EndereçoDto> converter(Page<Endereço> endereço) {
		return endereço.map(EndereçoDto::new);
	}

}
