package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.ThuaTach} entity. This class is used
 * in {@link vn.vnpt.web.rest.ThuaTachResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /thua-taches?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThuaTachCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idThuaTach;

    private LongFilter idTaiSan;

    private StringFilter thongTinThuaTach;

    private LongFilter trangThai;

    private Boolean distinct;

    public ThuaTachCriteria() {}

    public ThuaTachCriteria(ThuaTachCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idThuaTach = other.optionalIdThuaTach().map(LongFilter::copy).orElse(null);
        this.idTaiSan = other.optionalIdTaiSan().map(LongFilter::copy).orElse(null);
        this.thongTinThuaTach = other.optionalThongTinThuaTach().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public ThuaTachCriteria copy() {
        return new ThuaTachCriteria(this);
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

    public LongFilter getIdThuaTach() {
        return idThuaTach;
    }

    public Optional<LongFilter> optionalIdThuaTach() {
        return Optional.ofNullable(idThuaTach);
    }

    public LongFilter idThuaTach() {
        if (idThuaTach == null) {
            setIdThuaTach(new LongFilter());
        }
        return idThuaTach;
    }

    public void setIdThuaTach(LongFilter idThuaTach) {
        this.idThuaTach = idThuaTach;
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

    public StringFilter getThongTinThuaTach() {
        return thongTinThuaTach;
    }

    public Optional<StringFilter> optionalThongTinThuaTach() {
        return Optional.ofNullable(thongTinThuaTach);
    }

    public StringFilter thongTinThuaTach() {
        if (thongTinThuaTach == null) {
            setThongTinThuaTach(new StringFilter());
        }
        return thongTinThuaTach;
    }

    public void setThongTinThuaTach(StringFilter thongTinThuaTach) {
        this.thongTinThuaTach = thongTinThuaTach;
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
        final ThuaTachCriteria that = (ThuaTachCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idThuaTach, that.idThuaTach) &&
            Objects.equals(idTaiSan, that.idTaiSan) &&
            Objects.equals(thongTinThuaTach, that.thongTinThuaTach) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idThuaTach, idTaiSan, thongTinThuaTach, trangThai, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThuaTachCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdThuaTach().map(f -> "idThuaTach=" + f + ", ").orElse("") +
            optionalIdTaiSan().map(f -> "idTaiSan=" + f + ", ").orElse("") +
            optionalThongTinThuaTach().map(f -> "thongTinThuaTach=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
