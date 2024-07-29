package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaiSanDuongSu.
 */
@Entity
@Table(name = "tai_san_duong_su")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDuongSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "id_duong_su")
    private Long idDuongSu;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "id_loai_hop_dong")
    private Long idLoaiHopDong;

    @Column(name = "id_chung_thuc")
    private Long idChungThuc;

    @JsonIgnoreProperties(value = { "idTsGocs", "idLoaiTs", "idTinhTrang", "taiSans", "taiSanDuongSu" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private TaiSan idTaiSan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaiSanDuongSu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public TaiSanDuongSu trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getIdDuongSu() {
        return this.idDuongSu;
    }

    public TaiSanDuongSu idDuongSu(Long idDuongSu) {
        this.setIdDuongSu(idDuongSu);
        return this;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public TaiSanDuongSu ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getIdLoaiHopDong() {
        return this.idLoaiHopDong;
    }

    public TaiSanDuongSu idLoaiHopDong(Long idLoaiHopDong) {
        this.setIdLoaiHopDong(idLoaiHopDong);
        return this;
    }

    public void setIdLoaiHopDong(Long idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public Long getIdChungThuc() {
        return this.idChungThuc;
    }

    public TaiSanDuongSu idChungThuc(Long idChungThuc) {
        this.setIdChungThuc(idChungThuc);
        return this;
    }

    public void setIdChungThuc(Long idChungThuc) {
        this.idChungThuc = idChungThuc;
    }

    public TaiSan getIdTaiSan() {
        return this.idTaiSan;
    }

    public void setIdTaiSan(TaiSan taiSan) {
        this.idTaiSan = taiSan;
    }

    public TaiSanDuongSu idTaiSan(TaiSan taiSan) {
        this.setIdTaiSan(taiSan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiSanDuongSu)) {
            return false;
        }
        return getId() != null && getId().equals(((TaiSanDuongSu) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDuongSu{" +
            "id=" + getId() +
            ", trangThai=" + getTrangThai() +
            ", idDuongSu=" + getIdDuongSu() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", idLoaiHopDong=" + getIdLoaiHopDong() +
            ", idChungThuc=" + getIdChungThuc() +
            "}";
    }
}
