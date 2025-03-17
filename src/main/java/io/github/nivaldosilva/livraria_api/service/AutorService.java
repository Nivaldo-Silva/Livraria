package io.github.nivaldosilva.livraria_api.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
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

        Autor novosDados = autorMapper.toEntity(autorDTO);

        UUID autorId = autor.getId();
        var dataCriacao = autor.getDataCriacao();
        var usuarioId = autor.getUsuarioId();
        var livros = autor.getLivros();
        
        BeanUtils.copyProperties(novosDados, autor, "id", "dataCriacao", "usuarioId", "livros");

        autor.setId(autorId);
        autor.setDataCriacao(dataCriacao);
        autor.setUsuarioId(usuarioId);
        autor.setLivros(livros);

        Autor autorAtualizado = autorRepository.save(autor);
        return autorMapper.toDTO(autorAtualizado);
    }

    @Transactional
    public void deletarAutor(UUID id) {
        autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException(id));
        autorRepository.deleteById(id);
    }
}