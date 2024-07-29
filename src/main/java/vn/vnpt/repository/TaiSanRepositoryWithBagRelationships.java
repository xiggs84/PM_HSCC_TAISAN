package vn.vnpt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import vn.vnpt.domain.TaiSan;

public interface TaiSanRepositoryWithBagRelationships {
    Optional<TaiSan> fetchBagRelationships(Optional<TaiSan> taiSan);

    List<TaiSan> fetchBagRelationships(List<TaiSan> taiSans);

    Page<TaiSan> fetchBagRelationships(Page<TaiSan> taiSans);
}
