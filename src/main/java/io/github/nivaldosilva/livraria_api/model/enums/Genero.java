package io.github.nivaldosilva.livraria_api.model.enums;

import lombok.Getter;

@Getter
public enum Genero {

        AVENTURA("Aventura"),
        DRAMA("Drama"),
        COMEDIA("Comedia"),
        FICCAO("Ficção"),
        NAO_FICCAO("Não Ficção"),
        MISTERIO("Mistério"),
        FANTASIA("Fantasia"),
        FICCAO_CIENTIFICA("Ficção Científica"),
        BIOGRAFIA("Biografia"),
        HISTORIA("História"),
        ROMANCE("Romance"),
        TERROR("Terror"),
        AUTOAJUDA("Autoajuda");
    
        private final String descricao;
    
        Genero(String descricao) {
            this.descricao = descricao;
        }
    
       
    }
    


