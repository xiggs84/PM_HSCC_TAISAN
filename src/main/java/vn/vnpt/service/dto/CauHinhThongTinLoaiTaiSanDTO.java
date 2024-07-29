package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.CauHinhThongTinLoaiTaiSan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhThongTinLoaiTaiSanDTO implements Serializable {

    private Long id;

    private Long idCauHinh;

    private String noiDung;

    private String javascript;

    private String css;

    private Long idLoaiTs;

    private Long idDonVi;

    private Long trangThai;

    private String xml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCauHinh() {
        return idCauHinh;
    }

    public void setIdCauHinh(Long idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getJavascript() {
        return javascript;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public Long getIdLoaiTs() {
        return idLoaiTs;
    }

    public void setIdLoaiTs(Long idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhThongTinLoaiTaiSanDTO)) {
            return false;
        }

        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO = (CauHinhThongTinLoaiTaiSanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cauHinhThongTinLoaiTaiSanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhThongTinLoaiTaiSanDTO{" +
            "id=" + getId() +
            ", idCauHinh=" + getIdCauHinh() +
            ", noiDung='" + getNoiDung() + "'" +
            ", javascript='" + getJavascript() + "'" +
            ", css='" + getCss() + "'" +
            ", idLoaiTs=" + getIdLoaiTs() +
            ", idDonVi=" + getIdDonVi() +
            ", trangThai=" + getTrangThai() +
            ", xml='" + getXml() + "'" +
            "}";
    }
}
