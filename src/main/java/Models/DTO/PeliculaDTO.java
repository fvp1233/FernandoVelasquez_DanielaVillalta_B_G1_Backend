package Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Date;

@Getter @Setter @EqualsAndHashCode @ToString
public class PeliculaDTO {

    //Campos que se guardaran de la tabla Peliculas
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String director;

    @NotBlank
    private String genero;

    @NotNull
    private Integer anioEstreno;

    @Positive(message = "La duracion de la pelicula debe de ser positiva")
    @NotNull
    private Integer duracion;

    @NotBlank
    private Date fecha_creacion;
}
