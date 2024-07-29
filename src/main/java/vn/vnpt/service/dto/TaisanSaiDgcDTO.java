package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.TaisanSaiDgc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisanSaiDgcDTO implements Serializable {

    private Long id;

    private Long idMaster;

    private String thongTinTs;

    private String thongTinTsDung;

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

    public String getThongTinTs() {
        return thongTinTs;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    public String getThongTinTsDung() {
        return thongTinTsDung;
    }

    public void setThongTinTsDung(String thongTinTsDung) {
        this.thongTinTsDung = thongTinTsDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaisanSaiDgcDTO)) {
            return false;
        }

        TaisanSaiDgcDTO taisanSaiDgcDTO = (TaisanSaiDgcDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taisanSaiDgcDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisanSaiDgcDTO{" +
            "id=" + getId() +
            ", idMaster=" + getIdMaster() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            ", thongTinTsDung='" + getThongTinTsDung() + "'" +
            "}";
    }
}
