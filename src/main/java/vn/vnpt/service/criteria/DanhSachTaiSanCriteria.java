package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhSachTaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhSachTaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-sach-tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhSachTaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idTaiSan;

    private StringFilter tenTaiSan;

    private LongFilter trangThai;

    private StringFilter ghiChu;

    private LocalDateFilter ngayThaoTac;

    private LongFilter nguoiThaoTac;

    private LongFilter idDuongSu;

    private LongFilter idTsGoc;

    private StringFilter maTaiSan;

    private LongFilter idTinhTrang;

    private LongFilter idLoaiNganChan;

    private LocalDateFilter ngayBdNganChan;

    private LocalDateFilter ngayKtNganChan;

    private LongFilter idMaster;

    private StringFilter strSearch;

    private LongFilter idDonVi;

    private LongFilter soHsCv;

    private LongFilter soCc;

    private LongFilter soVaoSo;

    private StringFilter moTa;

    private LongFilter loaiNganChan;

    private StringFilter maXa;

    private LongFilter idLoaiTsId;

    private Boolean distinct;

    public DanhSachTaiSanCriteria() {}

    public DanhSachTaiSanCriteria(DanhSachTaiSanCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idTaiSan = other.optionalIdTaiSan().map(LongFilter::copy).orElse(null);
        this.tenTaiSan = other.optionalTenTaiSan().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.ghiChu = other.optionalGhiChu().map(StringFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.nguoiThaoTac = other.optionalNguoiThaoTac().map(LongFilter::copy).orElse(null);
        this.idDuongSu = other.optionalIdDuongSu().map(LongFilter::copy).orElse(null);
        this.idTsGoc = other.optionalIdTsGoc().map(LongFilter::copy).orElse(null);
        this.maTaiSan = other.optionalMaTaiSan().map(StringFilter::copy).orElse(null);
        this.idTinhTrang = other.optionalIdTinhTrang().map(LongFilter::copy).orElse(null);
        this.idLoaiNganChan = other.optionalIdLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.ngayBdNganChan = other.optionalNgayBdNganChan().map(LocalDateFilter::copy).orElse(null);
        this.ngayKtNganChan = other.optionalNgayKtNganChan().map(LocalDateFilter::copy).orElse(null);
        this.idMaster = other.optionalIdMaster().map(LongFilter::copy).orElse(null);
        this.strSearch = other.optionalStrSearch().map(StringFilter::copy).orElse(null);
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.soHsCv = other.optionalSoHsCv().map(LongFilter::copy).orElse(null);
        this.soCc = other.optionalSoCc().map(LongFilter::copy).orElse(null);
        this.soVaoSo = other.optionalSoVaoSo().map(LongFilter::copy).orElse(null);
        this.moTa = other.optionalMoTa().map(StringFilter::copy).orElse(null);
        this.loaiNganChan = other.optionalLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.maXa = other.optionalMaXa().map(StringFilter::copy).orElse(null);
        this.idLoaiTsId = other.optionalIdLoaiTsId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhSachTaiSanCriteria copy() {
        return new DanhSachTaiSanCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdTaiSan() {
        return idTaiSan;
    }

    public Optional<LongFilter> optionalIdTaiSan() {
        return Optional.ofNullable(idTaiSan);
    }

    public LongFilter idTaiSan() {
        if (idTaiSan == null) {
            setIdTaiSan(new LongFilter());
        }
        return idTaiSan;
    }

    public void setIdTaiSan(LongFilter idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public StringFilter getTenTaiSan() {
        return tenTaiSan;
    }

    public Optional<StringFilter> optionalTenTaiSan() {
        return Optional.ofNullable(tenTaiSan);
    }

    public StringFilter tenTaiSan() {
        if (tenTaiSan == null) {
            setTenTaiSan(new StringFilter());
        }
        return tenTaiSan;
    }

    public void setTenTaiSan(StringFilter tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public LongFilter getTrangThai() {
        return trangThai;
    }

    public Optional<LongFilter> optionalTrangThai() {
        return Optional.ofNullable(trangThai);
    }

    public LongFilter trangThai() {
        if (trangThai == null) {
            setTrangThai(new LongFilter());
        }
        return trangThai;
    }

    public void setTrangThai(LongFilter trangThai) {
        this.trangThai = trangThai;
    }

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public Optional<StringFilter> optionalGhiChu() {
        return Optional.ofNullable(ghiChu);
    }

    public StringFilter ghiChu() {
        if (ghiChu == null) {
            setGhiChu(new StringFilter());
        }
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDateFilter getNgayThaoTac() {
        return ngayThaoTac;
    }

    public Optional<LocalDateFilter> optionalNgayThaoTac() {
        return Optional.ofNullable(ngayThaoTac);
    }

    public LocalDateFilter ngayThaoTac() {
        if (ngayThaoTac == null) {
            setNgayThaoTac(new LocalDateFilter());
        }
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDateFilter ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public LongFilter getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public Optional<LongFilter> optionalNguoiThaoTac() {
        return Optional.ofNullable(nguoiThaoTac);
    }

    public LongFilter nguoiThaoTac() {
        if (nguoiThaoTac == null) {
            setNguoiThaoTac(new LongFilter());
        }
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(LongFilter nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LongFilter getIdDuongSu() {
        return idDuongSu;
    }

    public Optional<LongFilter> optionalIdDuongSu() {
        return Optional.ofNullable(idDuongSu);
    }

    public LongFilter idDuongSu() {
        if (idDuongSu == null) {
            setIdDuongSu(new LongFilter());
        }
        return idDuongSu;
    }

    public void setIdDuongSu(LongFilter idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public LongFilter getIdTsGoc() {
        return idTsGoc;
    }

    public Optional<LongFilter> optionalIdTsGoc() {
        return Optional.ofNullable(idTsGoc);
    }

    public LongFilter idTsGoc() {
        if (idTsGoc == null) {
            setIdTsGoc(new LongFilter());
        }
        return idTsGoc;
    }

    public void setIdTsGoc(LongFilter idTsGoc) {
        this.idTsGoc = idTsGoc;
    }

    public StringFilter getMaTaiSan() {
        return maTaiSan;
    }

    public Optional<StringFilter> optionalMaTaiSan() {
        return Optional.ofNullable(maTaiSan);
    }

    public StringFilter maTaiSan() {
        if (maTaiSan == null) {
            setMaTaiSan(new StringFilter());
        }
        return maTaiSan;
    }

    public void setMaTaiSan(StringFilter maTaiSan) {
        this.maTaiSan = maTaiSan;
    }

    public LongFilter getIdTinhTrang() {
        return idTinhTrang;
    }

    public Optional<LongFilter> optionalIdTinhTrang() {
        return Optional.ofNullable(idTinhTrang);
    }

    public LongFilter idTinhTrang() {
        if (idTinhTrang == null) {
            setIdTinhTrang(new LongFilter());
        }
        return idTinhTrang;
    }

    public void setIdTinhTrang(LongFilter idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public LongFilter getIdLoaiNganChan() {
        return idLoaiNganChan;
    }

    public Optional<LongFilter> optionalIdLoaiNganChan() {
        return Optional.ofNullable(idLoaiNganChan);
    }

    public LongFilter idLoaiNganChan() {
        if (idLoaiNganChan == null) {
            setIdLoaiNganChan(new LongFilter());
        }
        return idLoaiNganChan;
    }

    public void setIdLoaiNganChan(LongFilter idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public LocalDateFilter getNgayBdNganChan() {
        return ngayBdNganChan;
    }

    public Optional<LocalDateFilter> optionalNgayBdNganChan() {
        return Optional.ofNullable(ngayBdNganChan);
    }

    public LocalDateFilter ngayBdNganChan() {
        if (ngayBdNganChan == null) {
            setNgayBdNganChan(new LocalDateFilter());
        }
        return ngayBdNganChan;
    }

    public void setNgayBdNganChan(LocalDateFilter ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDateFilter getNgayKtNganChan() {
        return ngayKtNganChan;
    }

    public Optional<LocalDateFilter> optionalNgayKtNganChan() {
        return Optional.ofNullable(ngayKtNganChan);
    }

    public LocalDateFilter ngayKtNganChan() {
        if (ngayKtNganChan == null) {
            setNgayKtNganChan(new LocalDateFilter());
        }
        return ngayKtNganChan;
    }

    public void setNgayKtNganChan(LocalDateFilter ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public LongFilter getIdMaster() {
        return idMaster;
    }

    public Optional<LongFilter> optionalIdMaster() {
        return Optional.ofNullable(idMaster);
    }

    public LongFilter idMaster() {
        if (idMaster == null) {
            setIdMaster(new LongFilter());
        }
        return idMaster;
    }

    public void setIdMaster(LongFilter idMaster) {
        this.idMaster = idMaster;
    }

    public StringFilter getStrSearch() {
        return strSearch;
    }

    public Optional<StringFilter> optionalStrSearch() {
        return Optional.ofNullable(strSearch);
    }

    public StringFilter strSearch() {
        if (strSearch == null) {
            setStrSearch(new StringFilter());
        }
        return strSearch;
    }

    public void setStrSearch(StringFilter strSearch) {
        this.strSearch = strSearch;
    }

    public LongFilter getIdDonVi() {
        return idDonVi;
    }

    public Optional<LongFilter> optionalIdDonVi() {
        return Optional.ofNullable(idDonVi);
    }

    public LongFilter idDonVi() {
        if (idDonVi == null) {
            setIdDonVi(new LongFilter());
        }
        return idDonVi;
    }

    public void setIdDonVi(LongFilter idDonVi) {
        this.idDonVi = idDonVi;
    }

    public LongFilter getSoHsCv() {
        return soHsCv;
    }

    public Optional<LongFilter> optionalSoHsCv() {
        return Optional.ofNullable(soHsCv);
    }

    public LongFilter soHsCv() {
        if (soHsCv == null) {
            setSoHsCv(new LongFilter());
        }
        return soHsCv;
    }

    public void setSoHsCv(LongFilter soHsCv) {
        this.soHsCv = soHsCv;
    }

    public LongFilter getSoCc() {
        return soCc;
    }

    public Optional<LongFilter> optionalSoCc() {
        return Optional.ofNullable(soCc);
    }

    public LongFilter soCc() {
        if (soCc == null) {
            setSoCc(new LongFilter());
        }
        return soCc;
    }

    public void setSoCc(LongFilter soCc) {
        this.soCc = soCc;
    }

    public LongFilter getSoVaoSo() {
        return soVaoSo;
    }

    public Optional<LongFilter> optionalSoVaoSo() {
        return Optional.ofNullable(soVaoSo);
    }

    public LongFilter soVaoSo() {
        if (soVaoSo == null) {
            setSoVaoSo(new LongFilter());
        }
        return soVaoSo;
    }

    public void setSoVaoSo(LongFilter soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public StringFilter getMoTa() {
        return moTa;
    }

    public Optional<StringFilter> optionalMoTa() {
        return Optional.ofNullable(moTa);
    }

    public StringFilter moTa() {
        if (moTa == null) {
            setMoTa(new StringFilter());
        }
        return moTa;
    }

    public void setMoTa(StringFilter moTa) {
        this.moTa = moTa;
    }

    public LongFilter getLoaiNganChan() {
        return loaiNganChan;
    }

    public Optional<LongFilter> optionalLoaiNganChan() {
        return Optional.ofNullable(loaiNganChan);
    }

    public LongFilter loaiNganChan() {
        if (loaiNganChan == null) {
            setLoaiNganChan(new LongFilter());
        }
        return loaiNganChan;
    }

    public void setLoaiNganChan(LongFilter loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    public StringFilter getMaXa() {
        return maXa;
    }

    public Optional<StringFilter> optionalMaXa() {
        return Optional.ofNullable(maXa);
    }

    public StringFilter maXa() {
        if (maXa == null) {
            setMaXa(new StringFilter());
        }
        return maXa;
    }

    public void setMaXa(StringFilter maXa) {
        this.maXa = maXa;
    }

    public LongFilter getIdLoaiTsId() {
        return idLoaiTsId;
    }

    public Optional<LongFilter> optionalIdLoaiTsId() {
        return Optional.ofNullable(idLoaiTsId);
    }

    public LongFilter idLoaiTsId() {
        if (idLoaiTsId == null) {
            setIdLoaiTsId(new LongFilter());
        }
        return idLoaiTsId;
    }

    public void setIdLoaiTsId(LongFilter idLoaiTsId) {
        this.idLoaiTsId = idLoaiTsId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanhSachTaiSanCriteria that = (DanhSachTaiSanCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idTaiSan, that.idTaiSan) &&
            Objects.equals(tenTaiSan, that.tenTaiSan) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(nguoiThaoTac, that.nguoiThaoTac) &&
            Objects.equals(idDuongSu, that.idDuongSu) &&
            Objects.equals(idTsGoc, that.idTsGoc) &&
            Objects.equals(maTaiSan, that.maTaiSan) &&
            Objects.equals(idTinhTrang, that.idTinhTrang) &&
            Objects.equals(idLoaiNganChan, that.idLoaiNganChan) &&
            Objects.equals(ngayBdNganChan, that.ngayBdNganChan) &&
            Objects.equals(ngayKtNganChan, that.ngayKtNganChan) &&
            Objects.equals(idMaster, that.idMaster) &&
            Objects.equals(strSearch, that.strSearch) &&
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(soHsCv, that.soHsCv) &&
            Objects.equals(soCc, that.soCc) &&
            Objects.equals(soVaoSo, that.soVaoSo) &&
            Objects.equals(moTa, that.moTa) &&
            Objects.equals(loaiNganChan, that.loaiNganChan) &&
            Objects.equals(maXa, that.maXa) &&
            Objects.equals(idLoaiTsId, that.idLoaiTsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            idTaiSan,
            tenTaiSan,
            trangThai,
            ghiChu,
            ngayThaoTac,
            nguoiThaoTac,
            idDuongSu,
            idTsGoc,
            maTaiSan,
            idTinhTrang,
            idLoaiNganChan,
            ngayBdNganChan,
            ngayKtNganChan,
            idMaster,
            strSearch,
            idDonVi,
            soHsCv,
            soCc,
            soVaoSo,
            moTa,
            loaiNganChan,
            maXa,
            idLoaiTsId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhSachTaiSanCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdTaiSan().map(f -> "idTaiSan=" + f + ", ").orElse("") +
            optionalTenTaiSan().map(f -> "tenTaiSan=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalGhiChu().map(f -> "ghiChu=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalNguoiThaoTac().map(f -> "nguoiThaoTac=" + f + ", ").orElse("") +
            optionalIdDuongSu().map(f -> "idDuongSu=" + f + ", ").orElse("") +
            optionalIdTsGoc().map(f -> "idTsGoc=" + f + ", ").orElse("") +
            optionalMaTaiSan().map(f -> "maTaiSan=" + f + ", ").orElse("") +
            optionalIdTinhTrang().map(f -> "idTinhTrang=" + f + ", ").orElse("") +
            optionalIdLoaiNganChan().map(f -> "idLoaiNganChan=" + f + ", ").orElse("") +
            optionalNgayBdNganChan().map(f -> "ngayBdNganChan=" + f + ", ").orElse("") +
            optionalNgayKtNganChan().map(f -> "ngayKtNganChan=" + f + ", ").orElse("") +
            optionalIdMaster().map(f -> "idMaster=" + f + ", ").orElse("") +
            optionalStrSearch().map(f -> "strSearch=" + f + ", ").orElse("") +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalSoHsCv().map(f -> "soHsCv=" + f + ", ").orElse("") +
            optionalSoCc().map(f -> "soCc=" + f + ", ").orElse("") +
            optionalSoVaoSo().map(f -> "soVaoSo=" + f + ", ").orElse("") +
            optionalMoTa().map(f -> "moTa=" + f + ", ").orElse("") +
            optionalLoaiNganChan().map(f -> "loaiNganChan=" + f + ", ").orElse("") +
            optionalMaXa().map(f -> "maXa=" + f + ", ").orElse("") +
            optionalIdLoaiTsId().map(f -> "idLoaiTsId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
