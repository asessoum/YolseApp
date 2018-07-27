package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Culture entity.
 */
public class CultureDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer engraisID;

    @NotNull
    @Size(max = 20)
    private String libelle;

    private Double prixCession;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEngraisID() {
        return engraisID;
    }

    public void setEngraisID(Integer engraisID) {
        this.engraisID = engraisID;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixCession() {
        return prixCession;
    }

    public void setPrixCession(Double prixCession) {
        this.prixCession = prixCession;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CultureDTO cultureDTO = (CultureDTO) o;
        if (cultureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cultureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CultureDTO{" +
            "id=" + getId() +
            ", engraisID=" + getEngraisID() +
            ", libelle='" + getLibelle() + "'" +
            ", prixCession=" + getPrixCession() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
