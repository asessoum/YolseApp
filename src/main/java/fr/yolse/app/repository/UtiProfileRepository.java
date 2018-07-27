package fr.yolse.app.repository;

import fr.yolse.app.domain.UtiProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UtiProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtiProfileRepository extends JpaRepository<UtiProfile, Long> {

}
