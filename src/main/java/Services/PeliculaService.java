package Services;

import Entities.PeliculaEntity;
import Models.DTO.PeliculaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PeliculaService {

    @Autowired
    PeliculaEntity repo;

    private PeliculaDTO convertirADTO(PeliculaEntity entity){
        PeliculaDTO dto = new PeliculaDTO();
    }
}
