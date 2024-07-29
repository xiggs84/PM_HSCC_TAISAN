package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link vn.vnpt.domain.TaiSan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDTO implements Serializable {

    private Long id;

    private Long idTaiSan;

    private String tenTaiSan;

    private Long trangThai;

    private String thongTinTs;

    private String ghiChu;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private Long idDuongSu;

    private Long idTsGoc;

    private String maTaiSan;

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

    private Long syncStatus;

    private Set<TaiSanDTO> idTsGocs = new HashSet<>();

    private DanhMucLoaiTaiSanDTO idLoaiTs;

    private TinhTrangTaiSanDTO idTinhTrang;

    private Set<TaiSanDTO> taiSans = new HashSet<>();

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

    public Long getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Long syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Set<TaiSanDTO> getIdTsGocs() {
        return idTsGocs;
    }

    public void setIdTsGocs(Set<TaiSanDTO> idTsGocs) {
        this.idTsGocs = idTsGocs;
    }

    public DanhMucLoaiTaiSanDTO getIdLoaiTs() {
        return idLoaiTs;
    }

    public void setIdLoaiTs(DanhMucLoaiTaiSanDTO idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public TinhTrangTaiSanDTO getIdTinhTrang() {
        return idTinhTrang;
    }

    public void setIdTinhTrang(TinhTrangTaiSanDTO idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public Set<TaiSanDTO> getTaiSans() {
        return taiSans;
    }

    public void setTaiSans(Set<TaiSanDTO> taiSans) {
        this.taiSans = taiSans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiSanDTO)) {
            return false;
        }

        TaiSanDTO taiSanDTO = (TaiSanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taiSanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDTO{" +
            "id=" + getId() +
            ", idTaiSan=" + getIdTaiSan() +
            ", tenTaiSan='" + getTenTaiSan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDuongSu=" + getIdDuongSu() +
            ", idTsGoc=" + getIdTsGoc() +
            ", maTaiSan='" + getMaTaiSan() + "'" +
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
            ", syncStatus=" + getSyncStatus() +
            ", idTsGocs=" + getIdTsGocs() +
            ", idLoaiTs=" + getIdLoaiTs() +
            ", idTinhTrang=" + getIdTinhTrang() +
            ", taiSans=" + getTaiSans() +
            "}";
    }
}
