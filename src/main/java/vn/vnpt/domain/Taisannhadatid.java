package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Taisannhadatid.
 */
@Entity
@Table(name = "taisannhadatid")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Taisannhadatid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_tai_san")
    private Long idTaiSan;

    @Column(name = "thong_tin_ts")
    private String thongTinTs;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Taisannhadatid id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaiSan() {
        return this.idTaiSan;
    }

    public Taisannhadatid idTaiSan(Long idTaiSan) {
        this.setIdTaiSan(idTaiSan);
        return this;
    }

    public void setIdTaiSan(Long idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public String getThongTinTs() {
        return this.thongTinTs;
    }

    public Taisannhadatid thongTinTs(String thongTinTs) {
        this.setThongTinTs(thongTinTs);
        return this;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Taisannhadatid)) {
            return false;
        }
        return getId() != null && getId().equals(((Taisannhadatid) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Taisannhadatid{" +
            "id=" + getId() +
            ", idTaiSan=" + getIdTaiSan() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            "}";
    }
}
