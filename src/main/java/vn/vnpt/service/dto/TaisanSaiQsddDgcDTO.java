package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.TaisanSaiQsddDgc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisanSaiQsddDgcDTO implements Serializable {

    private Long id;

    private Long idMaster;

    private String noiCapQsdd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getNoiCapQsdd() {
        return noiCapQsdd;
    }

    public void setNoiCapQsdd(String noiCapQsdd) {
        this.noiCapQsdd = noiCapQsdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaisanSaiQsddDgcDTO)) {
            return false;
        }

        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO = (TaisanSaiQsddDgcDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taisanSaiQsddDgcDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisanSaiQsddDgcDTO{" +
            "id=" + getId() +
            ", idMaster=" + getIdMaster() +
            ", noiCapQsdd='" + getNoiCapQsdd() + "'" +
            "}";
    }
}
