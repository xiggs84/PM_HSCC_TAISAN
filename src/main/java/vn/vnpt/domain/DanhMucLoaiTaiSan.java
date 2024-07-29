package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucLoaiTaiSan.
 */
@Entity
@Table(name = "danh_muc_loai_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_loai_ts")
    private Long idLoaiTs;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "search_str")
    private String searchStr;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idLoaiTs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idTsGocs", "idLoaiTs", "idTinhTrang", "taiSans", "taiSanDuongSu" }, allowSetters = true)
    private Set<TaiSan> taiSans = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idLoaiTs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idLoaiTs" }, allowSetters = true)
    private Set<DanhSachTaiSan> danhSachTaiSans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhMucLoaiTaiSan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiTs() {
        return this.idLoaiTs;
    }

    public DanhMucLoaiTaiSan idLoaiTs(Long idLoaiTs) {
        this.setIdLoaiTs(idLoaiTs);
        return this;
    }

    public void setIdLoaiTs(Long idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiTaiSan dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucLoaiTaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getSearchStr() {
        return this.searchStr;
    }

    public DanhMucLoaiTaiSan searchStr(String searchStr) {
        this.setSearchStr(searchStr);
        return this;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Set<TaiSan> getTaiSans() {
        return this.taiSans;
    }

    public void setTaiSans(Set<TaiSan> taiSans) {
        if (this.taiSans != null) {
            this.taiSans.forEach(i -> i.setIdLoaiTs(null));
        }
        if (taiSans != null) {
            taiSans.forEach(i -> i.setIdLoaiTs(this));
        }
        this.taiSans = taiSans;
    }

    public DanhMucLoaiTaiSan taiSans(Set<TaiSan> taiSans) {
        this.setTaiSans(taiSans);
        return this;
    }

    public DanhMucLoaiTaiSan addTaiSan(TaiSan taiSan) {
        this.taiSans.add(taiSan);
        taiSan.setIdLoaiTs(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeTaiSan(TaiSan taiSan) {
        this.taiSans.remove(taiSan);
        taiSan.setIdLoaiTs(null);
        return this;
    }

    public Set<DanhSachTaiSan> getDanhSachTaiSans() {
        return this.danhSachTaiSans;
    }

    public void setDanhSachTaiSans(Set<DanhSachTaiSan> danhSachTaiSans) {
        if (this.danhSachTaiSans != null) {
            this.danhSachTaiSans.forEach(i -> i.setIdLoaiTs(null));
        }
        if (danhSachTaiSans != null) {
            danhSachTaiSans.forEach(i -> i.setIdLoaiTs(this));
        }
        this.danhSachTaiSans = danhSachTaiSans;
    }

    public DanhMucLoaiTaiSan danhSachTaiSans(Set<DanhSachTaiSan> danhSachTaiSans) {
        this.setDanhSachTaiSans(danhSachTaiSans);
        return this;
    }

    public DanhMucLoaiTaiSan addDanhSachTaiSan(DanhSachTaiSan danhSachTaiSan) {
        this.danhSachTaiSans.add(danhSachTaiSan);
        danhSachTaiSan.setIdLoaiTs(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeDanhSachTaiSan(DanhSachTaiSan danhSachTaiSan) {
        this.danhSachTaiSans.remove(danhSachTaiSan);
        danhSachTaiSan.setIdLoaiTs(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiTaiSan)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiTaiSan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiTaiSan{" +
            "id=" + getId() +
            ", idLoaiTs=" + getIdLoaiTs() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            ", searchStr='" + getSearchStr() + "'" +
            "}";
    }
}
