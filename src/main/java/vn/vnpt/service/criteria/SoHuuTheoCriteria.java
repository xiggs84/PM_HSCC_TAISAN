package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.SoHuuTheo} entity. This class is used
 * in {@link vn.vnpt.web.rest.SoHuuTheoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /so-huu-theos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoHuuTheoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idSoHuu;

    private StringFilter dienGiai;

    private StringFilter tenGcn;

    private Boolean distinct;

    public SoHuuTheoCriteria() {}

    public SoHuuTheoCriteria(SoHuuTheoCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idSoHuu = other.optionalIdSoHuu().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.tenGcn = other.optionalTenGcn().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public SoHuuTheoCriteria copy() {
        return new SoHuuTheoCriteria(this);
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

    public LongFilter getIdSoHuu() {
        return idSoHuu;
    }

    public Optional<LongFilter> optionalIdSoHuu() {
        return Optional.ofNullable(idSoHuu);
    }

    public LongFilter idSoHuu() {
        if (idSoHuu == null) {
            setIdSoHuu(new LongFilter());
        }
        return idSoHuu;
    }

    public void setIdSoHuu(LongFilter idSoHuu) {
        this.idSoHuu = idSoHuu;
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

    public StringFilter getTenGcn() {
        return tenGcn;
    }

    public Optional<StringFilter> optionalTenGcn() {
        return Optional.ofNullable(tenGcn);
    }

    public StringFilter tenGcn() {
        if (tenGcn == null) {
            setTenGcn(new StringFilter());
        }
        return tenGcn;
    }

    public void setTenGcn(StringFilter tenGcn) {
        this.tenGcn = tenGcn;
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
        final SoHuuTheoCriteria that = (SoHuuTheoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idSoHuu, that.idSoHuu) &&
            Objects.equals(dienGiai, that.dienGiai) &&
            Objects.equals(tenGcn, that.tenGcn) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idSoHuu, dienGiai, tenGcn, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoHuuTheoCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdSoHuu().map(f -> "idSoHuu=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalTenGcn().map(f -> "tenGcn=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
