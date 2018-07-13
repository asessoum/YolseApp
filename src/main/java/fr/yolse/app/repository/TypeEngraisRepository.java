package fr.yolse.app.repository;

import fr.yolse.app.domain.TypeEngrais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeEngrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeEngraisRepository extends JpaRepository<TypeEngrais, Long> {

}
