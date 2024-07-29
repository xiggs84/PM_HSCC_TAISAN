package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.Taisannhadatid} entity. This class is used
 * in {@link vn.vnpt.web.rest.TaisannhadatidResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /taisannhadatids?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisannhadatidCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idTaiSan;

    private StringFilter thongTinTs;

    private Boolean distinct;

    public TaisannhadatidCriteria() {}

    public TaisannhadatidCriteria(TaisannhadatidCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idTaiSan = other.optionalIdTaiSan().map(LongFilter::copy).orElse(null);
        this.thongTinTs = other.optionalThongTinTs().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TaisannhadatidCriteria copy() {
        return new TaisannhadatidCriteria(this);
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
        final TaisannhadatidCriteria that = (TaisannhadatidCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idTaiSan, that.idTaiSan) &&
            Objects.equals(thongTinTs, that.thongTinTs) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTaiSan, thongTinTs, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisannhadatidCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdTaiSan().map(f -> "idTaiSan=" + f + ", ").orElse("") +
            optionalThongTinTs().map(f -> "thongTinTs=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
