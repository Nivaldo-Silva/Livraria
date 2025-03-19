package io.github.nivaldosilva.livraria_api.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nivaldosilva.livraria_api.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, UUID> {

    Optional<Livro> findByIsbn(String isbn);

    

    


}
