package fr.yolse.app.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.yolse.app.domain.Transaction} entity.
 */
public class TransactionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer transactionID;

    @NotNull
    private Integer localID;

    @NotNull
    private Integer remoteID;

    @NotNull
    private Integer venteID;

    @NotNull
    private Integer vendeurID;

    @NotNull
    private Double quantiteTotal;

    @NotNull
    private Double prixTotalTransaction;

    private Boolean validSup;

    private Boolean validRes;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;


    private Long clientId;

    private Long vendeurIDId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getLocalID() {
        return localID;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
    }

    public Integer getVenteID() {
        return venteID;
    }

    public void setVenteID(Integer venteID) {
        this.venteID = venteID;
    }

    public Integer getVendeurID() {
        return vendeurID;
    }

    public void setVendeurID(Integer vendeurID) {
        this.vendeurID = vendeurID;
    }

    public Double getQuantiteTotal() {
        return quantiteTotal;
    }

    public void setQuantiteTotal(Double quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }

    public Double getPrixTotalTransaction() {
        return prixTotalTransaction;
    }

    public void setPrixTotalTransaction(Double prixTotalTransaction) {
        this.prixTotalTransaction = prixTotalTransaction;
    }

    public Boolean isValidSup() {
        return validSup;
    }

    public void setValidSup(Boolean validSup) {
        this.validSup = validSup;
    }

    public Boolean isValidRes() {
        return validRes;
    }

    public void setValidRes(Boolean validRes) {
        this.validRes = validRes;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getVendeurIDId() {
        return vendeurIDId;
    }

    public void setVendeurIDId(Long utilisateurId) {
        this.vendeurIDId = utilisateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;
        if (transactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + getId() +
            ", transactionID=" + getTransactionID() +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", venteID=" + getVenteID() +
            ", vendeurID=" + getVendeurID() +
            ", quantiteTotal=" + getQuantiteTotal() +
            ", prixTotalTransaction=" + getPrixTotalTransaction() +
            ", validSup='" + isValidSup() + "'" +
            ", validRes='" + isValidRes() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", client=" + getClientId() +
            ", vendeurID=" + getVendeurIDId() +
            "}";
    }
}
