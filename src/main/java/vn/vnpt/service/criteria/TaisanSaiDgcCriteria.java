package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.TaisanSaiDgc} entity. This class is used
 * in {@link vn.vnpt.web.rest.TaisanSaiDgcResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /taisan-sai-dgcs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisanSaiDgcCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idMaster;

    private StringFilter thongTinTs;

    private StringFilter thongTinTsDung;

    private Boolean distinct;

    public TaisanSaiDgcCriteria() {}

    public TaisanSaiDgcCriteria(TaisanSaiDgcCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idMaster = other.optionalIdMaster().map(LongFilter::copy).orElse(null);
        this.thongTinTs = other.optionalThongTinTs().map(StringFilter::copy).orElse(null);
        this.thongTinTsDung = other.optionalThongTinTsDung().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TaisanSaiDgcCriteria copy() {
        return new TaisanSaiDgcCriteria(this);
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

    public StringFilter getThongTinTs() {
        return thongTinTs;
    }

    public Optional<StringFilter> optionalThongTinTs() {
        return Optional.ofNullable(thongTinTs);
    }

    public StringFilter thongTinTs() {
        if (thongTinTs == null) {
            setThongTinTs(new StringFilter());
        }
        return thongTinTs;
    }

    public void setThongTinTs(StringFilter thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    public StringFilter getThongTinTsDung() {
        return thongTinTsDung;
    }

    public Optional<StringFilter> optionalThongTinTsDung() {
        return Optional.ofNullable(thongTinTsDung);
    }

    public StringFilter thongTinTsDung() {
        if (thongTinTsDung == null) {
            setThongTinTsDung(new StringFilter());
        }
        return thongTinTsDung;
    }

    public void setThongTinTsDung(StringFilter thongTinTsDung) {
        this.thongTinTsDung = thongTinTsDung;
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
        final TaisanSaiDgcCriteria that = (TaisanSaiDgcCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idMaster, that.idMaster) &&
            Objects.equals(thongTinTs, that.thongTinTs) &&
            Objects.equals(thongTinTsDung, that.thongTinTsDung) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMaster, thongTinTs, thongTinTsDung, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisanSaiDgcCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdMaster().map(f -> "idMaster=" + f + ", ").orElse("") +
            optionalThongTinTs().map(f -> "thongTinTs=" + f + ", ").orElse("") +
            optionalThongTinTsDung().map(f -> "thongTinTsDung=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
