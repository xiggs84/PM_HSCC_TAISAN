package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.TaisanSaiQsddDgc} entity. This class is used
 * in {@link vn.vnpt.web.rest.TaisanSaiQsddDgcResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /taisan-sai-qsdd-dgcs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisanSaiQsddDgcCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idMaster;

    private StringFilter noiCapQsdd;

    private Boolean distinct;

    public TaisanSaiQsddDgcCriteria() {}

    public TaisanSaiQsddDgcCriteria(TaisanSaiQsddDgcCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idMaster = other.optionalIdMaster().map(LongFilter::copy).orElse(null);
        this.noiCapQsdd = other.optionalNoiCapQsdd().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TaisanSaiQsddDgcCriteria copy() {
        return new TaisanSaiQsddDgcCriteria(this);
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

    public StringFilter getNoiCapQsdd() {
        return noiCapQsdd;
    }

    public Optional<StringFilter> optionalNoiCapQsdd() {
        return Optional.ofNullable(noiCapQsdd);
    }

    public StringFilter noiCapQsdd() {
        if (noiCapQsdd == null) {
            setNoiCapQsdd(new StringFilter());
        }
        return noiCapQsdd;
    }

    public void setNoiCapQsdd(StringFilter noiCapQsdd) {
        this.noiCapQsdd = noiCapQsdd;
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
        final TaisanSaiQsddDgcCriteria that = (TaisanSaiQsddDgcCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idMaster, that.idMaster) &&
            Objects.equals(noiCapQsdd, that.noiCapQsdd) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMaster, noiCapQsdd, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisanSaiQsddDgcCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdMaster().map(f -> "idMaster=" + f + ", ").orElse("") +
            optionalNoiCapQsdd().map(f -> "noiCapQsdd=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
