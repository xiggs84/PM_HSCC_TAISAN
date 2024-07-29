package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.SoHuuTheo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoHuuTheoDTO implements Serializable {

    private Long id;

    private Long idSoHuu;

    private String dienGiai;

    private String tenGcn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSoHuu() {
        return idSoHuu;
    }

    public void setIdSoHuu(Long idSoHuu) {
        this.idSoHuu = idSoHuu;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getTenGcn() {
        return tenGcn;
    }

    public void setTenGcn(String tenGcn) {
        this.tenGcn = tenGcn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoHuuTheoDTO)) {
            return false;
        }

        SoHuuTheoDTO soHuuTheoDTO = (SoHuuTheoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, soHuuTheoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoHuuTheoDTO{" +
            "id=" + getId() +
            ", idSoHuu=" + getIdSoHuu() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", tenGcn='" + getTenGcn() + "'" +
            "}";
    }
}
