package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class SoHuuTheoCriteriaTest {

    @Test
    void newSoHuuTheoCriteriaHasAllFiltersNullTest() {
        var soHuuTheoCriteria = new SoHuuTheoCriteria();
        assertThat(soHuuTheoCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void soHuuTheoCriteriaFluentMethodsCreatesFiltersTest() {
        var soHuuTheoCriteria = new SoHuuTheoCriteria();

        setAllFilters(soHuuTheoCriteria);

        assertThat(soHuuTheoCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void soHuuTheoCriteriaCopyCreatesNullFilterTest() {
        var soHuuTheoCriteria = new SoHuuTheoCriteria();
        var copy = soHuuTheoCriteria.copy();

        assertThat(soHuuTheoCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(soHuuTheoCriteria)
        );
    }

    @Test
    void soHuuTheoCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var soHuuTheoCriteria = new SoHuuTheoCriteria();
        setAllFilters(soHuuTheoCriteria);

        var copy = soHuuTheoCriteria.copy();

        assertThat(soHuuTheoCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(soHuuTheoCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var soHuuTheoCriteria = new SoHuuTheoCriteria();

        assertThat(soHuuTheoCriteria).hasToString("SoHuuTheoCriteria{}");
    }

    private static void setAllFilters(SoHuuTheoCriteria soHuuTheoCriteria) {
        soHuuTheoCriteria.id();
        soHuuTheoCriteria.idSoHuu();
        soHuuTheoCriteria.dienGiai();
        soHuuTheoCriteria.tenGcn();
        soHuuTheoCriteria.distinct();
    }

    private static Condition<SoHuuTheoCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdSoHuu()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getTenGcn()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<SoHuuTheoCriteria> copyFiltersAre(SoHuuTheoCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdSoHuu(), copy.getIdSoHuu()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getTenGcn(), copy.getTenGcn()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
