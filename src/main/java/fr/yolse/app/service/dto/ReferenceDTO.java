package fr.yolse.app.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.yolse.app.domain.Reference} entity.
 */
public class ReferenceDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idRef;

    @NotNull
    @Size(max = 10)
    private String libelleRef;

    @NotNull
    @Size(max = 100)
    private String valeurRef;

    private Boolean estActif;

    private Instant creeLe;

    private String creePar;

    private Instant modifLe;

    private String modifPar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRef() {
        return idRef;
    }

    public void setIdRef(Integer idRef) {
        this.idRef = idRef;
    }

    public String getLibelleRef() {
        return libelleRef;
    }

    public void setLibelleRef(String libelleRef) {
        this.libelleRef = libelleRef;
    }

    public String getValeurRef() {
        return valeurRef;
    }

    public void setValeurRef(String valeurRef) {
        this.valeurRef = valeurRef;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferenceDTO referenceDTO = (ReferenceDTO) o;
        if (referenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferenceDTO{" +
            "id=" + getId() +
            ", idRef=" + getIdRef() +
            ", libelleRef='" + getLibelleRef() + "'" +
            ", valeurRef='" + getValeurRef() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", creeLe='" + getCreeLe() + "'" +
            ", creePar='" + getCreePar() + "'" +
            ", modifLe='" + getModifLe() + "'" +
            ", modifPar='" + getModifPar() + "'" +
            "}";
    }
}
