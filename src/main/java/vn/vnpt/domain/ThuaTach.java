package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ThuaTach.
 */
@Entity
@Table(name = "thua_tach")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThuaTach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_thua_tach")
    private Long idThuaTach;

    @Column(name = "id_tai_san")
    private Long idTaiSan;

    @Column(name = "thong_tin_thua_tach")
    private String thongTinThuaTach;

    @Column(name = "trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ThuaTach id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdThuaTach() {
        return this.idThuaTach;
    }

    public ThuaTach idThuaTach(Long idThuaTach) {
        this.setIdThuaTach(idThuaTach);
        return this;
    }

    public void setIdThuaTach(Long idThuaTach) {
        this.idThuaTach = idThuaTach;
    }

    public Long getIdTaiSan() {
        return this.idTaiSan;
    }

    public ThuaTach idTaiSan(Long idTaiSan) {
        this.setIdTaiSan(idTaiSan);
        return this;
    }

    public void setIdTaiSan(Long idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public String getThongTinThuaTach() {
        return this.thongTinThuaTach;
    }

    public ThuaTach thongTinThuaTach(String thongTinThuaTach) {
        this.setThongTinThuaTach(thongTinThuaTach);
        return this;
    }

    public void setThongTinThuaTach(String thongTinThuaTach) {
        this.thongTinThuaTach = thongTinThuaTach;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public ThuaTach trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThuaTach)) {
            return false;
        }
        return getId() != null && getId().equals(((ThuaTach) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThuaTach{" +
            "id=" + getId() +
            ", idThuaTach=" + getIdThuaTach() +
            ", idTaiSan=" + getIdTaiSan() +
            ", thongTinThuaTach='" + getThongTinThuaTach() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
