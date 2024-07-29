package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaisanSaiQsddDgc.
 */
@Entity
@Table(name = "taisan_sai_qsdd_dgc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaisanSaiQsddDgc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_master")
    private Long idMaster;

    @Column(name = "noi_cap_qsdd")
    private String noiCapQsdd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaisanSaiQsddDgc id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public TaisanSaiQsddDgc idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getNoiCapQsdd() {
        return this.noiCapQsdd;
    }

    public TaisanSaiQsddDgc noiCapQsdd(String noiCapQsdd) {
        this.setNoiCapQsdd(noiCapQsdd);
        return this;
    }

    public void setNoiCapQsdd(String noiCapQsdd) {
        this.noiCapQsdd = noiCapQsdd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaisanSaiQsddDgc)) {
            return false;
        }
        return getId() != null && getId().equals(((TaisanSaiQsddDgc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaisanSaiQsddDgc{" +
            "id=" + getId() +
            ", idMaster=" + getIdMaster() +
            ", noiCapQsdd='" + getNoiCapQsdd() + "'" +
            "}";
    }
}
