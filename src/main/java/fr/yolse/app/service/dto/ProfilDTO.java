package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Profil entity.
 */
public class ProfilDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer profilID;

    @NotNull
    @Size(max = 20)
    private String libelle;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    private Long utilisateursId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfilID() {
        return profilID;
    }

    public void setProfilID(Integer profilID) {
        this.profilID = profilID;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Long getUtilisateursId() {
        return utilisateursId;
    }

    public void setUtilisateursId(Long utiProfilId) {
        this.utilisateursId = utiProfilId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfilDTO profilDTO = (ProfilDTO) o;
        if (profilDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profilDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfilDTO{" +
            "id=" + getId() +
            ", profilID=" + getProfilID() +
            ", libelle='" + getLibelle() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", utilisateurs=" + getUtilisateursId() +
            "}";
    }
}
