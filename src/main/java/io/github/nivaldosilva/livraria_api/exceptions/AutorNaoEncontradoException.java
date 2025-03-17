package io.github.nivaldosilva.livraria_api.exceptions;

import java.util.UUID;

public class AutorNaoEncontradoException extends RuntimeException {
    public AutorNaoEncontradoException(UUID id) {
        super("Autor com o ID " + id + " não encontrado.");
    }

}
