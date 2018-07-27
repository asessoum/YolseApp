package fr.yolse.app.repository;

import fr.yolse.app.domain.BesoinEngrais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BesoinEngrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BesoinEngraisRepository extends JpaRepository<BesoinEngrais, Long> {

}
