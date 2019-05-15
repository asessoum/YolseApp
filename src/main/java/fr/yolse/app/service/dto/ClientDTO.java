package fr.yolse.app.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import fr.yolse.app.domain.enumeration.Genre;

/**
 * A DTO for the {@link fr.yolse.app.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 13)
    private String clientID;

    @NotNull
    private Integer localID;

    @NotNull
    private Integer remoteID;

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
    @Size(max = 20)
    private String numCarteCli;

    @NotNull
    private Instant dCarteUtil;

    @NotNull
    @Size(max = 10)
    private String tel;

    @Size(max = 50)
    private String email;

    @Size(max = 200)
    private String photoID;

    @Size(max = 500)
    private String infoSupplementaires;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;


    private Long langueId;

    private Long commercialId;

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

    public Integer getLocalID() {
        return localID;
    }

    public void setLocalID(Integer localID) {
        this.localID = localID;
    }

    public Integer getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(Integer remoteID) {
        this.remoteID = remoteID;
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

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getInfoSupplementaires() {
        return infoSupplementaires;
    }

    public void setInfoSupplementaires(String infoSupplementaires) {
        this.infoSupplementaires = infoSupplementaires;
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

    public Long getCommercialId() {
        return commercialId;
    }

    public void setCommercialId(Long utilisateurId) {
        this.commercialId = utilisateurId;
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
            ", langue=" + getLangueId() +
            ", commercial=" + getCommercialId() +
            ", commune=" + getCommuneId() +
            "}";
    }
}
