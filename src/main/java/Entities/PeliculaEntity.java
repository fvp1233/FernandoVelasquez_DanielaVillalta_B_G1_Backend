package Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "PELICULAS")
@Getter @Setter @EqualsAndHashCode @ToString
public class PeliculaEntity {
    //Atributos de la tabla peliculas

    @Id
    @Column(name = "ID_PELICULA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_peliculas")
    @SequenceGenerator(name = "seq_peliculas", sequenceName = "seq_peliculas", allocationSize = 1)
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ANO_ESTRENO")
    private Integer anioEstreno;

    @Column(name = "DURACION_MIN")
    private Integer duracion;

    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;
}
