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

    @NotNull
    @Column(name = "date_naiss", nullable = false)
    private Instant dateNaiss;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @NotNull
    @Size(max = 10)
    @Column(name = "tel", length = 10, nullable = false)
    private String tel;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @NotNull
    @Size(max = 20)
    @Column(name = "num_carte_uti", length = 20, nullable = false)
    private String numCarteUti;

    @Column(name = "date_carte_uti")
    private Instant dateCarteUti;

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
    @JsonIgnoreProperties("utilisateurs")
    private Langue langue;

    @ManyToOne
    @JsonIgnoreProperties("utilisateurs")
    private Commune commune;

    @ManyToOne
    @JsonIgnoreProperties("employes")
    private Utilisateur responsable;

    @OneToMany(mappedBy = "responsable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Utilisateur> employes = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UtiProfile> profiles = new HashSet<>();

    @OneToMany(mappedBy = "commercial")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "vendeurID")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transaction> transactions = new HashSet<>();

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

    public Instant getDateNaiss() {
        return dateNaiss;
    }

    public Utilisateur dateNaiss(Instant dateNaiss) {
        this.dateNaiss = dateNaiss;
        return this;
    }

    public void setDateNaiss(Instant dateNaiss) {
        this.dateNaiss = dateNaiss;
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

    public String getTel() {
        return tel;
    }

    public Utilisateur tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getNumCarteUti() {
        return numCarteUti;
    }

    public Utilisateur numCarteUti(String numCarteUti) {
        this.numCarteUti = numCarteUti;
        return this;
    }

    public void setNumCarteUti(String numCarteUti) {
        this.numCarteUti = numCarteUti;
    }

    public Instant getDateCarteUti() {
        return dateCarteUti;
    }

    public Utilisateur dateCarteUti(Instant dateCarteUti) {
        this.dateCarteUti = dateCarteUti;
        return this;
    }

    public void setDateCarteUti(Instant dateCarteUti) {
        this.dateCarteUti = dateCarteUti;
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

    public Langue getLangue() {
        return langue;
    }

    public Utilisateur langue(Langue langue) {
        this.langue = langue;
        return this;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Commune getCommune() {
        return commune;
    }

    public Utilisateur commune(Commune commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Utilisateur getResponsable() {
        return responsable;
    }

    public Utilisateur responsable(Utilisateur utilisateur) {
        this.responsable = utilisateur;
        return this;
    }

    public void setResponsable(Utilisateur utilisateur) {
        this.responsable = utilisateur;
    }

    public Set<Utilisateur> getEmployes() {
        return employes;
    }

    public Utilisateur employes(Set<Utilisateur> utilisateurs) {
        this.employes = utilisateurs;
        return this;
    }

    public Utilisateur addEmployes(Utilisateur utilisateur) {
        this.employes.add(utilisateur);
        utilisateur.setResponsable(this);
        return this;
    }

    public Utilisateur removeEmployes(Utilisateur utilisateur) {
        this.employes.remove(utilisateur);
        utilisateur.setResponsable(null);
        return this;
    }

    public void setEmployes(Set<Utilisateur> utilisateurs) {
        this.employes = utilisateurs;
    }

    public Set<UtiProfile> getProfiles() {
        return profiles;
    }

    public Utilisateur profiles(Set<UtiProfile> utiProfiles) {
        this.profiles = utiProfiles;
        return this;
    }

    public Utilisateur addProfiles(UtiProfile utiProfile) {
        this.profiles.add(utiProfile);
        utiProfile.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeProfiles(UtiProfile utiProfile) {
        this.profiles.remove(utiProfile);
        utiProfile.setUtilisateur(null);
        return this;
    }

    public void setProfiles(Set<UtiProfile> utiProfiles) {
        this.profiles = utiProfiles;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Utilisateur clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Utilisateur addClients(Client client) {
        this.clients.add(client);
        client.setCommercial(this);
        return this;
    }

    public Utilisateur removeClients(Client client) {
        this.clients.remove(client);
        client.setCommercial(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Utilisateur transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Utilisateur addTransactions(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setVendeurID(this);
        return this;
    }

    public Utilisateur removeTransactions(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setVendeurID(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
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
            ", dateNaiss='" + getDateNaiss() + "'" +
            ", genre='" + getGenre() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", numCarteUti='" + getNumCarteUti() + "'" +
            ", dateCarteUti='" + getDateCarteUti() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
