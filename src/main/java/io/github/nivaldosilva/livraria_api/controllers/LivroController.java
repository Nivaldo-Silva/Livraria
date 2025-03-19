package io.github.nivaldosilva.livraria_api.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.nivaldosilva.livraria_api.dto.LivroRequestDto;
import io.github.nivaldosilva.livraria_api.dto.LivroResponseDto;
import io.github.nivaldosilva.livraria_api.model.Livro;
import io.github.nivaldosilva.livraria_api.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> criarLivro(@RequestBody @Valid LivroRequestDto livroRequestDto) {
        Livro livro = livroService.salvarLivro(livroRequestDto);
        LivroResponseDto livroResponseDto = livroService.buscarLivroPorId(livro.getId());
        return ResponseEntity.created(URI.create("/api/livros/" + livro.getId())).body(livroResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> listarLivros() {
        List<LivroResponseDto> livros = livroService.listarTodosLivros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> obterLivro(@PathVariable("id") UUID id) {
        LivroResponseDto livroResponseDto = livroService.buscarLivroPorId(id);
        return ResponseEntity.ok(livroResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(@PathVariable("id") UUID id,
                                                           @Valid @RequestBody LivroRequestDto livroRequestDto) {
        LivroResponseDto livroResponseDto = livroService.atualizarLivro(id, livroRequestDto);
        return ResponseEntity.ok(livroResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable("id") UUID id) {
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}