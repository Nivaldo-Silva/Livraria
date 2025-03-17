package io.github.nivaldosilva.livraria_api.dto;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record AutorDTO(
    UUID id,
    
    @NotBlank(message = "O nome do autor é obrigatório")
    String nome,
    
    @NotBlank(message = "A nacionalidade do autor é obrigatória")
    String nacionalidade,
    
    @NotNull(message = "A data de nascimento do autor é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    LocalDate dataNascimento,
    
    @NotBlank(message = "A biografia do autor é obrigatória")
    String biografia
) {}



