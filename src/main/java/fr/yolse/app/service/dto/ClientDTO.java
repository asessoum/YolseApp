package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import fr.yolse.app.domain.enumeration.Genre;

/**
 * A DTO for the Client entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 13)
    private String clientID;

    @NotNull
    @Size(max = 20)
    private String nom;

    @NotNull
    @Size(max = 20)
    private String prenom;

    @NotNull
    private Instant naissance;

    @NotNull
    private Genre genre;

    @NotNull
    private Boolean estMarie;

    @NotNull
    private Integer numCin;

    @NotNull
    private Instant validCin;

    @NotNull
    @Size(max = 20)
    private String village;

    @NotNull
    private Integer tel1;

    private Integer tel2;

    @Size(max = 50)
    private String email;

    @Size(max = 50)
    private String orgaProd;

    @Size(max = 200)
    private String photoID;

    private Integer tMenage;

    @Size(max = 20)
    private String nomPerAPr;

    @Size(max = 20)
    private String prenomPerAPr;

    private Integer telPerAPr;

    @NotNull
    private Double superfice;

    @NotNull
    private Double qSemence;

    @NotNull
    private Double mSimm;

    @NotNull
    private Double valTotal;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    private Long suiviChampsId;

    private Long engraisClientsId;

    private Long utilisateursId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Instant getNaissance() {
        return naissance;
    }

    public void setNaissance(Instant naissance) {
        this.naissance = naissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean isEstMarie() {
        return estMarie;
    }

    public void setEstMarie(Boolean estMarie) {
        this.estMarie = estMarie;
    }

    public Integer getNumCin() {
        return numCin;
    }

    public void setNumCin(Integer numCin) {
        this.numCin = numCin;
    }

    public Instant getValidCin() {
        return validCin;
    }

    public void setValidCin(Instant validCin) {
        this.validCin = validCin;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Integer getTel1() {
        return tel1;
    }

    public void setTel1(Integer tel1) {
        this.tel1 = tel1;
    }

    public Integer getTel2() {
        return tel2;
    }

    public void setTel2(Integer tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgaProd() {
        return orgaProd;
    }

    public void setOrgaProd(String orgaProd) {
        this.orgaProd = orgaProd;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public Integer gettMenage() {
        return tMenage;
    }

    public void settMenage(Integer tMenage) {
        this.tMenage = tMenage;
    }

    public String getNomPerAPr() {
        return nomPerAPr;
    }

    public void setNomPerAPr(String nomPerAPr) {
        this.nomPerAPr = nomPerAPr;
    }

    public String getPrenomPerAPr() {
        return prenomPerAPr;
    }

    public void setPrenomPerAPr(String prenomPerAPr) {
        this.prenomPerAPr = prenomPerAPr;
    }

    public Integer getTelPerAPr() {
        return telPerAPr;
    }

    public void setTelPerAPr(Integer telPerAPr) {
        this.telPerAPr = telPerAPr;
    }

    public Double getSuperfice() {
        return superfice;
    }

    public void setSuperfice(Double superfice) {
        this.superfice = superfice;
    }

    public Double getqSemence() {
        return qSemence;
    }

    public void setqSemence(Double qSemence) {
        this.qSemence = qSemence;
    }

    public Double getmSimm() {
        return mSimm;
    }

    public void setmSimm(Double mSimm) {
        this.mSimm = mSimm;
    }

    public Double getValTotal() {
        return valTotal;
    }

    public void setValTotal(Double valTotal) {
        this.valTotal = valTotal;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Instant getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(Instant creeLe) {
        this.creeLe = creeLe;
    }

    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public Instant getModifLe() {
        return modifLe;
    }

    public void setModifLe(Instant modifLe) {
        this.modifLe = modifLe;
    }

    public String getModifPar() {
        return modifPar;
    }

    public void setModifPar(String modifPar) {
        this.modifPar = modifPar;
    }

    public Long getSuiviChampsId() {
        return suiviChampsId;
    }

    public void setSuiviChampsId(Long suiviChampsId) {
        this.suiviChampsId = suiviChampsId;
    }

    public Long getEngraisClientsId() {
        return engraisClientsId;
    }

    public void setEngraisClientsId(Long engraisClientId) {
        this.engraisClientsId = engraisClientId;
    }

    public Long getUtilisateursId() {
        return utilisateursId;
    }

    public void setUtilisateursId(Long utilisateurId) {
        this.utilisateursId = utilisateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (clientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", clientID='" + getClientID() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", naissance='" + getNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", estMarie='" + isEstMarie() + "'" +
            ", numCin=" + getNumCin() +
            ", validCin='" + getValidCin() + "'" +
            ", village='" + getVillage() + "'" +
            ", tel1=" + getTel1() +
            ", tel2=" + getTel2() +
            ", email='" + getEmail() + "'" +
            ", orgaProd='" + getOrgaProd() + "'" +
            ", photoID='" + getPhotoID() + "'" +
            ", tMenage=" + gettMenage() +
            ", nomPerAPr='" + getNomPerAPr() + "'" +
            ", prenomPerAPr='" + getPrenomPerAPr() + "'" +
            ", telPerAPr=" + getTelPerAPr() +
            ", superfice=" + getSuperfice() +
            ", qSemence=" + getqSemence() +
            ", mSimm=" + getmSimm() +
            ", valTotal=" + getValTotal() +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", suiviChamps=" + getSuiviChampsId() +
            ", engraisClients=" + getEngraisClientsId() +
            ", utilisateurs=" + getUtilisateursId() +
            "}";
    }
}
