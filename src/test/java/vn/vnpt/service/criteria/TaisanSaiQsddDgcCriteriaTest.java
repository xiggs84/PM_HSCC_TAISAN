package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaisanSaiQsddDgcCriteriaTest {

    @Test
    void newTaisanSaiQsddDgcCriteriaHasAllFiltersNullTest() {
        var taisanSaiQsddDgcCriteria = new TaisanSaiQsddDgcCriteria();
        assertThat(taisanSaiQsddDgcCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taisanSaiQsddDgcCriteriaFluentMethodsCreatesFiltersTest() {
        var taisanSaiQsddDgcCriteria = new TaisanSaiQsddDgcCriteria();

        setAllFilters(taisanSaiQsddDgcCriteria);

        assertThat(taisanSaiQsddDgcCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taisanSaiQsddDgcCriteriaCopyCreatesNullFilterTest() {
        var taisanSaiQsddDgcCriteria = new TaisanSaiQsddDgcCriteria();
        var copy = taisanSaiQsddDgcCriteria.copy();

        assertThat(taisanSaiQsddDgcCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taisanSaiQsddDgcCriteria)
        );
    }

    @Test
    void taisanSaiQsddDgcCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taisanSaiQsddDgcCriteria = new TaisanSaiQsddDgcCriteria();
        setAllFilters(taisanSaiQsddDgcCriteria);

        var copy = taisanSaiQsddDgcCriteria.copy();

        assertThat(taisanSaiQsddDgcCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taisanSaiQsddDgcCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taisanSaiQsddDgcCriteria = new TaisanSaiQsddDgcCriteria();

        assertThat(taisanSaiQsddDgcCriteria).hasToString("TaisanSaiQsddDgcCriteria{}");
    }

    private static void setAllFilters(TaisanSaiQsddDgcCriteria taisanSaiQsddDgcCriteria) {
        taisanSaiQsddDgcCriteria.id();
        taisanSaiQsddDgcCriteria.idMaster();
        taisanSaiQsddDgcCriteria.noiCapQsdd();
        taisanSaiQsddDgcCriteria.distinct();
    }

    private static Condition<TaisanSaiQsddDgcCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdMaster()) &&
                condition.apply(criteria.getNoiCapQsdd()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaisanSaiQsddDgcCriteria> copyFiltersAre(
        TaisanSaiQsddDgcCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdMaster(), copy.getIdMaster()) &&
                condition.apply(criteria.getNoiCapQsdd(), copy.getNoiCapQsdd()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
