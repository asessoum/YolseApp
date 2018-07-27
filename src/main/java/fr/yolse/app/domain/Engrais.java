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
 * A Engrais.
 */
@Entity
@Table(name = "engrais")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Engrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "engrais_id", nullable = false)
    private Integer engraisID;

    @NotNull
    @Size(max = 20)
    @Column(name = "libelle", length = 20, nullable = false)
    private String libelle;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

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

    @ManyToOne
    @JsonIgnoreProperties("engrais")
    private BesoinEngrais besoinEngrais;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEngraisID() {
        return engraisID;
    }

    public Engrais engraisID(Integer engraisID) {
        this.engraisID = engraisID;
        return this;
    }

    public void setEngraisID(Integer engraisID) {
        this.engraisID = engraisID;
    }

    public String getLibelle() {
        return libelle;
    }

    public Engrais libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public Engrais prixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Engrais estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Engrais creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Engrais creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Engrais modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Engrais modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public BesoinEngrais getBesoinEngrais() {
        return besoinEngrais;
    }

    public Engrais besoinEngrais(BesoinEngrais besoinEngrais) {
        this.besoinEngrais = besoinEngrais;
        return this;
    }

    public void setBesoinEngrais(BesoinEngrais besoinEngrais) {
        this.besoinEngrais = besoinEngrais;
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
        Engrais engrais = (Engrais) o;
        if (engrais.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), engrais.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Engrais{" +
            "id=" + getId() +
            ", engraisID=" + getEngraisID() +
            ", libelle='" + getLibelle() + "'" +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
