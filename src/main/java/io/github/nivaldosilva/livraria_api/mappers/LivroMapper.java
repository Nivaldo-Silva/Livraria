package io.github.nivaldosilva.livraria_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import io.github.nivaldosilva.livraria_api.dto.LivroRequestDto;
import io.github.nivaldosilva.livraria_api.dto.LivroResponseDto;
import io.github.nivaldosilva.livraria_api.model.Livro;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public interface LivroMapper {

    @Mapping(target = "autor", ignore = true)
    Livro toEntity(LivroRequestDto livroRequestDto);

    @Mapping(source = "autor", target = "autorDTO")
    LivroResponseDto toDTO(Livro livro);

    @Mapping(target = "autor", ignore = true)
    Livro updateLivroFromDto(LivroRequestDto livroRequestDto, @MappingTarget Livro livro);
}

