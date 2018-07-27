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
    @Size(max = 20)
    @Column(name = "num_carte_cli", length = 20, nullable = false)
    private String numCarteCli;

    @NotNull
    @Column(name = "d_carte_util", nullable = false)
    private Instant dCarteUtil;

    @NotNull
    @Size(max = 20)
    @Column(name = "village", length = 20, nullable = false)
    private String village;

    @NotNull
    @Size(max = 10)
    @Column(name = "tel", length = 10, nullable = false)
    private String tel;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 50)
    @Column(name = "groupe", length = 50)
    private String groupe;

    @Size(max = 200)
    @Column(name = "photo_id", length = 200)
    private String photoID;

    @NotNull
    @Column(name = "taille_menage", nullable = false)
    private Integer tailleMenage;

    @NotNull
    @Column(name = "superficie_pos", nullable = false)
    private Double superficiePos;

    @Size(max = 20)
    @Column(name = "nom_pap", length = 20)
    private String nomPAP;

    @Size(max = 20)
    @Column(name = "prenom_pap", length = 20)
    private String prenomPAP;

    @Size(max = 10)
    @Column(name = "tel_pap", length = 10)
    private String telPAP;

    @Size(max = 10)
    @Column(name = "lien_pap", length = 10)
    private String lienPAP;

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
    private Langue langue;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Commune commune;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinIntrant> besoinIntrants = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SuiviChamps> suiviChamps = new HashSet<>();

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

    public String getNumCarteCli() {
        return numCarteCli;
    }

    public Client numCarteCli(String numCarteCli) {
        this.numCarteCli = numCarteCli;
        return this;
    }

    public void setNumCarteCli(String numCarteCli) {
        this.numCarteCli = numCarteCli;
    }

    public Instant getdCarteUtil() {
        return dCarteUtil;
    }

    public Client dCarteUtil(Instant dCarteUtil) {
        this.dCarteUtil = dCarteUtil;
        return this;
    }

    public void setdCarteUtil(Instant dCarteUtil) {
        this.dCarteUtil = dCarteUtil;
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

    public String getTel() {
        return tel;
    }

    public Client tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getGroupe() {
        return groupe;
    }

    public Client groupe(String groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
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

    public Integer getTailleMenage() {
        return tailleMenage;
    }

    public Client tailleMenage(Integer tailleMenage) {
        this.tailleMenage = tailleMenage;
        return this;
    }

    public void setTailleMenage(Integer tailleMenage) {
        this.tailleMenage = tailleMenage;
    }

    public Double getSuperficiePos() {
        return superficiePos;
    }

    public Client superficiePos(Double superficiePos) {
        this.superficiePos = superficiePos;
        return this;
    }

    public void setSuperficiePos(Double superficiePos) {
        this.superficiePos = superficiePos;
    }

    public String getNomPAP() {
        return nomPAP;
    }

    public Client nomPAP(String nomPAP) {
        this.nomPAP = nomPAP;
        return this;
    }

    public void setNomPAP(String nomPAP) {
        this.nomPAP = nomPAP;
    }

    public String getPrenomPAP() {
        return prenomPAP;
    }

    public Client prenomPAP(String prenomPAP) {
        this.prenomPAP = prenomPAP;
        return this;
    }

    public void setPrenomPAP(String prenomPAP) {
        this.prenomPAP = prenomPAP;
    }

    public String getTelPAP() {
        return telPAP;
    }

    public Client telPAP(String telPAP) {
        this.telPAP = telPAP;
        return this;
    }

    public void setTelPAP(String telPAP) {
        this.telPAP = telPAP;
    }

    public String getLienPAP() {
        return lienPAP;
    }

    public Client lienPAP(String lienPAP) {
        this.lienPAP = lienPAP;
        return this;
    }

    public void setLienPAP(String lienPAP) {
        this.lienPAP = lienPAP;
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

    public Langue getLangue() {
        return langue;
    }

    public Client langue(Langue langue) {
        this.langue = langue;
        return this;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Client utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Commune getCommune() {
        return commune;
    }

    public Client commune(Commune commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Set<BesoinIntrant> getBesoinIntrants() {
        return besoinIntrants;
    }

    public Client besoinIntrants(Set<BesoinIntrant> besoinIntrants) {
        this.besoinIntrants = besoinIntrants;
        return this;
    }

    public Client addBesoinIntrants(BesoinIntrant besoinIntrant) {
        this.besoinIntrants.add(besoinIntrant);
        besoinIntrant.setClient(this);
        return this;
    }

    public Client removeBesoinIntrants(BesoinIntrant besoinIntrant) {
        this.besoinIntrants.remove(besoinIntrant);
        besoinIntrant.setClient(null);
        return this;
    }

    public void setBesoinIntrants(Set<BesoinIntrant> besoinIntrants) {
        this.besoinIntrants = besoinIntrants;
    }

    public Set<SuiviChamps> getSuiviChamps() {
        return suiviChamps;
    }

    public Client suiviChamps(Set<SuiviChamps> suiviChamps) {
        this.suiviChamps = suiviChamps;
        return this;
    }

    public Client addSuiviChamps(SuiviChamps suiviChamps) {
        this.suiviChamps.add(suiviChamps);
        suiviChamps.setClient(this);
        return this;
    }

    public Client removeSuiviChamps(SuiviChamps suiviChamps) {
        this.suiviChamps.remove(suiviChamps);
        suiviChamps.setClient(null);
        return this;
    }

    public void setSuiviChamps(Set<SuiviChamps> suiviChamps) {
        this.suiviChamps = suiviChamps;
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
            ", numCarteCli='" + getNumCarteCli() + "'" +
            ", dCarteUtil='" + getdCarteUtil() + "'" +
            ", village='" + getVillage() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", groupe='" + getGroupe() + "'" +
            ", photoID='" + getPhotoID() + "'" +
            ", tailleMenage=" + getTailleMenage() +
            ", superficiePos=" + getSuperficiePos() +
            ", nomPAP='" + getNomPAP() + "'" +
            ", prenomPAP='" + getPrenomPAP() + "'" +
            ", telPAP='" + getTelPAP() + "'" +
            ", lienPAP='" + getLienPAP() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
