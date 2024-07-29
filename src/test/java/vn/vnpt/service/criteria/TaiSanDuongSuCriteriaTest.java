package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaiSanDuongSuCriteriaTest {

    @Test
    void newTaiSanDuongSuCriteriaHasAllFiltersNullTest() {
        var taiSanDuongSuCriteria = new TaiSanDuongSuCriteria();
        assertThat(taiSanDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taiSanDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var taiSanDuongSuCriteria = new TaiSanDuongSuCriteria();

        setAllFilters(taiSanDuongSuCriteria);

        assertThat(taiSanDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taiSanDuongSuCriteriaCopyCreatesNullFilterTest() {
        var taiSanDuongSuCriteria = new TaiSanDuongSuCriteria();
        var copy = taiSanDuongSuCriteria.copy();

        assertThat(taiSanDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanDuongSuCriteria)
        );
    }

    @Test
    void taiSanDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taiSanDuongSuCriteria = new TaiSanDuongSuCriteria();
        setAllFilters(taiSanDuongSuCriteria);

        var copy = taiSanDuongSuCriteria.copy();

        assertThat(taiSanDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taiSanDuongSuCriteria = new TaiSanDuongSuCriteria();

        assertThat(taiSanDuongSuCriteria).hasToString("TaiSanDuongSuCriteria{}");
    }

    private static void setAllFilters(TaiSanDuongSuCriteria taiSanDuongSuCriteria) {
        taiSanDuongSuCriteria.id();
        taiSanDuongSuCriteria.trangThai();
        taiSanDuongSuCriteria.idDuongSu();
        taiSanDuongSuCriteria.ngayThaoTac();
        taiSanDuongSuCriteria.idLoaiHopDong();
        taiSanDuongSuCriteria.idChungThuc();
        taiSanDuongSuCriteria.idTaiSanId();
        taiSanDuongSuCriteria.distinct();
    }

    private static Condition<TaiSanDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getIdDuongSu()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getIdLoaiHopDong()) &&
                condition.apply(criteria.getIdChungThuc()) &&
                condition.apply(criteria.getIdTaiSanId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaiSanDuongSuCriteria> copyFiltersAre(
        TaiSanDuongSuCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getIdDuongSu(), copy.getIdDuongSu()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getIdLoaiHopDong(), copy.getIdLoaiHopDong()) &&
                condition.apply(criteria.getIdChungThuc(), copy.getIdChungThuc()) &&
                condition.apply(criteria.getIdTaiSanId(), copy.getIdTaiSanId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
