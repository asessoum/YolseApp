package fr.yolse.app.repository;

import fr.yolse.app.domain.SuiviChamps;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SuiviChamps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuiviChampsRepository extends JpaRepository<SuiviChamps, Long> {

}
