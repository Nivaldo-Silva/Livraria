package io.github.nivaldosilva.livraria_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import io.github.nivaldosilva.livraria_api.dto.AutorDTO;
import io.github.nivaldosilva.livraria_api.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    
    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);
    
   
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "nacionalidade", source = "nacionalidade")
    @Mapping(target = "dataNascimento", source = "dataNascimento")
    @Mapping(target = "biografia", source = "biografia")
    @Mapping(target = "livros", ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Autor toEntity(AutorDTO autorDTO);
    
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "nacionalidade", source = "nacionalidade")
    @Mapping(target = "dataNascimento", source = "dataNascimento")
    @Mapping(target = "biografia", source = "biografia")
    AutorDTO toDTO(Autor autor);
}