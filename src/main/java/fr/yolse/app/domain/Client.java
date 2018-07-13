package fr.yolse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import fr.yolse.app.domain.enumeration.Genre;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 13)
    @Column(name = "client_id", length = 13, nullable = false)
    private String clientID;

    @NotNull
    @Size(max = 20)
    @Column(name = "nom", length = 20, nullable = false)
    private String nom;

    @NotNull
    @Size(max = 20)
    @Column(name = "prenom", length = 20, nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "naissance", nullable = false)
    private Instant naissance;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @NotNull
    @Column(name = "est_marie", nullable = false)
    private Boolean estMarie;

    @NotNull
    @Column(name = "num_cin", nullable = false)
    private Integer numCin;

    @NotNull
    @Column(name = "valid_cin", nullable = false)
    private Instant validCin;

    @NotNull
    @Size(max = 20)
    @Column(name = "village", length = 20, nullable = false)
    private String village;

    @NotNull
    @Column(name = "tel_1", nullable = false)
    private Integer tel1;

    @Column(name = "tel_2")
    private Integer tel2;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 50)
    @Column(name = "orga_prod", length = 50)
    private String orgaProd;

    @Size(max = 200)
    @Column(name = "photo_id", length = 200)
    private String photoID;

    @Column(name = "t_menage")
    private Integer tMenage;

    @Size(max = 20)
    @Column(name = "nom_per_a_pr", length = 20)
    private String nomPerAPr;

    @Size(max = 20)
    @Column(name = "prenom_per_a_pr", length = 20)
    private String prenomPerAPr;

    @Column(name = "tel_per_a_pr")
    private Integer telPerAPr;

    @NotNull
    @Column(name = "superfice", nullable = false)
    private Double superfice;

    @NotNull
    @Column(name = "q_semence", nullable = false)
    private Double qSemence;

    @NotNull
    @Column(name = "m_simm", nullable = false)
    private Double mSimm;

    @NotNull
    @Column(name = "val_total", nullable = false)
    private Double valTotal;

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
    @JsonIgnoreProperties("clients")
    private SuiviChamps suiviChamps;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private EngraisClient engraisClients;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Utilisateur utilisateurs;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientID() {
        return clientID;
    }

    public Client clientID(String clientID) {
        this.clientID = clientID;
        return this;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getNom() {
        return nom;
    }

    public Client nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Client prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Instant getNaissance() {
        return naissance;
    }

    public Client naissance(Instant naissance) {
        this.naissance = naissance;
        return this;
    }

    public void setNaissance(Instant naissance) {
        this.naissance = naissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public Client genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean isEstMarie() {
        return estMarie;
    }

    public Client estMarie(Boolean estMarie) {
        this.estMarie = estMarie;
        return this;
    }

    public void setEstMarie(Boolean estMarie) {
        this.estMarie = estMarie;
    }

    public Integer getNumCin() {
        return numCin;
    }

    public Client numCin(Integer numCin) {
        this.numCin = numCin;
        return this;
    }

    public void setNumCin(Integer numCin) {
        this.numCin = numCin;
    }

    public Instant getValidCin() {
        return validCin;
    }

    public Client validCin(Instant validCin) {
        this.validCin = validCin;
        return this;
    }

    public void setValidCin(Instant validCin) {
        this.validCin = validCin;
    }

    public String getVillage() {
        return village;
    }

    public Client village(String village) {
        this.village = village;
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Integer getTel1() {
        return tel1;
    }

    public Client tel1(Integer tel1) {
        this.tel1 = tel1;
        return this;
    }

    public void setTel1(Integer tel1) {
        this.tel1 = tel1;
    }

    public Integer getTel2() {
        return tel2;
    }

    public Client tel2(Integer tel2) {
        this.tel2 = tel2;
        return this;
    }

    public void setTel2(Integer tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public Client email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgaProd() {
        return orgaProd;
    }

    public Client orgaProd(String orgaProd) {
        this.orgaProd = orgaProd;
        return this;
    }

    public void setOrgaProd(String orgaProd) {
        this.orgaProd = orgaProd;
    }

    public String getPhotoID() {
        return photoID;
    }

    public Client photoID(String photoID) {
        this.photoID = photoID;
        return this;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public Integer gettMenage() {
        return tMenage;
    }

    public Client tMenage(Integer tMenage) {
        this.tMenage = tMenage;
        return this;
    }

    public void settMenage(Integer tMenage) {
        this.tMenage = tMenage;
    }

    public String getNomPerAPr() {
        return nomPerAPr;
    }

    public Client nomPerAPr(String nomPerAPr) {
        this.nomPerAPr = nomPerAPr;
        return this;
    }

    public void setNomPerAPr(String nomPerAPr) {
        this.nomPerAPr = nomPerAPr;
    }

    public String getPrenomPerAPr() {
        return prenomPerAPr;
    }

    public Client prenomPerAPr(String prenomPerAPr) {
        this.prenomPerAPr = prenomPerAPr;
        return this;
    }

    public void setPrenomPerAPr(String prenomPerAPr) {
        this.prenomPerAPr = prenomPerAPr;
    }

    public Integer getTelPerAPr() {
        return telPerAPr;
    }

    public Client telPerAPr(Integer telPerAPr) {
        this.telPerAPr = telPerAPr;
        return this;
    }

    public void setTelPerAPr(Integer telPerAPr) {
        this.telPerAPr = telPerAPr;
    }

    public Double getSuperfice() {
        return superfice;
    }

    public Client superfice(Double superfice) {
        this.superfice = superfice;
        return this;
    }

    public void setSuperfice(Double superfice) {
        this.superfice = superfice;
    }

    public Double getqSemence() {
        return qSemence;
    }

    public Client qSemence(Double qSemence) {
        this.qSemence = qSemence;
        return this;
    }

    public void setqSemence(Double qSemence) {
        this.qSemence = qSemence;
    }

    public Double getmSimm() {
        return mSimm;
    }

    public Client mSimm(Double mSimm) {
        this.mSimm = mSimm;
        return this;
    }

    public void setmSimm(Double mSimm) {
        this.mSimm = mSimm;
    }

    public Double getValTotal() {
        return valTotal;
    }

    public Client valTotal(Double valTotal) {
        this.valTotal = valTotal;
        return this;
    }

    public void setValTotal(Double valTotal) {
        this.valTotal = valTotal;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Client estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Client creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Client creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Client modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Client modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public SuiviChamps getSuiviChamps() {
        return suiviChamps;
    }

    public Client suiviChamps(SuiviChamps suiviChamps) {
        this.suiviChamps = suiviChamps;
        return this;
    }

    public void setSuiviChamps(SuiviChamps suiviChamps) {
        this.suiviChamps = suiviChamps;
    }

    public EngraisClient getEngraisClients() {
        return engraisClients;
    }

    public Client engraisClients(EngraisClient engraisClient) {
        this.engraisClients = engraisClient;
        return this;
    }

    public void setEngraisClients(EngraisClient engraisClient) {
        this.engraisClients = engraisClient;
    }

    public Utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public Client utilisateurs(Utilisateur utilisateur) {
        this.utilisateurs = utilisateur;
        return this;
    }

    public void setUtilisateurs(Utilisateur utilisateur) {
        this.utilisateurs = utilisateur;
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
        Client client = (Client) o;
        if (client.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", clientID='" + getClientID() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", naissance='" + getNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", estMarie='" + isEstMarie() + "'" +
            ", numCin=" + getNumCin() +
            ", validCin='" + getValidCin() + "'" +
            ", village='" + getVillage() + "'" +
            ", tel1=" + getTel1() +
            ", tel2=" + getTel2() +
            ", email='" + getEmail() + "'" +
            ", orgaProd='" + getOrgaProd() + "'" +
            ", photoID='" + getPhotoID() + "'" +
            ", tMenage=" + gettMenage() +
            ", nomPerAPr='" + getNomPerAPr() + "'" +
            ", prenomPerAPr='" + getPrenomPerAPr() + "'" +
            ", telPerAPr=" + getTelPerAPr() +
            ", superfice=" + getSuperfice() +
            ", qSemence=" + getqSemence() +
            ", mSimm=" + getmSimm() +
            ", valTotal=" + getValTotal() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
