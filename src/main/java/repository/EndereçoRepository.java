package repository;

import modelo.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import modelo.Endereço;

public interface EndereçoRepository extends JpaRepository<Endereço, Long> {

	Optional<Endereço> findByPessoa(Pessoa pessoa);

	@Query("select p from Pessoa p left join Endereço e on (p.id = e.pessoa) where e.endereçoPrincipal = true")
	List<Endereço> endereçoPrincipal();

}