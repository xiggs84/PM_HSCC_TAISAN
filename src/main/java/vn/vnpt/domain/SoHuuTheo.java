package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SoHuuTheo.
 */
@Entity
@Table(name = "so_huu_theo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoHuuTheo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_so_huu")
    private Long idSoHuu;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "ten_gcn")
    private String tenGcn;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SoHuuTheo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSoHuu() {
        return this.idSoHuu;
    }

    public SoHuuTheo idSoHuu(Long idSoHuu) {
        this.setIdSoHuu(idSoHuu);
        return this;
    }

    public void setIdSoHuu(Long idSoHuu) {
        this.idSoHuu = idSoHuu;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public SoHuuTheo dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getTenGcn() {
        return this.tenGcn;
    }

    public SoHuuTheo tenGcn(String tenGcn) {
        this.setTenGcn(tenGcn);
        return this;
    }

    public void setTenGcn(String tenGcn) {
        this.tenGcn = tenGcn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoHuuTheo)) {
            return false;
        }
        return getId() != null && getId().equals(((SoHuuTheo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoHuuTheo{" +
            "id=" + getId() +
            ", idSoHuu=" + getIdSoHuu() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", tenGcn='" + getTenGcn() + "'" +
            "}";
    }
}
