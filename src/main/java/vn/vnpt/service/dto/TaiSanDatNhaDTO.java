package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.TaiSanDatNha} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDatNhaDTO implements Serializable {

    private Long id;

    private Long idTaiSan;

    private String tenTaiSan;

    private Long trangThai;

    private String thongTinTs;

    private Long idLoaiTs;

    private String ghiChu;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private Long idDuongSu;

    private Long idTsGoc;

    private String maTaiSan;

    private Long idTinhTrang;

    private Long idLoaiNganChan;

    private LocalDate ngayBdNganChan;

    private LocalDate ngayKtNganChan;

    private Long idMaster;

    private String strSearch;

    private Long idDonVi;

    private Long soHsCv;

    private Long soCc;

    private Long soVaoSo;

    private String moTa;

    private Long loaiNganChan;

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

    public String getTenTaiSan() {
        return tenTaiSan;
    }

    public void setTenTaiSan(String tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongTinTs() {
        return thongTinTs;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    public Long getIdLoaiTs() {
        return idLoaiTs;
    }

    public void setIdLoaiTs(Long idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getIdDuongSu() {
        return idDuongSu;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public Long getIdTsGoc() {
        return idTsGoc;
    }

    public void setIdTsGoc(Long idTsGoc) {
        this.idTsGoc = idTsGoc;
    }

    public String getMaTaiSan() {
        return maTaiSan;
    }

    public void setMaTaiSan(String maTaiSan) {
        this.maTaiSan = maTaiSan;
    }

    public Long getIdTinhTrang() {
        return idTinhTrang;
    }

    public void setIdTinhTrang(Long idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public Long getIdLoaiNganChan() {
        return idLoaiNganChan;
    }

    public void setIdLoaiNganChan(Long idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public LocalDate getNgayBdNganChan() {
        return ngayBdNganChan;
    }

    public void setNgayBdNganChan(LocalDate ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDate getNgayKtNganChan() {
        return ngayKtNganChan;
    }

    public void setNgayKtNganChan(LocalDate ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public Long getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getStrSearch() {
        return strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getSoHsCv() {
        return soHsCv;
    }

    public void setSoHsCv(Long soHsCv) {
        this.soHsCv = soHsCv;
    }

    public Long getSoCc() {
        return soCc;
    }

    public void setSoCc(Long soCc) {
        this.soCc = soCc;
    }

    public Long getSoVaoSo() {
        return soVaoSo;
    }

    public void setSoVaoSo(Long soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Long getLoaiNganChan() {
        return loaiNganChan;
    }

    public void setLoaiNganChan(Long loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiSanDatNhaDTO)) {
            return false;
        }

        TaiSanDatNhaDTO taiSanDatNhaDTO = (TaiSanDatNhaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taiSanDatNhaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDatNhaDTO{" +
            "id=" + getId() +
            ", idTaiSan=" + getIdTaiSan() +
            ", tenTaiSan='" + getTenTaiSan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            ", idLoaiTs=" + getIdLoaiTs() +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDuongSu=" + getIdDuongSu() +
            ", idTsGoc=" + getIdTsGoc() +
            ", maTaiSan='" + getMaTaiSan() + "'" +
            ", idTinhTrang=" + getIdTinhTrang() +
            ", idLoaiNganChan=" + getIdLoaiNganChan() +
            ", ngayBdNganChan='" + getNgayBdNganChan() + "'" +
            ", ngayKtNganChan='" + getNgayKtNganChan() + "'" +
            ", idMaster=" + getIdMaster() +
            ", strSearch='" + getStrSearch() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", soHsCv=" + getSoHsCv() +
            ", soCc=" + getSoCc() +
            ", soVaoSo=" + getSoVaoSo() +
            ", moTa='" + getMoTa() + "'" +
            ", loaiNganChan=" + getLoaiNganChan() +
            "}";
    }
}
