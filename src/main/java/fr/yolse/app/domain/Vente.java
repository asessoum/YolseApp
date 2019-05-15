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
 * A Vente.
 */
@Entity
@Table(name = "vente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "vente_id", nullable = false)
    private Integer venteID;

    @NotNull
    @Column(name = "local_id", nullable = false)
    private Integer localID;

    @NotNull
    @Column(name = "remote_id", nullable = false)
    private Integer remoteID;

    @NotNull
    @Column(name = "quantite", nullable = false)
    private Double quantite;

    @NotNull
    @Column(name = "prix_vente", nullable = false)
    private Double prixVente;

    @NotNull
    @Column(name = "marge_vente", nullable = false)
    private Double margeVente;

    @Column(name = "cree_le")
    private Instant creeLe;

    @Column(name = "cree_par")
    private String creePar;

    @Column(name = "modif_le")
    private Instant modifLe;

    @Column(name = "modif_par")
    private String modifPar;

    @ManyToOne
    @JsonIgnoreProperties("ventes")
    private Transaction transaction;

    @ManyToOne
    @JsonIgnoreProperties("ventes")
    private Article article;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVenteID() {
        return venteID;
    }

    public Vente venteID(Integer venteID) {
        this.venteID = venteID;
        return this;
    }

    public void setVenteID(Integer venteID) {
        this.venteID = venteID;
    }

    public Integer getLocalID() {
        return localID;
    }

    public Vente localID(Integer localID) {
        this.localID = localID;
        return this;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public Vente remoteID(Integer remoteID) {
        this.remoteID = remoteID;
        return this;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
    }

    public Double getQuantite() {
        return quantite;
    }

    public Vente quantite(Double quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public Vente prixVente(Double prixVente) {
        this.prixVente = prixVente;
        return this;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public Double getMargeVente() {
        return margeVente;
    }

    public Vente margeVente(Double margeVente) {
        this.margeVente = margeVente;
        return this;
    }

    public void setMargeVente(Double margeVente) {
        this.margeVente = margeVente;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Vente creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Vente creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Vente modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Vente modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Vente transaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Article getArticle() {
        return article;
    }

    public Vente article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vente)) {
            return false;
        }
        return id != null && id.equals(((Vente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Vente{" +
            "id=" + getId() +
            ", venteID=" + getVenteID() +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", quantite=" + getQuantite() +
            ", prixVente=" + getPrixVente() +
            ", margeVente=" + getMargeVente() +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
