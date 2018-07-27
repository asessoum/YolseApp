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
 * A Commune.
 */
@Entity
@Table(name = "commune")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Commune implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "commune_id", nullable = false)
    private Integer communeID;

    @NotNull
    @Size(max = 20)
    @Column(name = "nom_commune", length = 20, nullable = false)
    private String nomCommune;

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
    @JsonIgnoreProperties("communes")
    private Province province;

    @OneToMany(mappedBy = "commune")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Utilisateur> utilisateurs = new HashSet<>();

    @OneToMany(mappedBy = "commune")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "commune")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SuiviChamps> suiviChamps = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCommuneID() {
        return communeID;
    }

    public Commune communeID(Integer communeID) {
        this.communeID = communeID;
        return this;
    }

    public void setCommuneID(Integer communeID) {
        this.communeID = communeID;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public Commune nomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
        return this;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public Commune estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public Commune creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public Commune creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public Commune modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public Commune modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Province getProvince() {
        return province;
    }

    public Commune province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public Commune utilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
        return this;
    }

    public Commune addUtilisateurs(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
        utilisateur.setCommune(this);
        return this;
    }

    public Commune removeUtilisateurs(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        utilisateur.setCommune(null);
        return this;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Commune clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Commune addClients(Client client) {
        this.clients.add(client);
        client.setCommune(this);
        return this;
    }

    public Commune removeClients(Client client) {
        this.clients.remove(client);
        client.setCommune(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<SuiviChamps> getSuiviChamps() {
        return suiviChamps;
    }

    public Commune suiviChamps(Set<SuiviChamps> suiviChamps) {
        this.suiviChamps = suiviChamps;
        return this;
    }

    public Commune addSuiviChamps(SuiviChamps suiviChamps) {
        this.suiviChamps.add(suiviChamps);
        suiviChamps.setCommune(this);
        return this;
    }

    public Commune removeSuiviChamps(SuiviChamps suiviChamps) {
        this.suiviChamps.remove(suiviChamps);
        suiviChamps.setCommune(null);
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
        Commune commune = (Commune) o;
        if (commune.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commune.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Commune{" +
            "id=" + getId() +
            ", communeID=" + getCommuneID() +
            ", nomCommune='" + getNomCommune() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
