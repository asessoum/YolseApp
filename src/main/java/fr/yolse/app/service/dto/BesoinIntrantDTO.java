package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BesoinIntrant entity.
 */
public class BesoinIntrantDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer besIntID;

    @NotNull
    private Double mAdhesion;

    @NotNull
    private Double mAssur;

    @NotNull
    private Double mGaran;

    @NotNull
    private Integer stockGar;

    @NotNull
    @Size(max = 50)
    private String magasin;

    @NotNull
    @Size(max = 50)
    private String sfd;

    @NotNull
    private Double mUniGes;

    @NotNull
    private Double mAdmin;

    @NotNull
    private Double mExploi;

    private Boolean validSup;

    private Boolean validRes;

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

    public Integer getBesIntID() {
        return besIntID;
    }

    public void setBesIntID(Integer besIntID) {
        this.besIntID = besIntID;
    }

    public Double getmAdhesion() {
        return mAdhesion;
    }

    public void setmAdhesion(Double mAdhesion) {
        this.mAdhesion = mAdhesion;
    }

    public Double getmAssur() {
        return mAssur;
    }

    public void setmAssur(Double mAssur) {
        this.mAssur = mAssur;
    }

    public Double getmGaran() {
        return mGaran;
    }

    public void setmGaran(Double mGaran) {
        this.mGaran = mGaran;
    }

    public Integer getStockGar() {
        return stockGar;
    }

    public void setStockGar(Integer stockGar) {
        this.stockGar = stockGar;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getSfd() {
        return sfd;
    }

    public void setSfd(String sfd) {
        this.sfd = sfd;
    }

    public Double getmUniGes() {
        return mUniGes;
    }

    public void setmUniGes(Double mUniGes) {
        this.mUniGes = mUniGes;
    }

    public Double getmAdmin() {
        return mAdmin;
    }

    public void setmAdmin(Double mAdmin) {
        this.mAdmin = mAdmin;
    }

    public Double getmExploi() {
        return mExploi;
    }

    public void setmExploi(Double mExploi) {
        this.mExploi = mExploi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BesoinIntrantDTO besoinIntrantDTO = (BesoinIntrantDTO) o;
        if (besoinIntrantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), besoinIntrantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BesoinIntrantDTO{" +
            "id=" + getId() +
            ", besIntID=" + getBesIntID() +
            ", mAdhesion=" + getmAdhesion() +
            ", mAssur=" + getmAssur() +
            ", mGaran=" + getmGaran() +
            ", stockGar=" + getStockGar() +
            ", magasin='" + getMagasin() + "'" +
            ", sfd='" + getSfd() + "'" +
            ", mUniGes=" + getmUniGes() +
            ", mAdmin=" + getmAdmin() +
            ", mExploi=" + getmExploi() +
            ", validSup='" + isValidSup() + "'" +
            ", validRes='" + isValidRes() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
