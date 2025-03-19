package io.github.nivaldosilva.livraria_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.validator.constraints.ISBN;
import io.github.nivaldosilva.livraria_api.model.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record LivroRequestDto(

        @ISBN(message = "O ISBN do livro é obrigatório") 
        @NotBlank(message = "O campo ISBN não pode estar vazio")
        String isbn,

        @NotBlank(message = "O campo Título é obrigatório")
        String titulo,

        @NotBlank (message = "O campo Sinopse é obrigatório")
        String sinopse,

        @NotNull (message = "O campo Gênero é obrigatório")
        Genero genero,

        @NotNull (message = "A data de publicação é obrigatória")
        @Past(message = "A data de publicação deve ser no passado") 
        LocalDate dataPublicacao,

        @NotNull(message = "O campo Preço é obrigatório") 
        BigDecimal preco,

        @NotNull 
        UUID autorId

) {}
