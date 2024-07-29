package vn.vnpt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaiSan;

/**
 * Spring Data JPA repository for the TaiSan entity.
 *
 * When extending this class, extend TaiSanRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface TaiSanRepository
    extends TaiSanRepositoryWithBagRelationships, JpaRepository<TaiSan, Long>, JpaSpecificationExecutor<TaiSan> {
    default Optional<TaiSan> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<TaiSan> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<TaiSan> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
