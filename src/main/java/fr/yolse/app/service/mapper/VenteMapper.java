package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.VenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vente} and its DTO {@link VenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {TransactionMapper.class, ArticleMapper.class})
public interface VenteMapper extends EntityMapper<VenteDTO, Vente> {

    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "article.id", target = "articleId")
    VenteDTO toDto(Vente vente);

    @Mapping(source = "transactionId", target = "transaction")
    @Mapping(source = "articleId", target = "article")
    Vente toEntity(VenteDTO venteDTO);

    default Vente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vente vente = new Vente();
        vente.setId(id);
        return vente;
    }
}
