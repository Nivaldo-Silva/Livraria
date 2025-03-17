package io.github.nivaldosilva.livraria_api.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nivaldosilva.livraria_api.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    Optional<Autor> findByNome(String nome);

    

}
