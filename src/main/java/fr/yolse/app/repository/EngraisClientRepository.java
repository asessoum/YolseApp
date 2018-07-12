package fr.yolse.app.repository;

import fr.yolse.app.domain.EngraisClient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EngraisClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngraisClientRepository extends JpaRepository<EngraisClient, Long> {

}
