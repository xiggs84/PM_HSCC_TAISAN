package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ThuaTach} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThuaTachDTO implements Serializable {

    private Long id;

    private Long idThuaTach;

    private Long idTaiSan;

    private String thongTinThuaTach;

    private Long trangThai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdThuaTach() {
        return idThuaTach;
    }

    public void setIdThuaTach(Long idThuaTach) {
        this.idThuaTach = idThuaTach;
    }

    public Long getIdTaiSan() {
        return idTaiSan;
    }

    public void setIdTaiSan(Long idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public String getThongTinThuaTach() {
        return thongTinThuaTach;
    }

    public void setThongTinThuaTach(String thongTinThuaTach) {
        this.thongTinThuaTach = thongTinThuaTach;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThuaTachDTO)) {
            return false;
        }

        ThuaTachDTO thuaTachDTO = (ThuaTachDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, thuaTachDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThuaTachDTO{" +
            "id=" + getId() +
            ", idThuaTach=" + getIdThuaTach() +
            ", idTaiSan=" + getIdTaiSan() +
            ", thongTinThuaTach='" + getThongTinThuaTach() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
