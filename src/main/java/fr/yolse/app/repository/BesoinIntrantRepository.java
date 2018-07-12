package fr.yolse.app.repository;

import fr.yolse.app.domain.BesoinIntrant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BesoinIntrant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BesoinIntrantRepository extends JpaRepository<BesoinIntrant, Long> {

}
