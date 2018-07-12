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
 * A EngraisClient.
 */
@Entity
@Table(name = "engrais_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EngraisClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "eng_cli_id", nullable = false)
    private Integer engCliID;

    @NotNull
    @Column(name = "q_engrais", nullable = false)
    private Double qEngrais;

    @NotNull
    @Column(name = "p_tot_engr", nullable = false)
    private Double pTotEngr;

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

    @OneToMany(mappedBy = "engraisClients")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "engraisClients")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TypeEngrais> typeEngrais = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("engraisClients")
    private BesoinIntrant besoinIntrants;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEngCliID() {
        return engCliID;
    }

    public EngraisClient engCliID(Integer engCliID) {
        this.engCliID = engCliID;
        return this;
    }

    public void setEngCliID(Integer engCliID) {
        this.engCliID = engCliID;
    }

    public Double getqEngrais() {
        return qEngrais;
    }

    public EngraisClient qEngrais(Double qEngrais) {
        this.qEngrais = qEngrais;
        return this;
    }

    public void setqEngrais(Double qEngrais) {
        this.qEngrais = qEngrais;
    }

    public Double getpTotEngr() {
        return pTotEngr;
    }

    public EngraisClient pTotEngr(Double pTotEngr) {
        this.pTotEngr = pTotEngr;
        return this;
    }

    public void setpTotEngr(Double pTotEngr) {
        this.pTotEngr = pTotEngr;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public EngraisClient estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public EngraisClient creeLe(Instant creeLe) {
        this.creeLe = creeLe;
        return this;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public EngraisClient creePar(String creePar) {
        this.creePar = creePar;
        return this;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public EngraisClient modifLe(Instant modifLe) {
        this.modifLe = modifLe;
        return this;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public EngraisClient modifPar(String modifPar) {
        this.modifPar = modifPar;
        return this;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public EngraisClient clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public EngraisClient addClient(Client client) {
        this.clients.add(client);
        client.setEngraisClients(this);
        return this;
    }

    public EngraisClient removeClient(Client client) {
        this.clients.remove(client);
        client.setEngraisClients(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<TypeEngrais> getTypeEngrais() {
        return typeEngrais;
    }

    public EngraisClient typeEngrais(Set<TypeEngrais> typeEngrais) {
        this.typeEngrais = typeEngrais;
        return this;
    }

    public EngraisClient addTypeEngrais(TypeEngrais typeEngrais) {
        this.typeEngrais.add(typeEngrais);
        typeEngrais.setEngraisClients(this);
        return this;
    }

    public EngraisClient removeTypeEngrais(TypeEngrais typeEngrais) {
        this.typeEngrais.remove(typeEngrais);
        typeEngrais.setEngraisClients(null);
        return this;
    }

    public void setTypeEngrais(Set<TypeEngrais> typeEngrais) {
        this.typeEngrais = typeEngrais;
    }

    public BesoinIntrant getBesoinIntrants() {
        return besoinIntrants;
    }

    public EngraisClient besoinIntrants(BesoinIntrant besoinIntrant) {
        this.besoinIntrants = besoinIntrant;
        return this;
    }

    public void setBesoinIntrants(BesoinIntrant besoinIntrant) {
        this.besoinIntrants = besoinIntrant;
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
        EngraisClient engraisClient = (EngraisClient) o;
        if (engraisClient.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), engraisClient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EngraisClient{" +
            "id=" + getId() +
            ", engCliID=" + getEngCliID() +
            ", qEngrais=" + getqEngrais() +
            ", pTotEngr=" + getpTotEngr() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
