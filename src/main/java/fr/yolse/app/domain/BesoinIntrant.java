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
 * A BesoinIntrant.
 */
@Entity
@Table(name = "besoin_intrant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BesoinIntrant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "bes_int_id", nullable = false)
    private Integer besIntID;

    @NotNull
    @Column(name = "m_adhesion", nullable = false)
    private Double mAdhesion;

    @NotNull
    @Column(name = "m_assur", nullable = false)
    private Double mAssur;

    @NotNull
    @Column(name = "m_garan", nullable = false)
    private Double mGaran;

    @NotNull
    @Column(name = "stock_gar", nullable = false)
    private Integer stockGar;

    @NotNull
    @Size(max = 50)
    @Column(name = "magasin", length = 50, nullable = false)
    private String magasin;

    @NotNull
    @Size(max = 50)
    @Column(name = "sfd", length = 50, nullable = false)
    private String sfd;

    @NotNull
    @Column(name = "m_uni_ges", nullable = false)
    private Double mUniGes;

    @NotNull
    @Column(name = "m_admin", nullable = false)
    private Double mAdmin;

    @NotNull
    @Column(name = "m_exploi", nullable = false)
    private Double mExploi;

    @Column(name = "valid_sup")
    private Boolean validSup;

    @Column(name = "valid_res")
    private Boolean validRes;

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

    @OneToMany(mappedBy = "besoinIntrants")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EngraisClient> engraisClients = new HashSet<>();

    @OneToMany(mappedBy = "engraisClients")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TypeCulture> typeCultures = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBesIntID() {
        return besIntID;
    }

    public BesoinIntrant besIntID(Integer besIntID) {
        this.besIntID = besIntID;
        return this;
    }

    public void setBesIntID(Integer besIntID) {
        this.besIntID = besIntID;
    }

    public Double getmAdhesion() {
        return mAdhesion;
    }

    public BesoinIntrant mAdhesion(Double mAdhesion) {
        this.mAdhesion = mAdhesion;
        return this;
    }

    public void setmAdhesion(Double mAdhesion) {
        this.mAdhesion = mAdhesion;
    }

    public Double getmAssur() {
        return mAssur;
    }

    public BesoinIntrant mAssur(Double mAssur) {
        this.mAssur = mAssur;
        return this;
    }

    public void setmAssur(Double mAssur) {
        this.mAssur = mAssur;
    }

    public Double getmGaran() {
        return mGaran;
    }

    public BesoinIntrant mGaran(Double mGaran) {
        this.mGaran = mGaran;
        return this;
    }

    public void setmGaran(Double mGaran) {
        this.mGaran = mGaran;
    }

    public Integer getStockGar() {
        return stockGar;
    }

    public BesoinIntrant stockGar(Integer stockGar) {
        this.stockGar = stockGar;
        return this;
    }

    public void setStockGar(Integer stockGar) {
        this.stockGar = stockGar;
    }

    public String getMagasin() {
        return magasin;
    }

    public BesoinIntrant magasin(String magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getSfd() {
        return sfd;
    }

    public BesoinIntrant sfd(String sfd) {
        this.sfd = sfd;
        return this;
    }

    public void setSfd(String sfd) {
        this.sfd = sfd;
    }

    public Double getmUniGes() {
        return mUniGes;
    }

    public BesoinIntrant mUniGes(Double mUniGes) {
        this.mUniGes = mUniGes;
        return this;
    }

    public void setmUniGes(Double mUniGes) {
        this.mUniGes = mUniGes;
    }

    public Double getmAdmin() {
        return mAdmin;
    }

    public BesoinIntrant mAdmin(Double mAdmin) {
        this.mAdmin = mAdmin;
        return this;
    }

    public void setmAdmin(Double mAdmin) {
        this.mAdmin = mAdmin;
    }

    public Double getmExploi() {
        return mExploi;
    }

    public BesoinIntrant mExploi(Double mExploi) {
        this.mExploi = mExploi;
        return this;
    }

    public void setmExploi(Double mExploi) {
        this.mExploi = mExploi;
    }

    public Boolean isValidSup() {
        return validSup;
    }

    public BesoinIntrant validSup(Boolean validSup) {
        this.validSup = validSup;
        return this;
    }

    public void setValidSup(Boolean validSup) {
        this.validSup = validSup;
    }

    public Boolean isValidRes() {
        return validRes;
    }

    public BesoinIntrant validRes(Boolean validRes) {
        this.validRes = validRes;
        return this;
    }

    public void setValidRes(Boolean validRes) {
        this.validRes = validRes;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public BesoinIntrant estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public BesoinIntrant creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public BesoinIntrant creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public BesoinIntrant modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public BesoinIntrant modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Set<EngraisClient> getEngraisClients() {
        return engraisClients;
    }

    public BesoinIntrant engraisClients(Set<EngraisClient> engraisClients) {
        this.engraisClients = engraisClients;
        return this;
    }

    public BesoinIntrant addEngraisClient(EngraisClient engraisClient) {
        this.engraisClients.add(engraisClient);
        engraisClient.setBesoinIntrants(this);
        return this;
    }

    public BesoinIntrant removeEngraisClient(EngraisClient engraisClient) {
        this.engraisClients.remove(engraisClient);
        engraisClient.setBesoinIntrants(null);
        return this;
    }

    public void setEngraisClients(Set<EngraisClient> engraisClients) {
        this.engraisClients = engraisClients;
    }

    public Set<TypeCulture> getTypeCultures() {
        return typeCultures;
    }

    public BesoinIntrant typeCultures(Set<TypeCulture> typeCultures) {
        this.typeCultures = typeCultures;
        return this;
    }

    public BesoinIntrant addTypeCulture(TypeCulture typeCulture) {
        this.typeCultures.add(typeCulture);
        typeCulture.setEngraisClients(this);
        return this;
    }

    public BesoinIntrant removeTypeCulture(TypeCulture typeCulture) {
        this.typeCultures.remove(typeCulture);
        typeCulture.setEngraisClients(null);
        return this;
    }

    public void setTypeCultures(Set<TypeCulture> typeCultures) {
        this.typeCultures = typeCultures;
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
        BesoinIntrant besoinIntrant = (BesoinIntrant) o;
        if (besoinIntrant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), besoinIntrant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BesoinIntrant{" +
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
