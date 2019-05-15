package fr.yolse.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "categorie_id", nullable = false)
    private Integer categorieID;

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

    @OneToMany(mappedBy = "categorie")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Article> articles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategorieID() {
        return categorieID;
    }

    public Categorie categorieID(Integer categorieID) {
        this.categorieID = categorieID;
        return this;
    }

    public void setCategorieID(Integer categorieID) {
        this.categorieID = categorieID;
    }

    public Integer getLocalID() {
        return localID;
    }

    public Categorie localID(Integer localID) {
        this.localID = localID;
        return this;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public Categorie remoteID(Integer remoteID) {
        this.remoteID = remoteID;
        return this;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
    }

    public String getLibelle() {
        return libelle;
    }

    public Categorie libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Categorie estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Categorie creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Categorie creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Categorie modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Categorie modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public Categorie articles(Set<Article> articles) {
        this.articles = articles;
        return this;
    }

    public Categorie addArticles(Article article) {
        this.articles.add(article);
        article.setCategorie(this);
        return this;
    }

    public Categorie removeArticles(Article article) {
        this.articles.remove(article);
        article.setCategorie(null);
        return this;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categorie)) {
            return false;
        }
        return id != null && id.equals(((Categorie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + getId() +
            ", categorieID=" + getCategorieID() +
            ", localID=" + getLocalID() +
            ", remoteID=" + getRemoteID() +
            ", libelle='" + getLibelle() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
