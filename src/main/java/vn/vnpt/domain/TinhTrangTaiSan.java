package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TinhTrangTaiSan.
 */
@Entity
@Table(name = "tinh_trang_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TinhTrangTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_tinh_trang")
    private Long idTinhTrang;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "trang_thai")
    private Long trangThai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idTinhTrang")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idTsGocs", "idLoaiTs", "idTinhTrang", "taiSans", "taiSanDuongSu" }, allowSetters = true)
    private Set<TaiSan> taiSans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TinhTrangTaiSan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTinhTrang() {
        return this.idTinhTrang;
    }

    public TinhTrangTaiSan idTinhTrang(Long idTinhTrang) {
        this.setIdTinhTrang(idTinhTrang);
        return this;
    }

    public void setIdTinhTrang(Long idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public TinhTrangTaiSan dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public TinhTrangTaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Set<TaiSan> getTaiSans() {
        return this.taiSans;
    }

    public void setTaiSans(Set<TaiSan> taiSans) {
        if (this.taiSans != null) {
            this.taiSans.forEach(i -> i.setIdTinhTrang(null));
        }
        if (taiSans != null) {
            taiSans.forEach(i -> i.setIdTinhTrang(this));
        }
        this.taiSans = taiSans;
    }

    public TinhTrangTaiSan taiSans(Set<TaiSan> taiSans) {
        this.setTaiSans(taiSans);
        return this;
    }

    public TinhTrangTaiSan addTaiSan(TaiSan taiSan) {
        this.taiSans.add(taiSan);
        taiSan.setIdTinhTrang(this);
        return this;
    }

    public TinhTrangTaiSan removeTaiSan(TaiSan taiSan) {
        this.taiSans.remove(taiSan);
        taiSan.setIdTinhTrang(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TinhTrangTaiSan)) {
            return false;
        }
        return getId() != null && getId().equals(((TinhTrangTaiSan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TinhTrangTaiSan{" +
            "id=" + getId() +
            ", idTinhTrang=" + getIdTinhTrang() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
