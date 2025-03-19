package io.github.nivaldosilva.livraria_api.exceptions;

public class LivroJaExisteException extends RuntimeException {
    public LivroJaExisteException(String isbn) {
        super("Livro com ISBN " + isbn + " já está cadastrado");
    }


}
