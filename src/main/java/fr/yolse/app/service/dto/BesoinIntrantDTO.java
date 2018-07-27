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
    private Double superficieEsc;

    @NotNull
    private Double qSemence;

    @NotNull
    private Double prixTotSemence;

    @NotNull
    private Double valeurTot;

    @NotNull
    private Double mAdhesion;

    @NotNull
    private Double mAssur;

    @NotNull
    private Double mGaran;

    @NotNull
    private Double qStockGar;

    @NotNull
    @Size(max = 50)
    private String magasinStock;

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

    private Long clientId;

    private Long cultureEscId;

    private Long cultureGarId;

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

    public Double getSuperficieEsc() {
        return superficieEsc;
    }

    public void setSuperficieEsc(Double superficieEsc) {
        this.superficieEsc = superficieEsc;
    }

    public Double getqSemence() {
        return qSemence;
    }

    public void setqSemence(Double qSemence) {
        this.qSemence = qSemence;
    }

    public Double getPrixTotSemence() {
        return prixTotSemence;
    }

    public void setPrixTotSemence(Double prixTotSemence) {
        this.prixTotSemence = prixTotSemence;
    }

    public Double getValeurTot() {
        return valeurTot;
    }

    public void setValeurTot(Double valeurTot) {
        this.valeurTot = valeurTot;
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

    public Double getqStockGar() {
        return qStockGar;
    }

    public void setqStockGar(Double qStockGar) {
        this.qStockGar = qStockGar;
    }

    public String getMagasinStock() {
        return magasinStock;
    }

    public void setMagasinStock(String magasinStock) {
        this.magasinStock = magasinStock;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCultureEscId() {
        return cultureEscId;
    }

    public void setCultureEscId(Long cultureId) {
        this.cultureEscId = cultureId;
    }

    public Long getCultureGarId() {
        return cultureGarId;
    }

    public void setCultureGarId(Long cultureId) {
        this.cultureGarId = cultureId;
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
            ", superficieEsc=" + getSuperficieEsc() +
            ", qSemence=" + getqSemence() +
            ", prixTotSemence=" + getPrixTotSemence() +
            ", valeurTot=" + getValeurTot() +
            ", mAdhesion=" + getmAdhesion() +
            ", mAssur=" + getmAssur() +
            ", mGaran=" + getmGaran() +
            ", qStockGar=" + getqStockGar() +
            ", magasinStock='" + getMagasinStock() + "'" +
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
            ", client=" + getClientId() +
            ", cultureEsc=" + getCultureEscId() +
            ", cultureGar=" + getCultureGarId() +
            "}";
    }
}
