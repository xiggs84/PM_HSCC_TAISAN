package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.Taisannhadatid} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisannhadatidDTO implements Serializable {

    private Long id;

    private Long idTaiSan;

    private String thongTinTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaiSan() {
        return idTaiSan;
    }

    public void setIdTaiSan(Long idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public String getThongTinTs() {
        return thongTinTs;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaisannhadatidDTO)) {
            return false;
        }

        TaisannhadatidDTO taisannhadatidDTO = (TaisannhadatidDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taisannhadatidDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisannhadatidDTO{" +
            "id=" + getId() +
            ", idTaiSan=" + getIdTaiSan() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            "}";
    }
}
