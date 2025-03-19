package io.github.nivaldosilva.livraria_api.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.github.nivaldosilva.livraria_api.dto.LivroRequestDto;
import io.github.nivaldosilva.livraria_api.dto.LivroResponseDto;
import io.github.nivaldosilva.livraria_api.exceptions.AutorNaoEncontradoException;
import io.github.nivaldosilva.livraria_api.exceptions.LivroJaExisteException;
import io.github.nivaldosilva.livraria_api.exceptions.LivroNaoEncontradoException;
import io.github.nivaldosilva.livraria_api.mappers.LivroMapper;
import io.github.nivaldosilva.livraria_api.model.Autor;
import io.github.nivaldosilva.livraria_api.model.Livro;
import io.github.nivaldosilva.livraria_api.repositories.AutorRepository;
import io.github.nivaldosilva.livraria_api.repositories.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public Livro salvarLivro(LivroRequestDto livroRequestDto) {
        livroRepository.findByIsbn(livroRequestDto.isbn()).ifPresent(livroExistente -> {
            throw new LivroJaExisteException(livroRequestDto.isbn());
        });
        Livro livro = livroMapper.toEntity(livroRequestDto);

        Autor autor = autorRepository.findById(livroRequestDto.autorId())
                .orElseThrow(() -> new AutorNaoEncontradoException(livroRequestDto.autorId()));

        livro.setAutor(autor);

        return livroRepository.save(livro);
    }

    public Page<LivroResponseDto> listarTodosLivros(Pageable pageable) {
        return livroRepository.findAll(pageable)
                .map(livroMapper::toDTO);
    }

    public LivroResponseDto buscarLivroPorId(UUID id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        return livroMapper.toDTO(livro);
    }

    public LivroResponseDto atualizarLivro(UUID id, LivroRequestDto livroRequestDto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        livroMapper.updateLivroFromDto(livroRequestDto, livroExistente);

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return livroMapper.toDTO(livroAtualizado);
    }

    public void deletarLivro(UUID id) {
        livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        livroRepository.deleteById(id);
    }
}