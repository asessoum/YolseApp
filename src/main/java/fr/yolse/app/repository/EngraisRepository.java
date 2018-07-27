package fr.yolse.app.repository;

import fr.yolse.app.domain.Engrais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Engrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngraisRepository extends JpaRepository<Engrais, Long> {

}
