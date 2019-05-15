package fr.yolse.app.service.mapper;

import fr.yolse.app.domain.*;
import fr.yolse.app.service.dto.TransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, UtilisateurMapper.class})
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "vendeurID.id", target = "vendeurIDId")
    TransactionDTO toDto(Transaction transaction);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "vendeurIDId", target = "vendeurID")
    @Mapping(target = "ventes", ignore = true)
    Transaction toEntity(TransactionDTO transactionDTO);

    default Transaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}
