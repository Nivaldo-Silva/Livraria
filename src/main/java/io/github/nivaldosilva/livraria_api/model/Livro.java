package io.github.nivaldosilva.livraria_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.ISBN;
import io.github.nivaldosilva.livraria_api.model.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Livro")
@Table(name = "livros")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "isbn",nullable = false, unique = true)
    @ISBN
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 100)
    @NotBlank
    private String titulo;

    @Column(name = "sinopse", nullable = false, columnDefinition = "TEXT")
    private String sinopse;

    @Column(name = "genero", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Genero genero;

    @Column(name = "data_publicacao", nullable = false)
    @NotNull
    @Past
    private LocalDate dataPublicacao;

    @Column(name = "preco")
    @NotNull
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor; 

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
    


