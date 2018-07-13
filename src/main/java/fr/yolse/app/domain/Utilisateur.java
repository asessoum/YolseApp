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
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userID;

    @NotNull
    @Size(max = 6)
    @Column(name = "login", length = 6, nullable = false)
    private String login;

    @NotNull
    @Size(max = 8)
    @Column(name = "mdp", length = 8, nullable = false)
    private String mdp;

    @NotNull
    @Size(max = 20)
    @Column(name = "nom", length = 20, nullable = false)
    private String nom;

    @NotNull
    @Size(max = 20)
    @Column(name = "prenom", length = 20, nullable = false)
    private String prenom;

    @Column(name = "naissance")
    private Instant naissance;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @Column(name = "tel_1")
    private Integer tel1;

    @Column(name = "tel_2")
    private Integer tel2;

    @Column(name = "email")
    private String email;

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

    @OneToMany(mappedBy = "utilisateurs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TypeCulture> typeCultures = new HashSet<>();

    @OneToMany(mappedBy = "utilisateurs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "utilisateurs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Langue> langues = new HashSet<>();

    @OneToMany(mappedBy = "utilisateurs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Commune> communes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("utilisateurs")
    private UtiProfil profils;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public Utilisateur userID(Integer userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public Utilisateur login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public Utilisateur mdp(String mdp) {
        this.mdp = mdp;
        return this;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public Utilisateur nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Utilisateur prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Instant getNaissance() {
        return naissance;
    }

    public Utilisateur naissance(Instant naissance) {
        this.naissance = naissance;
        return this;
    }

    public void setNaissance(Instant naissance) {
        this.naissance = naissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public Utilisateur genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getTel1() {
        return tel1;
    }

    public Utilisateur tel1(Integer tel1) {
        this.tel1 = tel1;
        return this;
    }

    public void setTel1(Integer tel1) {
        this.tel1 = tel1;
    }

    public Integer getTel2() {
        return tel2;
    }

    public Utilisateur tel2(Integer tel2) {
        this.tel2 = tel2;
        return this;
    }

    public void setTel2(Integer tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public Utilisateur email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Utilisateur estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Utilisateur creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Utilisateur creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Utilisateur modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Utilisateur modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Set<TypeCulture> getTypeCultures() {
        return typeCultures;
    }

    public Utilisateur typeCultures(Set<TypeCulture> typeCultures) {
        this.typeCultures = typeCultures;
        return this;
    }

    public Utilisateur addTypeCulture(TypeCulture typeCulture) {
        this.typeCultures.add(typeCulture);
        typeCulture.setUtilisateurs(this);
        return this;
    }

    public Utilisateur removeTypeCulture(TypeCulture typeCulture) {
        this.typeCultures.remove(typeCulture);
        typeCulture.setUtilisateurs(null);
        return this;
    }

    public void setTypeCultures(Set<TypeCulture> typeCultures) {
        this.typeCultures = typeCultures;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Utilisateur clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Utilisateur addClient(Client client) {
        this.clients.add(client);
        client.setUtilisateurs(this);
        return this;
    }

    public Utilisateur removeClient(Client client) {
        this.clients.remove(client);
        client.setUtilisateurs(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Langue> getLangues() {
        return langues;
    }

    public Utilisateur langues(Set<Langue> langues) {
        this.langues = langues;
        return this;
    }

    public Utilisateur addLangue(Langue langue) {
        this.langues.add(langue);
        langue.setUtilisateurs(this);
        return this;
    }

    public Utilisateur removeLangue(Langue langue) {
        this.langues.remove(langue);
        langue.setUtilisateurs(null);
        return this;
    }

    public void setLangues(Set<Langue> langues) {
        this.langues = langues;
    }

    public Set<Commune> getCommunes() {
        return communes;
    }

    public Utilisateur communes(Set<Commune> communes) {
        this.communes = communes;
        return this;
    }

    public Utilisateur addCommune(Commune commune) {
        this.communes.add(commune);
        commune.setUtilisateurs(this);
        return this;
    }

    public Utilisateur removeCommune(Commune commune) {
        this.communes.remove(commune);
        commune.setUtilisateurs(null);
        return this;
    }

    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
    }

    public UtiProfil getProfils() {
        return profils;
    }

    public Utilisateur profils(UtiProfil utiProfil) {
        this.profils = utiProfil;
        return this;
    }

    public void setProfils(UtiProfil utiProfil) {
        this.profils = utiProfil;
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
        Utilisateur utilisateur = (Utilisateur) o;
        if (utilisateur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", userID=" + getUserID() +
            ", login='" + getLogin() + "'" +
            ", mdp='" + getMdp() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", naissance='" + getNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", tel1=" + getTel1() +
            ", tel2=" + getTel2() +
            ", email='" + getEmail() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
