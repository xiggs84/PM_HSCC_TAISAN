package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ThuaTach;

/**
 * Spring Data JPA repository for the ThuaTach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThuaTachRepository extends JpaRepository<ThuaTach, Long>, JpaSpecificationExecutor<ThuaTach> {}
