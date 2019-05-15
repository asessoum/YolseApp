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
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "article_id", length = 10, nullable = false)
    private String articleID;

    @NotNull
    @Column(name = "local_id", nullable = false)
    private Integer localID;

    @NotNull
    @Column(name = "remote_id", nullable = false)
    private Integer remoteID;

    @NotNull
    @Size(max = 20)
    @Column(name = "libelle", length = 20, nullable = false)
    private String libelle;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "prix_de_vente")
    private Double prixDeVente;

    @Column(name = "prix_de_revient")
    private Double prixDeRevient;

    @Column(name = "marge_brute")
    private Double margeBrute;

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
    @JsonIgnoreProperties("articles")
    private Categorie categorie;

    @OneToMany(mappedBy = "article")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleID() {
        return articleID;
    }

    public Article articleID(String articleID) {
        this.articleID = articleID;
        return this;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public Integer getLocalID() {
        return localID;
    }

    public Article localID(Integer localID) {
        this.localID = localID;
        return this;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public Article remoteID(Integer remoteID) {
        this.remoteID = remoteID;
        return this;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
    }

    public String getLibelle() {
        return libelle;
    }

    public Article libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Article description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixDeVente() {
        return prixDeVente;
    }

    public Article prixDeVente(Double prixDeVente) {
        this.prixDeVente = prixDeVente;
        return this;
    }

    public void setPrixDeVente(Double prixDeVente) {
        this.prixDeVente = prixDeVente;
    }

    public Double getPrixDeRevient() {
        return prixDeRevient;
    }

    public Article prixDeRevient(Double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
        return this;
    }

    public void setPrixDeRevient(Double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    public Double getMargeBrute() {
        return margeBrute;
    }

    public Article margeBrute(Double margeBrute) {
        this.margeBrute = margeBrute;
        return this;
    }

    public void setMargeBrute(Double margeBrute) {
        this.margeBrute = margeBrute;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Article estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Article creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Article creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Article modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Article modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Article categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public Article ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public Article addVentes(Vente vente) {
        this.ventes.add(vente);
        vente.setArticle(this);
        return this;
    }

    public Article removeVentes(Vente vente) {
        this.ventes.remove(vente);
        vente.setArticle(null);
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
        if (!(o instanceof Article)) {
            return false;
        }
        return id != null && id.equals(((Article) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + getId() +
            ", articleID='" + getArticleID() + "'" +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", prixDeVente=" + getPrixDeVente() +
            ", prixDeRevient=" + getPrixDeRevient() +
            ", margeBrute=" + getMargeBrute() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
