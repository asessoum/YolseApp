package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BesoinEngrais entity.
 */
public class BesoinEngraisDTO implements Serializable {

    private Long id;

    @NotNull
    private Double qEngrais;

    @NotNull
    private Double pTotEngr;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    private Long besoinIntrantId;

    private Long engraisId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getBesoinIntrantId() {
        return besoinIntrantId;
    }

    public void setBesoinIntrantId(Long besoinIntrantId) {
        this.besoinIntrantId = besoinIntrantId;
    }

    public Long getEngraisId() {
        return engraisId;
    }

    public void setEngraisId(Long engraisId) {
        this.engraisId = engraisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BesoinEngraisDTO besoinEngraisDTO = (BesoinEngraisDTO) o;
        if (besoinEngraisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), besoinEngraisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BesoinEngraisDTO{" +
            "id=" + getId() +
            ", qEngrais=" + getqEngrais() +
            ", pTotEngr=" + getpTotEngr() +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", besoinIntrant=" + getBesoinIntrantId() +
            ", engrais=" + getEngraisId() +
            "}";
    }
}
