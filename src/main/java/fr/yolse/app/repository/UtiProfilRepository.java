package fr.yolse.app.repository;

import fr.yolse.app.domain.UtiProfil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UtiProfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtiProfilRepository extends JpaRepository<UtiProfil, Long> {

}
