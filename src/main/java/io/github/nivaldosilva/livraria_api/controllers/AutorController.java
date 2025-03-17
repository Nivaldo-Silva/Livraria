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
import io.github.nivaldosilva.livraria_api.dto.AutorDTO;
import io.github.nivaldosilva.livraria_api.model.Autor;
import io.github.nivaldosilva.livraria_api.service.AutorService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/autores")
public class AutorController {
    
    private final AutorService autorService;
    
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    
    @PostMapping
    public ResponseEntity<Void> criarAutor(@Valid @RequestBody AutorDTO autorDTO) {
        Autor autor = autorService.salvarAutor(autorDTO);
        return ResponseEntity.created(URI.create("/api/autores/" + autor.getId())).build();
    }
    
    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        List<AutorDTO> autores = autorService.listarAutores();
        return ResponseEntity.ok(autores);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterAutor(@PathVariable("id") UUID id) {
        AutorDTO autorDTO = autorService.obterAutor(id);
        return ResponseEntity.ok(autorDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> atualizarAutor(@PathVariable("id") UUID id, @Valid @RequestBody AutorDTO autorDTO) {
        AutorDTO autorAtualizado = autorService.atualizarAutor(id, autorDTO);
        return ResponseEntity.ok(autorAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") UUID id) {
        autorService.deletarAutor(id);
        return ResponseEntity.noContent().build();
    }
}