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
    @Column(name = "local_id", nullable = false)
    private Integer localID;

    @NotNull
    @Column(name = "remote_id", nullable = false)
    private Integer remoteID;

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
    @Size(max = 20)
    @Column(name = "num_carte_cli", length = 20, nullable = false)
    private String numCarteCli;

    @NotNull
    @Column(name = "d_carte_util", nullable = false)
    private Instant dCarteUtil;

    @NotNull
    @Size(max = 10)
    @Column(name = "tel", length = 10, nullable = false)
    private String tel;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 200)
    @Column(name = "photo_id", length = 200)
    private String photoID;

    @Size(max = 500)
    @Column(name = "info_supplementaires", length = 500)
    private String infoSupplementaires;

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
    private Utilisateur commercial;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Commune commune;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transaction> transactions = new HashSet<>();

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

    public Integer getLocalID() {
        return localID;
    }

    public Client localID(Integer localID) {
        this.localID = localID;
        return this;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public Client remoteID(Integer remoteID) {
        this.remoteID = remoteID;
        return this;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
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

    public String getInfoSupplementaires() {
        return infoSupplementaires;
    }

    public Client infoSupplementaires(String infoSupplementaires) {
        this.infoSupplementaires = infoSupplementaires;
        return this;
    }

    public void setInfoSupplementaires(String infoSupplementaires) {
        this.infoSupplementaires = infoSupplementaires;
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

    public Utilisateur getCommercial() {
        return commercial;
    }

    public Client commercial(Utilisateur utilisateur) {
        this.commercial = utilisateur;
        return this;
    }

    public void setCommercial(Utilisateur utilisateur) {
        this.commercial = utilisateur;
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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Client transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Client addTransactions(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setClient(this);
        return this;
    }

    public Client removeTransactions(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setClient(null);
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
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", clientID='" + getClientID() + "'" +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", naissance='" + getNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", numCarteCli='" + getNumCarteCli() + "'" +
            ", dCarteUtil='" + getdCarteUtil() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", photoID='" + getPhotoID() + "'" +
            ", infoSupplementaires='" + getInfoSupplementaires() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
