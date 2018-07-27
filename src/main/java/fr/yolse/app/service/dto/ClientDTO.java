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
    @Size(max = 20)
    private String numCarteCli;

    @NotNull
    private Instant dCarteUtil;

    @NotNull
    @Size(max = 20)
    private String village;

    @NotNull
    @Size(max = 10)
    private String tel;

    @Size(max = 50)
    private String email;

    @Size(max = 50)
    private String groupe;

    @Size(max = 200)
    private String photoID;

    @NotNull
    private Integer tailleMenage;

    @NotNull
    private Double superficiePos;

    @Size(max = 20)
    private String nomPAP;

    @Size(max = 20)
    private String prenomPAP;

    @Size(max = 10)
    private String telPAP;

    @Size(max = 10)
    private String lienPAP;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;

    private Long langueId;

    private Long utilisateurId;

    private Long communeId;

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

    public String getNumCarteCli() {
        return numCarteCli;
    }

    public void setNumCarteCli(String numCarteCli) {
        this.numCarteCli = numCarteCli;
    }

    public Instant getdCarteUtil() {
        return dCarteUtil;
    }

    public void setdCarteUtil(Instant dCarteUtil) {
        this.dCarteUtil = dCarteUtil;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public Integer getTailleMenage() {
        return tailleMenage;
    }

    public void setTailleMenage(Integer tailleMenage) {
        this.tailleMenage = tailleMenage;
    }

    public Double getSuperficiePos() {
        return superficiePos;
    }

    public void setSuperficiePos(Double superficiePos) {
        this.superficiePos = superficiePos;
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

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Long communeId) {
        this.communeId = communeId;
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
            ", numCarteCli='" + getNumCarteCli() + "'" +
            ", dCarteUtil='" + getdCarteUtil() + "'" +
            ", village='" + getVillage() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", groupe='" + getGroupe() + "'" +
            ", photoID='" + getPhotoID() + "'" +
            ", tailleMenage=" + getTailleMenage() +
            ", superficiePos=" + getSuperficiePos() +
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
            ", utilisateur=" + getUtilisateurId() +
            ", commune=" + getCommuneId() +
            "}";
    }
}
