package FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Repositories;

import FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Entities.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository  extends JpaRepository<PeliculaEntity, Long> {
}
