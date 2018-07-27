package fr.yolse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "superficie_esc", nullable = false)
    private Double superficieEsc;

    @NotNull
    @Column(name = "q_semence", nullable = false)
    private Double qSemence;

    @NotNull
    @Column(name = "valeur_tot", nullable = false)
    private Double valeurTot;

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
    @Column(name = "q_stock_gar", nullable = false)
    private Double qStockGar;

    @NotNull
    @Size(max = 50)
    @Column(name = "magasin_stock", length = 50, nullable = false)
    private String magasinStock;

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

    @ManyToOne
    @JsonIgnoreProperties("besoinIntrants")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("besoinsIntrantsEscs")
    private Culture cultureEsc;

    @ManyToOne
    @JsonIgnoreProperties("besoinsIntrantsGars")
    private Culture cultureGar;

    @OneToMany(mappedBy = "besoinIntrant")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinEngrais> besoinEngrais = new HashSet<>();

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

    public Double getSuperficieEsc() {
        return superficieEsc;
    }

    public BesoinIntrant superficieEsc(Double superficieEsc) {
        this.superficieEsc = superficieEsc;
        return this;
    }

    public void setSuperficieEsc(Double superficieEsc) {
        this.superficieEsc = superficieEsc;
    }

    public Double getqSemence() {
        return qSemence;
    }

    public BesoinIntrant qSemence(Double qSemence) {
        this.qSemence = qSemence;
        return this;
    }

    public void setqSemence(Double qSemence) {
        this.qSemence = qSemence;
    }

    public Double getValeurTot() {
        return valeurTot;
    }

    public BesoinIntrant valeurTot(Double valeurTot) {
        this.valeurTot = valeurTot;
        return this;
    }

    public void setValeurTot(Double valeurTot) {
        this.valeurTot = valeurTot;
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

    public Double getqStockGar() {
        return qStockGar;
    }

    public BesoinIntrant qStockGar(Double qStockGar) {
        this.qStockGar = qStockGar;
        return this;
    }

    public void setqStockGar(Double qStockGar) {
        this.qStockGar = qStockGar;
    }

    public String getMagasinStock() {
        return magasinStock;
    }

    public BesoinIntrant magasinStock(String magasinStock) {
        this.magasinStock = magasinStock;
        return this;
    }

    public void setMagasinStock(String magasinStock) {
        this.magasinStock = magasinStock;
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

    public Client getClient() {
        return client;
    }

    public BesoinIntrant client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Culture getCultureEsc() {
        return cultureEsc;
    }

    public BesoinIntrant cultureEsc(Culture culture) {
        this.cultureEsc = culture;
        return this;
    }

    public void setCultureEsc(Culture culture) {
        this.cultureEsc = culture;
    }

    public Culture getCultureGar() {
        return cultureGar;
    }

    public BesoinIntrant cultureGar(Culture culture) {
        this.cultureGar = culture;
        return this;
    }

    public void setCultureGar(Culture culture) {
        this.cultureGar = culture;
    }

    public Set<BesoinEngrais> getBesoinEngrais() {
        return besoinEngrais;
    }

    public BesoinIntrant besoinEngrais(Set<BesoinEngrais> besoinEngrais) {
        this.besoinEngrais = besoinEngrais;
        return this;
    }

    public BesoinIntrant addBesoinEngrais(BesoinEngrais besoinEngrais) {
        this.besoinEngrais.add(besoinEngrais);
        besoinEngrais.setBesoinIntrant(this);
        return this;
    }

    public BesoinIntrant removeBesoinEngrais(BesoinEngrais besoinEngrais) {
        this.besoinEngrais.remove(besoinEngrais);
        besoinEngrais.setBesoinIntrant(null);
        return this;
    }

    public void setBesoinEngrais(Set<BesoinEngrais> besoinEngrais) {
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
            ", superficieEsc=" + getSuperficieEsc() +
            ", qSemence=" + getqSemence() +
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
            "}";
    }
}
