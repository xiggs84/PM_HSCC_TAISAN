package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaisannhadatidCriteriaTest {

    @Test
    void newTaisannhadatidCriteriaHasAllFiltersNullTest() {
        var taisannhadatidCriteria = new TaisannhadatidCriteria();
        assertThat(taisannhadatidCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taisannhadatidCriteriaFluentMethodsCreatesFiltersTest() {
        var taisannhadatidCriteria = new TaisannhadatidCriteria();

        setAllFilters(taisannhadatidCriteria);

        assertThat(taisannhadatidCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taisannhadatidCriteriaCopyCreatesNullFilterTest() {
        var taisannhadatidCriteria = new TaisannhadatidCriteria();
        var copy = taisannhadatidCriteria.copy();

        assertThat(taisannhadatidCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taisannhadatidCriteria)
        );
    }

    @Test
    void taisannhadatidCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taisannhadatidCriteria = new TaisannhadatidCriteria();
        setAllFilters(taisannhadatidCriteria);

        var copy = taisannhadatidCriteria.copy();

        assertThat(taisannhadatidCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taisannhadatidCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taisannhadatidCriteria = new TaisannhadatidCriteria();

        assertThat(taisannhadatidCriteria).hasToString("TaisannhadatidCriteria{}");
    }

    private static void setAllFilters(TaisannhadatidCriteria taisannhadatidCriteria) {
        taisannhadatidCriteria.id();
        taisannhadatidCriteria.idTaiSan();
        taisannhadatidCriteria.thongTinTs();
        taisannhadatidCriteria.distinct();
    }

    private static Condition<TaisannhadatidCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdTaiSan()) &&
                condition.apply(criteria.getThongTinTs()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaisannhadatidCriteria> copyFiltersAre(
        TaisannhadatidCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdTaiSan(), copy.getIdTaiSan()) &&
                condition.apply(criteria.getThongTinTs(), copy.getThongTinTs()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
