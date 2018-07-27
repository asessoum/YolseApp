package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SuiviChamps entity.
 */
public class SuiviChampsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer suiviID;

    @NotNull
    private Instant dVisit;

    @NotNull
    @Size(max = 50)
    private String emplac;

    @Size(max = 1000)
    private String obsAgent;

    @Size(max = 2000)
    private String obsRes;

    @Size(max = 200)
    private String dosImg;

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

    public Integer getSuiviID() {
        return suiviID;
    }

    public void setSuiviID(Integer suiviID) {
        this.suiviID = suiviID;
    }

    public Instant getdVisit() {
        return dVisit;
    }

    public void setdVisit(Instant dVisit) {
        this.dVisit = dVisit;
    }

    public String getEmplac() {
        return emplac;
    }

    public void setEmplac(String emplac) {
        this.emplac = emplac;
    }

    public String getObsAgent() {
        return obsAgent;
    }

    public void setObsAgent(String obsAgent) {
        this.obsAgent = obsAgent;
    }

    public String getObsRes() {
        return obsRes;
    }

    public void setObsRes(String obsRes) {
        this.obsRes = obsRes;
    }

    public String getDosImg() {
        return dosImg;
    }

    public void setDosImg(String dosImg) {
        this.dosImg = dosImg;
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

        SuiviChampsDTO suiviChampsDTO = (SuiviChampsDTO) o;
        if (suiviChampsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suiviChampsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuiviChampsDTO{" +
            "id=" + getId() +
            ", suiviID=" + getSuiviID() +
            ", dVisit='" + getdVisit() + "'" +
            ", emplac='" + getEmplac() + "'" +
            ", obsAgent='" + getObsAgent() + "'" +
            ", obsRes='" + getObsRes() + "'" +
            ", dosImg='" + getDosImg() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
