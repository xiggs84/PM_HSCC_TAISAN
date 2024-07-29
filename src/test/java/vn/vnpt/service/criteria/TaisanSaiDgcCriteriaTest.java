package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaisanSaiDgcCriteriaTest {

    @Test
    void newTaisanSaiDgcCriteriaHasAllFiltersNullTest() {
        var taisanSaiDgcCriteria = new TaisanSaiDgcCriteria();
        assertThat(taisanSaiDgcCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taisanSaiDgcCriteriaFluentMethodsCreatesFiltersTest() {
        var taisanSaiDgcCriteria = new TaisanSaiDgcCriteria();

        setAllFilters(taisanSaiDgcCriteria);

        assertThat(taisanSaiDgcCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taisanSaiDgcCriteriaCopyCreatesNullFilterTest() {
        var taisanSaiDgcCriteria = new TaisanSaiDgcCriteria();
        var copy = taisanSaiDgcCriteria.copy();

        assertThat(taisanSaiDgcCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taisanSaiDgcCriteria)
        );
    }

    @Test
    void taisanSaiDgcCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taisanSaiDgcCriteria = new TaisanSaiDgcCriteria();
        setAllFilters(taisanSaiDgcCriteria);

        var copy = taisanSaiDgcCriteria.copy();

        assertThat(taisanSaiDgcCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taisanSaiDgcCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taisanSaiDgcCriteria = new TaisanSaiDgcCriteria();

        assertThat(taisanSaiDgcCriteria).hasToString("TaisanSaiDgcCriteria{}");
    }

    private static void setAllFilters(TaisanSaiDgcCriteria taisanSaiDgcCriteria) {
        taisanSaiDgcCriteria.id();
        taisanSaiDgcCriteria.idMaster();
        taisanSaiDgcCriteria.thongTinTs();
        taisanSaiDgcCriteria.thongTinTsDung();
        taisanSaiDgcCriteria.distinct();
    }

    private static Condition<TaisanSaiDgcCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdMaster()) &&
                condition.apply(criteria.getThongTinTs()) &&
                condition.apply(criteria.getThongTinTsDung()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaisanSaiDgcCriteria> copyFiltersAre(
        TaisanSaiDgcCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdMaster(), copy.getIdMaster()) &&
                condition.apply(criteria.getThongTinTs(), copy.getThongTinTs()) &&
                condition.apply(criteria.getThongTinTsDung(), copy.getThongTinTsDung()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
