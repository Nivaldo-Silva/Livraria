package io.github.nivaldosilva.livraria_api.exceptions;

import java.util.UUID;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(UUID id) {
        super("Livro com ID " + id + " não encontrado");
    }


}
