package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.TinhTrangTaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.TinhTrangTaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tinh-trang-tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TinhTrangTaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idTinhTrang;

    private StringFilter dienGiai;

    private LongFilter trangThai;

    private LongFilter taiSanId;

    private Boolean distinct;

    public TinhTrangTaiSanCriteria() {}

    public TinhTrangTaiSanCriteria(TinhTrangTaiSanCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idTinhTrang = other.optionalIdTinhTrang().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.taiSanId = other.optionalTaiSanId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TinhTrangTaiSanCriteria copy() {
        return new TinhTrangTaiSanCriteria(this);
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
        final TinhTrangTaiSanCriteria that = (TinhTrangTaiSanCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idTinhTrang, that.idTinhTrang) &&
            Objects.equals(dienGiai, that.dienGiai) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(taiSanId, that.taiSanId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTinhTrang, dienGiai, trangThai, taiSanId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TinhTrangTaiSanCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdTinhTrang().map(f -> "idTinhTrang=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalTaiSanId().map(f -> "taiSanId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
