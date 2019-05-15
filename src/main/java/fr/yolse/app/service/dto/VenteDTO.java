package fr.yolse.app.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.yolse.app.domain.Vente} entity.
 */
public class VenteDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer venteID;

    @NotNull
    private Integer localID;

    @NotNull
    private Integer remoteID;

    @NotNull
    private Double quantite;

    @NotNull
    private Double prixVente;

    @NotNull
    private Double margeVente;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;


    private Long transactionId;

    private Long articleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVenteID() {
        return venteID;
    }

    public void setVenteID(Integer venteID) {
        this.venteID = venteID;
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

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public Double getMargeVente() {
        return margeVente;
    }

    public void setMargeVente(Double margeVente) {
        this.margeVente = margeVente;
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VenteDTO venteDTO = (VenteDTO) o;
        if (venteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), venteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VenteDTO{" +
            "id=" + getId() +
            ", venteID=" + getVenteID() +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", quantite=" + getQuantite() +
            ", prixVente=" + getPrixVente() +
            ", margeVente=" + getMargeVente() +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", transaction=" + getTransactionId() +
            ", article=" + getArticleId() +
            "}";
    }
}
