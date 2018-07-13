package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EngraisClient entity.
 */
public class EngraisClientDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer engCliID;

    @NotNull
    private Double qEngrais;

    @NotNull
    private Double pTotEngr;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    private Long besoinIntrantsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEngCliID() {
        return engCliID;
    }

    public void setEngCliID(Integer engCliID) {
        this.engCliID = engCliID;
    }

    public Double getqEngrais() {
        return qEngrais;
    }

    public void setqEngrais(Double qEngrais) {
        this.qEngrais = qEngrais;
    }

    public Double getpTotEngr() {
        return pTotEngr;
    }

    public void setpTotEngr(Double pTotEngr) {
        this.pTotEngr = pTotEngr;
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

    public Long getBesoinIntrantsId() {
        return besoinIntrantsId;
    }

    public void setBesoinIntrantsId(Long besoinIntrantId) {
        this.besoinIntrantsId = besoinIntrantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EngraisClientDTO engraisClientDTO = (EngraisClientDTO) o;
        if (engraisClientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), engraisClientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EngraisClientDTO{" +
            "id=" + getId() +
            ", engCliID=" + getEngCliID() +
            ", qEngrais=" + getqEngrais() +
            ", pTotEngr=" + getpTotEngr() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", besoinIntrants=" + getBesoinIntrantsId() +
            "}";
    }
}
