package io.github.nivaldosilva.livraria_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import io.github.nivaldosilva.livraria_api.model.Autor;
import io.github.nivaldosilva.livraria_api.model.enums.Genero;

public record LivroResponseDto(

        String isbn,
        String titulo,
        String sinopse,
        Genero genero,
        LocalDate dataPublicacao,
        BigDecimal preco,
        Autor autorId,
        AutorDTO autorDTO


) {}
