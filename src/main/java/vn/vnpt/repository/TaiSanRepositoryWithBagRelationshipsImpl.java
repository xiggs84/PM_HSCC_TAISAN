package vn.vnpt.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.vnpt.domain.TaiSan;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class TaiSanRepositoryWithBagRelationshipsImpl implements TaiSanRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String TAISANS_PARAMETER = "taiSans";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TaiSan> fetchBagRelationships(Optional<TaiSan> taiSan) {
        return taiSan.map(this::fetchIdTsGocs);
    }

    @Override
    public Page<TaiSan> fetchBagRelationships(Page<TaiSan> taiSans) {
        return new PageImpl<>(fetchBagRelationships(taiSans.getContent()), taiSans.getPageable(), taiSans.getTotalElements());
    }

    @Override
    public List<TaiSan> fetchBagRelationships(List<TaiSan> taiSans) {
        return Optional.of(taiSans).map(this::fetchIdTsGocs).orElse(Collections.emptyList());
    }

    TaiSan fetchIdTsGocs(TaiSan result) {
        return entityManager
            .createQuery("select taiSan from TaiSan taiSan left join fetch taiSan.idTsGocs where taiSan.id = :id", TaiSan.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<TaiSan> fetchIdTsGocs(List<TaiSan> taiSans) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, taiSans.size()).forEach(index -> order.put(taiSans.get(index).getId(), index));
        List<TaiSan> result = entityManager
            .createQuery("select taiSan from TaiSan taiSan left join fetch taiSan.idTsGocs where taiSan in :taiSans", TaiSan.class)
            .setParameter(TAISANS_PARAMETER, taiSans)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
