package fr.yolse.app.repository;

import fr.yolse.app.domain.TypeCulture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeCulture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeCultureRepository extends JpaRepository<TypeCulture, Long> {

}
