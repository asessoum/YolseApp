package fr.yolse.app.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.yolse.app.domain.Article} entity.
 */
public class ArticleDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 10)
    private String articleID;

    @NotNull
    private Integer localID;

    @NotNull
    private Integer remoteID;

    @NotNull
    @Size(max = 20)
    private String libelle;

    @Size(max = 200)
    private String description;

    private Double prixDeVente;

    private Double prixDeRevient;

    private Double margeBrute;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;


    private Long categorieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixDeVente() {
        return prixDeVente;
    }

    public void setPrixDeVente(Double prixDeVente) {
        this.prixDeVente = prixDeVente;
    }

    public Double getPrixDeRevient() {
        return prixDeRevient;
    }

    public void setPrixDeRevient(Double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    public Double getMargeBrute() {
        return margeBrute;
    }

    public void setMargeBrute(Double margeBrute) {
        this.margeBrute = margeBrute;
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

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleDTO articleDTO = (ArticleDTO) o;
        if (articleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), articleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + getId() +
            ", articleID='" + getArticleID() + "'" +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", prixDeVente=" + getPrixDeVente() +
            ", prixDeRevient=" + getPrixDeRevient() +
            ", margeBrute=" + getMargeBrute() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", categorie=" + getCategorieId() +
            "}";
    }
}
