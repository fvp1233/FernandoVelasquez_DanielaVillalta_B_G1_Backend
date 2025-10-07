package FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Services;

import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Entities.PeliculaEntity;
import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Exceptions.ExceptionPeliculaNoEncontrada;
import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Models.DTO.PeliculaDTO;
import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Repositories.PeliculaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository repo;

    private PeliculaDTO convertirADTO(PeliculaEntity entity){
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDirector(entity.getDirector());
        dto.setGenero(entity.getGenero());
        dto.setAnioEstreno(entity.getAnioEstreno());
        dto.setDuracion(entity.getDuracion());
        dto.setFecha_creacion(entity.getFecha_creacion());
        return dto;
    }

    private PeliculaEntity convertirAEntity(@Valid PeliculaDTO json){
        PeliculaEntity entity = new PeliculaEntity();
        entity.setTitulo(json.getTitulo());
        entity.setDirector(json.getDirector());
        entity.setGenero(json.getGenero());
        entity.setAnioEstreno(json.getAnioEstreno());
        entity.setDuracion(json.getDuracion());
        entity.setFecha_creacion(json.getFecha_creacion());
        return entity;
    }

    public List<PeliculaDTO> obtenerPeliculas() {
        List<PeliculaEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PeliculaDTO InsertarPeliculas(@Valid PeliculaDTO json) {
        //Validacion para ver si los datos estan bien
        if (json == null){
            throw new IllegalArgumentException("Los datos no pueden ser nulos");
        }
        try {
            //Convertimos los datos de tipo DTO a Entity
            PeliculaEntity entity = convertirAEntity(json);
            PeliculaEntity respuesta = repo.save(entity);
            return convertirADTO(respuesta);
        }catch (Exception e){
            log.error("Error al registrar una pelicula nueva: " + e.getMessage());
            throw new ExceptionPeliculaNoEncontrada("error al registrar");
        }
    }

    public PeliculaDTO ActualizarPelicula(Long id, @Valid PeliculaDTO json) {
        PeliculaEntity peliculaExiste = repo.findById(id).orElseThrow(()-> new ExceptionPeliculaNoEncontrada("pelicula no encontrada"));
        peliculaExiste.setTitulo(json.getTitulo());
        peliculaExiste.setDirector(json.getDirector());
        peliculaExiste.setGenero(json.getGenero());
        peliculaExiste.setAnioEstreno(json.getAnioEstreno());
        peliculaExiste.setDuracion(json.getDuracion());
        peliculaExiste.setFecha_creacion(json.getFecha_creacion());
        PeliculaEntity peliculaActualizada = repo.save(peliculaExiste);
        return convertirADTO(peliculaActualizada);
    }

    public void eliminarPelicula(Long id) {
        if (!repo.existsById(id)){
            log.warn("La pelicula que deseas eliminar no existe");
            throw new ExceptionPeliculaNoEncontrada("Pelicula con el id: " + id + " no encontrado");
        }
        repo.deleteById(id);
        log.info("Pelicula eliminada correctamente");
    }

    public PeliculaDTO obtenerPeliculaPorId(Long id) {
        PeliculaEntity peliculaEncontrada = repo.findById(id).orElseThrow(()-> new ExceptionPeliculaNoEncontrada("Pelicula con el id: " + id + "no encontrado"));
        return convertirADTO(peliculaEncontrada);
    }
}
