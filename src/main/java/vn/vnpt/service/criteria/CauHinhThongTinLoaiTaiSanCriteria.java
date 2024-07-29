package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.CauHinhThongTinLoaiTaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.CauHinhThongTinLoaiTaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cau-hinh-thong-tin-loai-tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhThongTinLoaiTaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idCauHinh;

    private StringFilter noiDung;

    private StringFilter javascript;

    private StringFilter css;

    private LongFilter idLoaiTs;

    private LongFilter idDonVi;

    private LongFilter trangThai;

    private StringFilter xml;

    private Boolean distinct;

    public CauHinhThongTinLoaiTaiSanCriteria() {}

    public CauHinhThongTinLoaiTaiSanCriteria(CauHinhThongTinLoaiTaiSanCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idCauHinh = other.optionalIdCauHinh().map(LongFilter::copy).orElse(null);
        this.noiDung = other.optionalNoiDung().map(StringFilter::copy).orElse(null);
        this.javascript = other.optionalJavascript().map(StringFilter::copy).orElse(null);
        this.css = other.optionalCss().map(StringFilter::copy).orElse(null);
        this.idLoaiTs = other.optionalIdLoaiTs().map(LongFilter::copy).orElse(null);
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.xml = other.optionalXml().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CauHinhThongTinLoaiTaiSanCriteria copy() {
        return new CauHinhThongTinLoaiTaiSanCriteria(this);
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

    public LongFilter getIdCauHinh() {
        return idCauHinh;
    }

    public Optional<LongFilter> optionalIdCauHinh() {
        return Optional.ofNullable(idCauHinh);
    }

    public LongFilter idCauHinh() {
        if (idCauHinh == null) {
            setIdCauHinh(new LongFilter());
        }
        return idCauHinh;
    }

    public void setIdCauHinh(LongFilter idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public StringFilter getNoiDung() {
        return noiDung;
    }

    public Optional<StringFilter> optionalNoiDung() {
        return Optional.ofNullable(noiDung);
    }

    public StringFilter noiDung() {
        if (noiDung == null) {
            setNoiDung(new StringFilter());
        }
        return noiDung;
    }

    public void setNoiDung(StringFilter noiDung) {
        this.noiDung = noiDung;
    }

    public StringFilter getJavascript() {
        return javascript;
    }

    public Optional<StringFilter> optionalJavascript() {
        return Optional.ofNullable(javascript);
    }

    public StringFilter javascript() {
        if (javascript == null) {
            setJavascript(new StringFilter());
        }
        return javascript;
    }

    public void setJavascript(StringFilter javascript) {
        this.javascript = javascript;
    }

    public StringFilter getCss() {
        return css;
    }

    public Optional<StringFilter> optionalCss() {
        return Optional.ofNullable(css);
    }

    public StringFilter css() {
        if (css == null) {
            setCss(new StringFilter());
        }
        return css;
    }

    public void setCss(StringFilter css) {
        this.css = css;
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

    public StringFilter getXml() {
        return xml;
    }

    public Optional<StringFilter> optionalXml() {
        return Optional.ofNullable(xml);
    }

    public StringFilter xml() {
        if (xml == null) {
            setXml(new StringFilter());
        }
        return xml;
    }

    public void setXml(StringFilter xml) {
        this.xml = xml;
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
        final CauHinhThongTinLoaiTaiSanCriteria that = (CauHinhThongTinLoaiTaiSanCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idCauHinh, that.idCauHinh) &&
            Objects.equals(noiDung, that.noiDung) &&
            Objects.equals(javascript, that.javascript) &&
            Objects.equals(css, that.css) &&
            Objects.equals(idLoaiTs, that.idLoaiTs) &&
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(xml, that.xml) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCauHinh, noiDung, javascript, css, idLoaiTs, idDonVi, trangThai, xml, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhThongTinLoaiTaiSanCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdCauHinh().map(f -> "idCauHinh=" + f + ", ").orElse("") +
            optionalNoiDung().map(f -> "noiDung=" + f + ", ").orElse("") +
            optionalJavascript().map(f -> "javascript=" + f + ", ").orElse("") +
            optionalCss().map(f -> "css=" + f + ", ").orElse("") +
            optionalIdLoaiTs().map(f -> "idLoaiTs=" + f + ", ").orElse("") +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalXml().map(f -> "xml=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
