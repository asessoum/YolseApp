package fr.yolse.app.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Reference.
 */
@Entity
@Table(name = "reference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_ref", nullable = false)
    private Integer idRef;

    @NotNull
    @Size(max = 10)
    @Column(name = "libelle_ref", length = 10, nullable = false)
    private String libelleRef;

    @NotNull
    @Size(max = 100)
    @Column(name = "valeur_ref", length = 100, nullable = false)
    private String valeurRef;

    @Column(name = "est_actif")
    private Boolean estActif;

    @Column(name = "cree_le")
    private Instant creeLe;

    @Column(name = "cree_par")
    private String creePar;

    @Column(name = "modif_le")
    private Instant modifLe;

    @Column(name = "modif_par")
    private String modifPar;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRef() {
        return idRef;
    }

    public Reference idRef(Integer idRef) {
        this.idRef = idRef;
        return this;
    }

    public void setIdRef(Integer idRef) {
        this.idRef = idRef;
    }

    public String getLibelleRef() {
        return libelleRef;
    }

    public Reference libelleRef(String libelleRef) {
        this.libelleRef = libelleRef;
        return this;
    }

    public void setLibelleRef(String libelleRef) {
        this.libelleRef = libelleRef;
    }

    public String getValeurRef() {
        return valeurRef;
    }

    public Reference valeurRef(String valeurRef) {
        this.valeurRef = valeurRef;
        return this;
    }

    public void setValeurRef(String valeurRef) {
        this.valeurRef = valeurRef;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Reference estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Reference creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Reference creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Reference modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Reference modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reference)) {
            return false;
        }
        return id != null && id.equals(((Reference) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reference{" +
            "id=" + getId() +
            ", idRef=" + getIdRef() +
            ", libelleRef='" + getLibelleRef() + "'" +
            ", valeurRef='" + getValeurRef() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
