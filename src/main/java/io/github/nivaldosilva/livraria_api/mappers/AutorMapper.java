package io.github.nivaldosilva.livraria_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import io.github.nivaldosilva.livraria_api.dto.AutorDTO;
import io.github.nivaldosilva.livraria_api.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(target = "livros", ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Autor toEntity(AutorDTO autorDTO);

    AutorDTO toDTO(Autor autor);
}