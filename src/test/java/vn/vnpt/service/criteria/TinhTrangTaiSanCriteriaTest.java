package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TinhTrangTaiSanCriteriaTest {

    @Test
    void newTinhTrangTaiSanCriteriaHasAllFiltersNullTest() {
        var tinhTrangTaiSanCriteria = new TinhTrangTaiSanCriteria();
        assertThat(tinhTrangTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void tinhTrangTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var tinhTrangTaiSanCriteria = new TinhTrangTaiSanCriteria();

        setAllFilters(tinhTrangTaiSanCriteria);

        assertThat(tinhTrangTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void tinhTrangTaiSanCriteriaCopyCreatesNullFilterTest() {
        var tinhTrangTaiSanCriteria = new TinhTrangTaiSanCriteria();
        var copy = tinhTrangTaiSanCriteria.copy();

        assertThat(tinhTrangTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(tinhTrangTaiSanCriteria)
        );
    }

    @Test
    void tinhTrangTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var tinhTrangTaiSanCriteria = new TinhTrangTaiSanCriteria();
        setAllFilters(tinhTrangTaiSanCriteria);

        var copy = tinhTrangTaiSanCriteria.copy();

        assertThat(tinhTrangTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(tinhTrangTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var tinhTrangTaiSanCriteria = new TinhTrangTaiSanCriteria();

        assertThat(tinhTrangTaiSanCriteria).hasToString("TinhTrangTaiSanCriteria{}");
    }

    private static void setAllFilters(TinhTrangTaiSanCriteria tinhTrangTaiSanCriteria) {
        tinhTrangTaiSanCriteria.id();
        tinhTrangTaiSanCriteria.idTinhTrang();
        tinhTrangTaiSanCriteria.dienGiai();
        tinhTrangTaiSanCriteria.trangThai();
        tinhTrangTaiSanCriteria.taiSanId();
        tinhTrangTaiSanCriteria.distinct();
    }

    private static Condition<TinhTrangTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdTinhTrang()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getTaiSanId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TinhTrangTaiSanCriteria> copyFiltersAre(
        TinhTrangTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdTinhTrang(), copy.getIdTinhTrang()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getTaiSanId(), copy.getTaiSanId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
