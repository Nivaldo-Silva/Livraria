package io.github.nivaldosilva.livraria_api.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.nivaldosilva.livraria_api.dto.AutorDTO;
import io.github.nivaldosilva.livraria_api.exceptions.AutorJaExisteException;
import io.github.nivaldosilva.livraria_api.exceptions.AutorNaoEncontradoException;
import io.github.nivaldosilva.livraria_api.mappers.AutorMapper;
import io.github.nivaldosilva.livraria_api.model.Autor;
import io.github.nivaldosilva.livraria_api.repositories.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    public AutorService(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }

    @Transactional
    public Autor salvarAutor(AutorDTO autorDTO) {
        autorRepository.findByNome(autorDTO.nome())
                .ifPresent(autorExistente -> {
                    throw new AutorJaExisteException(autorDTO.nome());
                });

        Autor autor = autorMapper.toEntity(autorDTO);
        return autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<AutorDTO> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AutorDTO obterAutor(UUID id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException(id));
        return autorMapper.toDTO(autor);
    }

    @Transactional
    public AutorDTO atualizarAutor(UUID id, AutorDTO autorDTO) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException(id));

        autor.setNome(autorDTO.nome());
        autor.setNacionalidade(autorDTO.nacionalidade());
        autor.setDataNascimento(autorDTO.dataNascimento());
        autor.setBiografia(autorDTO.biografia());

        return autorMapper.toDTO(autorRepository.save(autor));
    }

    @Transactional
    public void deletarAutor(UUID id) {
        autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException(id));
        autorRepository.deleteById(id);
    }
}