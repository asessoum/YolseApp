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
 * A SuiviChamps.
 */
@Entity
@Table(name = "suivi_champs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SuiviChamps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "suivi_id", nullable = false)
    private Integer suiviID;

    @NotNull
    @Column(name = "d_visit", nullable = false)
    private Instant dVisit;

    @NotNull
    @Size(max = 50)
    @Column(name = "emplac", length = 50, nullable = false)
    private String emplac;

    @Size(max = 1000)
    @Column(name = "obs_agent", length = 1000)
    private String obsAgent;

    @Size(max = 2000)
    @Column(name = "obs_res", length = 2000)
    private String obsRes;

    @Size(max = 200)
    @Column(name = "dos_img", length = 200)
    private String dosImg;

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
    @JsonIgnoreProperties("suiviChamps")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("suiviChamps")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnoreProperties("suiviChamps")
    private Commune commune;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSuiviID() {
        return suiviID;
    }

    public SuiviChamps suiviID(Integer suiviID) {
        this.suiviID = suiviID;
        return this;
    }

    public void setSuiviID(Integer suiviID) {
        this.suiviID = suiviID;
    }

    public Instant getdVisit() {
        return dVisit;
    }

    public SuiviChamps dVisit(Instant dVisit) {
        this.dVisit = dVisit;
        return this;
    }

    public void setdVisit(Instant dVisit) {
        this.dVisit = dVisit;
    }

    public String getEmplac() {
        return emplac;
    }

    public SuiviChamps emplac(String emplac) {
        this.emplac = emplac;
        return this;
    }

    public void setEmplac(String emplac) {
        this.emplac = emplac;
    }

    public String getObsAgent() {
        return obsAgent;
    }

    public SuiviChamps obsAgent(String obsAgent) {
        this.obsAgent = obsAgent;
        return this;
    }

    public void setObsAgent(String obsAgent) {
        this.obsAgent = obsAgent;
    }

    public String getObsRes() {
        return obsRes;
    }

    public SuiviChamps obsRes(String obsRes) {
        this.obsRes = obsRes;
        return this;
    }

    public void setObsRes(String obsRes) {
        this.obsRes = obsRes;
    }

    public String getDosImg() {
        return dosImg;
    }

    public SuiviChamps dosImg(String dosImg) {
        this.dosImg = dosImg;
        return this;
    }

    public void setDosImg(String dosImg) {
        this.dosImg = dosImg;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public SuiviChamps estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public SuiviChamps creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public SuiviChamps creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public SuiviChamps modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public SuiviChamps modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Client getClient() {
        return client;
    }

    public SuiviChamps client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public SuiviChamps utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Commune getCommune() {
        return commune;
    }

    public SuiviChamps commune(Commune commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
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
        SuiviChamps suiviChamps = (SuiviChamps) o;
        if (suiviChamps.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suiviChamps.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuiviChamps{" +
            "id=" + getId() +
            ", suiviID=" + getSuiviID() +
            ", dVisit='" + getdVisit() + "'" +
            ", emplac='" + getEmplac() + "'" +
            ", obsAgent='" + getObsAgent() + "'" +
            ", obsRes='" + getObsRes() + "'" +
            ", dosImg='" + getDosImg() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
