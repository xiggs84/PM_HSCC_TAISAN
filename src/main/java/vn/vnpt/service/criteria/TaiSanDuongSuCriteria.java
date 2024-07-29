package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.TaiSanDuongSu} entity. This class is used
 * in {@link vn.vnpt.web.rest.TaiSanDuongSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tai-san-duong-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDuongSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter trangThai;

    private LongFilter idDuongSu;

    private LocalDateFilter ngayThaoTac;

    private LongFilter idLoaiHopDong;

    private LongFilter idChungThuc;

    private LongFilter idTaiSanId;

    private Boolean distinct;

    public TaiSanDuongSuCriteria() {}

    public TaiSanDuongSuCriteria(TaiSanDuongSuCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.idDuongSu = other.optionalIdDuongSu().map(LongFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.idLoaiHopDong = other.optionalIdLoaiHopDong().map(LongFilter::copy).orElse(null);
        this.idChungThuc = other.optionalIdChungThuc().map(LongFilter::copy).orElse(null);
        this.idTaiSanId = other.optionalIdTaiSanId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TaiSanDuongSuCriteria copy() {
        return new TaiSanDuongSuCriteria(this);
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

    public LongFilter getIdLoaiHopDong() {
        return idLoaiHopDong;
    }

    public Optional<LongFilter> optionalIdLoaiHopDong() {
        return Optional.ofNullable(idLoaiHopDong);
    }

    public LongFilter idLoaiHopDong() {
        if (idLoaiHopDong == null) {
            setIdLoaiHopDong(new LongFilter());
        }
        return idLoaiHopDong;
    }

    public void setIdLoaiHopDong(LongFilter idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public LongFilter getIdChungThuc() {
        return idChungThuc;
    }

    public Optional<LongFilter> optionalIdChungThuc() {
        return Optional.ofNullable(idChungThuc);
    }

    public LongFilter idChungThuc() {
        if (idChungThuc == null) {
            setIdChungThuc(new LongFilter());
        }
        return idChungThuc;
    }

    public void setIdChungThuc(LongFilter idChungThuc) {
        this.idChungThuc = idChungThuc;
    }

    public LongFilter getIdTaiSanId() {
        return idTaiSanId;
    }

    public Optional<LongFilter> optionalIdTaiSanId() {
        return Optional.ofNullable(idTaiSanId);
    }

    public LongFilter idTaiSanId() {
        if (idTaiSanId == null) {
            setIdTaiSanId(new LongFilter());
        }
        return idTaiSanId;
    }

    public void setIdTaiSanId(LongFilter idTaiSanId) {
        this.idTaiSanId = idTaiSanId;
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
        final TaiSanDuongSuCriteria that = (TaiSanDuongSuCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(idDuongSu, that.idDuongSu) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(idLoaiHopDong, that.idLoaiHopDong) &&
            Objects.equals(idChungThuc, that.idChungThuc) &&
            Objects.equals(idTaiSanId, that.idTaiSanId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trangThai, idDuongSu, ngayThaoTac, idLoaiHopDong, idChungThuc, idTaiSanId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDuongSuCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalIdDuongSu().map(f -> "idDuongSu=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalIdLoaiHopDong().map(f -> "idLoaiHopDong=" + f + ", ").orElse("") +
            optionalIdChungThuc().map(f -> "idChungThuc=" + f + ", ").orElse("") +
            optionalIdTaiSanId().map(f -> "idTaiSanId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
