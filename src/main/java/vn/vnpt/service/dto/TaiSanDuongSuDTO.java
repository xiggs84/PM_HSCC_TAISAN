package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.TaiSanDuongSu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDuongSuDTO implements Serializable {

    private Long id;

    private Long trangThai;

    private Long idDuongSu;

    private LocalDate ngayThaoTac;

    private Long idLoaiHopDong;

    private Long idChungThuc;

    private TaiSanDTO idTaiSan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getIdDuongSu() {
        return idDuongSu;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getIdLoaiHopDong() {
        return idLoaiHopDong;
    }

    public void setIdLoaiHopDong(Long idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public Long getIdChungThuc() {
        return idChungThuc;
    }

    public void setIdChungThuc(Long idChungThuc) {
        this.idChungThuc = idChungThuc;
    }

    public TaiSanDTO getIdTaiSan() {
        return idTaiSan;
    }

    public void setIdTaiSan(TaiSanDTO idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiSanDuongSuDTO)) {
            return false;
        }

        TaiSanDuongSuDTO taiSanDuongSuDTO = (TaiSanDuongSuDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taiSanDuongSuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDuongSuDTO{" +
            "id=" + getId() +
            ", trangThai=" + getTrangThai() +
            ", idDuongSu=" + getIdDuongSu() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", idLoaiHopDong=" + getIdLoaiHopDong() +
            ", idChungThuc=" + getIdChungThuc() +
            ", idTaiSan=" + getIdTaiSan() +
            "}";
    }
}
