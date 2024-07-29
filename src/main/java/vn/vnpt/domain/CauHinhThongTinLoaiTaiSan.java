package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CauHinhThongTinLoaiTaiSan.
 */
@Entity
@Table(name = "cau_hinh_thong_tin_loai_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhThongTinLoaiTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_cau_hinh")
    private Long idCauHinh;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "javascript")
    private String javascript;

    @Column(name = "css")
    private String css;

    @Column(name = "id_loai_ts")
    private Long idLoaiTs;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "xml")
    private String xml;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CauHinhThongTinLoaiTaiSan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCauHinh() {
        return this.idCauHinh;
    }

    public CauHinhThongTinLoaiTaiSan idCauHinh(Long idCauHinh) {
        this.setIdCauHinh(idCauHinh);
        return this;
    }

    public void setIdCauHinh(Long idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public CauHinhThongTinLoaiTaiSan noiDung(String noiDung) {
        this.setNoiDung(noiDung);
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getJavascript() {
        return this.javascript;
    }

    public CauHinhThongTinLoaiTaiSan javascript(String javascript) {
        this.setJavascript(javascript);
        return this;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public String getCss() {
        return this.css;
    }

    public CauHinhThongTinLoaiTaiSan css(String css) {
        this.setCss(css);
        return this;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public Long getIdLoaiTs() {
        return this.idLoaiTs;
    }

    public CauHinhThongTinLoaiTaiSan idLoaiTs(Long idLoaiTs) {
        this.setIdLoaiTs(idLoaiTs);
        return this;
    }

    public void setIdLoaiTs(Long idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public CauHinhThongTinLoaiTaiSan idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public CauHinhThongTinLoaiTaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getXml() {
        return this.xml;
    }

    public CauHinhThongTinLoaiTaiSan xml(String xml) {
        this.setXml(xml);
        return this;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhThongTinLoaiTaiSan)) {
            return false;
        }
        return getId() != null && getId().equals(((CauHinhThongTinLoaiTaiSan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhThongTinLoaiTaiSan{" +
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
