package io.github.nivaldosilva.livraria_api.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Entity(name = "Autor")
@Table(name = "autores")
@Data
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank
    private String nome;

    @Column(name = "nacionalidade", nullable = false, length = 100)
    @NotBlank
    private String nacionalidade;

    @Column(name = "data_nascimento", nullable = false)
    @NotBlank
    @Past
    private String dataNascimento;

    @Column(name = "biografia", nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String biografia;

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Livro> livros;

    @CreationTimestamp
    @Column(name = "data_criacao")
    @NotNull
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @NotNull
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    


}
