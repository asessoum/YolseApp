package fr.yolse.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import fr.yolse.app.domain.enumeration.Genre;

/**
 * A DTO for the Utilisateur entity.
 */
public class UtilisateurDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer userID;

    @NotNull
    @Size(max = 6)
    private String login;

    @NotNull
    @Size(max = 8)
    private String mdp;

    @NotNull
    @Size(max = 20)
    private String nom;

    @NotNull
    @Size(max = 20)
    private String prenom;

    @NotNull
    private Instant dateNaiss;

    @NotNull
    private Genre genre;

    @NotNull
    @Size(max = 10)
    private String tel;

    @Size(max = 50)
    private String email;

    @NotNull
    @Size(max = 20)
    private String numCarteUti;

    private String nomPAP;

    private String prenomPAP;

    private String telPAP;

    private String lienPAP;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    private Long langueId;

    private Long communeId;

    private Long responsableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    public Instant getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Instant dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumCarteUti() {
        return numCarteUti;
    }

    public void setNumCarteUti(String numCarteUti) {
        this.numCarteUti = numCarteUti;
    }

    public String getNomPAP() {
        return nomPAP;
    }

    public void setNomPAP(String nomPAP) {
        this.nomPAP = nomPAP;
    }

    public String getPrenomPAP() {
        return prenomPAP;
    }

    public void setPrenomPAP(String prenomPAP) {
        this.prenomPAP = prenomPAP;
    }

    public String getTelPAP() {
        return telPAP;
    }

    public void setTelPAP(String telPAP) {
        this.telPAP = telPAP;
    }

    public String getLienPAP() {
        return lienPAP;
    }

    public void setLienPAP(String lienPAP) {
        this.lienPAP = lienPAP;
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

    public Long getLangueId() {
        return langueId;
    }

    public void setLangueId(Long langueId) {
        this.langueId = langueId;
    }

    public Long getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Long communeId) {
        this.communeId = communeId;
    }

    public Long getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Long utilisateurId) {
        this.responsableId = utilisateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UtilisateurDTO utilisateurDTO = (UtilisateurDTO) o;
        if (utilisateurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisateurDTO{" +
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
            ", nomPAP='" + getNomPAP() + "'" +
            ", prenomPAP='" + getPrenomPAP() + "'" +
            ", telPAP='" + getTelPAP() + "'" +
            ", lienPAP='" + getLienPAP() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            ", langue=" + getLangueId() +
            ", commune=" + getCommuneId() +
            ", responsable=" + getResponsableId() +
            "}";
    }
}
