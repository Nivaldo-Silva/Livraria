package io.github.nivaldosilva.livraria_api.exceptions;

public class AutorJaExisteException extends RuntimeException {
    public AutorJaExisteException(String nome) {
        super("Autor com o nome " + nome + " já existe.");
    }

}
