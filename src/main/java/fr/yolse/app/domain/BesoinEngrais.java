package fr.yolse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A BesoinEngrais.
 */
@Entity
@Table(name = "besoin_engrais")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BesoinEngrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "q_engrais", nullable = false)
    private Double qEngrais;

    @NotNull
    @Column(name = "p_tot_engr", nullable = false)
    private Double pTotEngr;

    @Column(name = "cree_le")
    private Instant creeLe;

    @Column(name = "cree_par")
    private String creePar;

    @Column(name = "modif_le")
    private Instant modifLe;

    @Column(name = "modif_par")
    private String modifPar;

    @ManyToOne
    @JsonIgnoreProperties("besoinEngrais")
    private BesoinIntrant besoinIntrant;

    @ManyToOne
    @JsonIgnoreProperties("besoinEngrais")
    private Engrais engrais;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getqEngrais() {
        return qEngrais;
    }

    public BesoinEngrais qEngrais(Double qEngrais) {
        this.qEngrais = qEngrais;
        return this;
    }

    public void setqEngrais(Double qEngrais) {
        this.qEngrais = qEngrais;
    }

    public Double getpTotEngr() {
        return pTotEngr;
    }

    public BesoinEngrais pTotEngr(Double pTotEngr) {
        this.pTotEngr = pTotEngr;
        return this;
    }

    public void setpTotEngr(Double pTotEngr) {
        this.pTotEngr = pTotEngr;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public BesoinEngrais creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public BesoinEngrais creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public BesoinEngrais modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public BesoinEngrais modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public BesoinIntrant getBesoinIntrant() {
        return besoinIntrant;
    }

    public BesoinEngrais besoinIntrant(BesoinIntrant besoinIntrant) {
        this.besoinIntrant = besoinIntrant;
        return this;
    }

    public void setBesoinIntrant(BesoinIntrant besoinIntrant) {
        this.besoinIntrant = besoinIntrant;
    }

    public Engrais getEngrais() {
        return engrais;
    }

    public BesoinEngrais engrais(Engrais engrais) {
        this.engrais = engrais;
        return this;
    }

    public void setEngrais(Engrais engrais) {
        this.engrais = engrais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BesoinEngrais besoinEngrais = (BesoinEngrais) o;
        if (besoinEngrais.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), besoinEngrais.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BesoinEngrais{" +
            "id=" + getId() +
            ", qEngrais=" + getqEngrais() +
            ", pTotEngr=" + getpTotEngr() +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
