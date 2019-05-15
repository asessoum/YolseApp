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
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private Integer transactionID;

    @NotNull
    @Column(name = "local_id", nullable = false)
    private Integer localID;

    @NotNull
    @Column(name = "remote_id", nullable = false)
    private Integer remoteID;

    @NotNull
    @Column(name = "vente_id", nullable = false)
    private Integer venteID;

    @NotNull
    @Column(name = "vendeur_id", nullable = false)
    private Integer vendeurID;

    @NotNull
    @Column(name = "quantite_total", nullable = false)
    private Double quantiteTotal;

    @NotNull
    @Column(name = "prix_total_transaction", nullable = false)
    private Double prixTotalTransaction;

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
    @JsonIgnoreProperties("transactions")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("transactions")
    private Utilisateur vendeurID;

    @OneToMany(mappedBy = "transaction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public Transaction transactionID(Integer transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getLocalID() {
        return localID;
    }

    public Transaction localID(Integer localID) {
        this.localID = localID;
        return this;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public Transaction remoteID(Integer remoteID) {
        this.remoteID = remoteID;
        return this;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
    }

    public Integer getVenteID() {
        return venteID;
    }

    public Transaction venteID(Integer venteID) {
        this.venteID = venteID;
        return this;
    }

    public void setVenteID(Integer venteID) {
        this.venteID = venteID;
    }

    public Integer getVendeurID() {
        return vendeurID;
    }

    public Transaction vendeurID(Integer vendeurID) {
        this.vendeurID = vendeurID;
        return this;
    }

    public void setVendeurID(Integer vendeurID) {
        this.vendeurID = vendeurID;
    }

    public Double getQuantiteTotal() {
        return quantiteTotal;
    }

    public Transaction quantiteTotal(Double quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
        return this;
    }

    public void setQuantiteTotal(Double quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }

    public Double getPrixTotalTransaction() {
        return prixTotalTransaction;
    }

    public Transaction prixTotalTransaction(Double prixTotalTransaction) {
        this.prixTotalTransaction = prixTotalTransaction;
        return this;
    }

    public void setPrixTotalTransaction(Double prixTotalTransaction) {
        this.prixTotalTransaction = prixTotalTransaction;
    }

    public Boolean isValidSup() {
        return validSup;
    }

    public Transaction validSup(Boolean validSup) {
        this.validSup = validSup;
        return this;
    }

    public void setValidSup(Boolean validSup) {
        this.validSup = validSup;
    }

    public Boolean isValidRes() {
        return validRes;
    }

    public Transaction validRes(Boolean validRes) {
        this.validRes = validRes;
        return this;
    }

    public void setValidRes(Boolean validRes) {
        this.validRes = validRes;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Transaction estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Transaction creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Transaction creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Transaction modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Transaction modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Client getClient() {
        return client;
    }

    public Transaction client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getVendeurID() {
        return vendeurID;
    }

    public Transaction vendeurID(Utilisateur utilisateur) {
        this.vendeurID = utilisateur;
        return this;
    }

    public void setVendeurID(Utilisateur utilisateur) {
        this.vendeurID = utilisateur;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public Transaction ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public Transaction addVentes(Vente vente) {
        this.ventes.add(vente);
        vente.setTransaction(this);
        return this;
    }

    public Transaction removeVentes(Vente vente) {
        this.ventes.remove(vente);
        vente.setTransaction(null);
        return this;
    }

    public void setVentes(Set<Vente> ventes) {
        this.ventes = ventes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", transactionID=" + getTransactionID() +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", venteID=" + getVenteID() +
            ", vendeurID=" + getVendeurID() +
            ", quantiteTotal=" + getQuantiteTotal() +
            ", prixTotalTransaction=" + getPrixTotalTransaction() +
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
