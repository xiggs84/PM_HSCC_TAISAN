package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucLoaiTaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucLoaiTaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-loai-tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiTaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idLoaiTs;

    private StringFilter dienGiai;

    private LongFilter trangThai;

    private StringFilter searchStr;

    private LongFilter taiSanId;

    private LongFilter danhSachTaiSanId;

    private Boolean distinct;

    public DanhMucLoaiTaiSanCriteria() {}

    public DanhMucLoaiTaiSanCriteria(DanhMucLoaiTaiSanCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idLoaiTs = other.optionalIdLoaiTs().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.searchStr = other.optionalSearchStr().map(StringFilter::copy).orElse(null);
        this.taiSanId = other.optionalTaiSanId().map(LongFilter::copy).orElse(null);
        this.danhSachTaiSanId = other.optionalDanhSachTaiSanId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucLoaiTaiSanCriteria copy() {
        return new DanhMucLoaiTaiSanCriteria(this);
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

    public LongFilter getIdLoaiTs() {
        return idLoaiTs;
    }

    public Optional<LongFilter> optionalIdLoaiTs() {
        return Optional.ofNullable(idLoaiTs);
    }

    public LongFilter idLoaiTs() {
        if (idLoaiTs == null) {
            setIdLoaiTs(new LongFilter());
        }
        return idLoaiTs;
    }

    public void setIdLoaiTs(LongFilter idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public StringFilter getDienGiai() {
        return dienGiai;
    }

    public Optional<StringFilter> optionalDienGiai() {
        return Optional.ofNullable(dienGiai);
    }

    public StringFilter dienGiai() {
        if (dienGiai == null) {
            setDienGiai(new StringFilter());
        }
        return dienGiai;
    }

    public void setDienGiai(StringFilter dienGiai) {
        this.dienGiai = dienGiai;
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

    public StringFilter getSearchStr() {
        return searchStr;
    }

    public Optional<StringFilter> optionalSearchStr() {
        return Optional.ofNullable(searchStr);
    }

    public StringFilter searchStr() {
        if (searchStr == null) {
            setSearchStr(new StringFilter());
        }
        return searchStr;
    }

    public void setSearchStr(StringFilter searchStr) {
        this.searchStr = searchStr;
    }

    public LongFilter getTaiSanId() {
        return taiSanId;
    }

    public Optional<LongFilter> optionalTaiSanId() {
        return Optional.ofNullable(taiSanId);
    }

    public LongFilter taiSanId() {
        if (taiSanId == null) {
            setTaiSanId(new LongFilter());
        }
        return taiSanId;
    }

    public void setTaiSanId(LongFilter taiSanId) {
        this.taiSanId = taiSanId;
    }

    public LongFilter getDanhSachTaiSanId() {
        return danhSachTaiSanId;
    }

    public Optional<LongFilter> optionalDanhSachTaiSanId() {
        return Optional.ofNullable(danhSachTaiSanId);
    }

    public LongFilter danhSachTaiSanId() {
        if (danhSachTaiSanId == null) {
            setDanhSachTaiSanId(new LongFilter());
        }
        return danhSachTaiSanId;
    }

    public void setDanhSachTaiSanId(LongFilter danhSachTaiSanId) {
        this.danhSachTaiSanId = danhSachTaiSanId;
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
        final DanhMucLoaiTaiSanCriteria that = (DanhMucLoaiTaiSanCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idLoaiTs, that.idLoaiTs) &&
            Objects.equals(dienGiai, that.dienGiai) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(searchStr, that.searchStr) &&
            Objects.equals(taiSanId, that.taiSanId) &&
            Objects.equals(danhSachTaiSanId, that.danhSachTaiSanId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idLoaiTs, dienGiai, trangThai, searchStr, taiSanId, danhSachTaiSanId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiTaiSanCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdLoaiTs().map(f -> "idLoaiTs=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalSearchStr().map(f -> "searchStr=" + f + ", ").orElse("") +
            optionalTaiSanId().map(f -> "taiSanId=" + f + ", ").orElse("") +
            optionalDanhSachTaiSanId().map(f -> "danhSachTaiSanId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
