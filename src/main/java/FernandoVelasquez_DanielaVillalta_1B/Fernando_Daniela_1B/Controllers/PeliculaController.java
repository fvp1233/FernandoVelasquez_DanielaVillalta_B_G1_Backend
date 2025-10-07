package FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Controllers;

import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Exceptions.ExceptionPeliculaDuplicada;
import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Exceptions.ExceptionPeliculaNoEncontrada;
import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Models.DTO.PeliculaDTO;
import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Services.PeliculaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/apiPeliculas")
public class PeliculaController {

    //Inyectamos el peliculasService sobre el controller
    @Autowired
    PeliculaService service;

    @GetMapping("/consultarPeliculas")
    public List<PeliculaDTO> obtenerDatos(){
        System.out.println("Entre al endpoint");
        return service.obtenerPeliculas();
    }

    @PostMapping("/registrarPeliculas")
    public ResponseEntity<?> nuevaPelicula(@Valid @RequestBody PeliculaDTO json, HttpServletRequest request){
        try {
            PeliculaDTO respuesta = service.InsertarPeliculas(json);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Insercion fallida",
                        "errorType", "VALIDATION_ERROR",
                        "message", "La pelicula no pudo ser insertada"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data", respuesta
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error no controlado al añadir una pelicula",
                    "detail", e.getMessage()
            ));

        }
    }

    @PutMapping("/editarPelicula/{id}")
    public ResponseEntity<?> modificarPelicula(@PathVariable Long id, @Valid @RequestBody PeliculaDTO json, BindingResult bindingResult){
        //1.Verificar si hay errores
        if (bindingResult.hasErrors()){
            //2.Creamos un mapa que almacena los erorres
            Map<String, String> errores = new HashMap<>();
            //3.Iterar cada error
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
        }
        try {
            PeliculaDTO dto = service.ActualizarPelicula(id, json);
            return ResponseEntity.ok(dto);

        } catch (ExceptionPeliculaNoEncontrada e){
            return ResponseEntity.notFound().build();
        } catch (ExceptionPeliculaDuplicada e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos duplicados",
                    "Campo", e.getCampoDulicado
            ));
        }

    }

    @DeleteMapping("/eliminarPelicula/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id){
        try {
            service.eliminarPelicula(id);
            return ResponseEntity.noContent().build();
        }catch (ExceptionPeliculaNoEncontrada e){
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/consultarPelicula/{id}")
    public ResponseEntity<?> obtenerPeliculasPorId(@PathVariable Long id){
        try {
            PeliculaDTO pelicula = service.obtenerPeliculaPorId(id);
            return ResponseEntity.ok(pelicula);
        }catch (ExceptionPeliculaNoEncontrada e){
            return ResponseEntity.notFound().build();
        }
    }
}
