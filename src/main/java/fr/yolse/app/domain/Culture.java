package fr.yolse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Culture.
 */
@Entity
@Table(name = "culture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Culture implements Serializable {

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

    @Column(name = "prix_cession")
    private Double prixCession;

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

    @OneToMany(mappedBy = "cultureEsc")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinIntrant> besoinsIntrantsEscs = new HashSet<>();

    @OneToMany(mappedBy = "cultureGar")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinIntrant> besoinsIntrantsGars = new HashSet<>();

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

    public Culture engraisID(Integer engraisID) {
        this.engraisID = engraisID;
        return this;
    }

    public void setEngraisID(Integer engraisID) {
        this.engraisID = engraisID;
    }

    public String getLibelle() {
        return libelle;
    }

    public Culture libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixCession() {
        return prixCession;
    }

    public Culture prixCession(Double prixCession) {
        this.prixCession = prixCession;
        return this;
    }

    public void setPrixCession(Double prixCession) {
        this.prixCession = prixCession;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Culture estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Culture creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Culture creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Culture modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Culture modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Set<BesoinIntrant> getBesoinsIntrantsEscs() {
        return besoinsIntrantsEscs;
    }

    public Culture besoinsIntrantsEscs(Set<BesoinIntrant> besoinIntrants) {
        this.besoinsIntrantsEscs = besoinIntrants;
        return this;
    }

    public Culture addBesoinsIntrantsEsc(BesoinIntrant besoinIntrant) {
        this.besoinsIntrantsEscs.add(besoinIntrant);
        besoinIntrant.setCultureEsc(this);
        return this;
    }

    public Culture removeBesoinsIntrantsEsc(BesoinIntrant besoinIntrant) {
        this.besoinsIntrantsEscs.remove(besoinIntrant);
        besoinIntrant.setCultureEsc(null);
        return this;
    }

    public void setBesoinsIntrantsEscs(Set<BesoinIntrant> besoinIntrants) {
        this.besoinsIntrantsEscs = besoinIntrants;
    }

    public Set<BesoinIntrant> getBesoinsIntrantsGars() {
        return besoinsIntrantsGars;
    }

    public Culture besoinsIntrantsGars(Set<BesoinIntrant> besoinIntrants) {
        this.besoinsIntrantsGars = besoinIntrants;
        return this;
    }

    public Culture addBesoinsIntrantsGar(BesoinIntrant besoinIntrant) {
        this.besoinsIntrantsGars.add(besoinIntrant);
        besoinIntrant.setCultureGar(this);
        return this;
    }

    public Culture removeBesoinsIntrantsGar(BesoinIntrant besoinIntrant) {
        this.besoinsIntrantsGars.remove(besoinIntrant);
        besoinIntrant.setCultureGar(null);
        return this;
    }

    public void setBesoinsIntrantsGars(Set<BesoinIntrant> besoinIntrants) {
        this.besoinsIntrantsGars = besoinIntrants;
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
        Culture culture = (Culture) o;
        if (culture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), culture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Culture{" +
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
