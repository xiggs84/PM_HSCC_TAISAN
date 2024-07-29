package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaisanSaiDgc.
 */
@Entity
@Table(name = "taisan_sai_dgc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisanSaiDgc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_master")
    private Long idMaster;

    @Column(name = "thong_tin_ts")
    private String thongTinTs;

    @Column(name = "thong_tin_ts_dung")
    private String thongTinTsDung;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaisanSaiDgc id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public TaisanSaiDgc idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getThongTinTs() {
        return this.thongTinTs;
    }

    public TaisanSaiDgc thongTinTs(String thongTinTs) {
        this.setThongTinTs(thongTinTs);
        return this;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    public String getThongTinTsDung() {
        return this.thongTinTsDung;
    }

    public TaisanSaiDgc thongTinTsDung(String thongTinTsDung) {
        this.setThongTinTsDung(thongTinTsDung);
        return this;
    }

    public void setThongTinTsDung(String thongTinTsDung) {
        this.thongTinTsDung = thongTinTsDung;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaisanSaiDgc)) {
            return false;
        }
        return getId() != null && getId().equals(((TaisanSaiDgc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisanSaiDgc{" +
            "id=" + getId() +
            ", idMaster=" + getIdMaster() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            ", thongTinTsDung='" + getThongTinTsDung() + "'" +
            "}";
    }
}
