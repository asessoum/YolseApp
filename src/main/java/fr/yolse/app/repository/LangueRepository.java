package fr.yolse.app.repository;

import fr.yolse.app.domain.Langue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Langue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LangueRepository extends JpaRepository<Langue, Long> {

}
