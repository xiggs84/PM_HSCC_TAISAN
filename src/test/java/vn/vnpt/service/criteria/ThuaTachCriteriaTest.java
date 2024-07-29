package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class ThuaTachCriteriaTest {

    @Test
    void newThuaTachCriteriaHasAllFiltersNullTest() {
        var thuaTachCriteria = new ThuaTachCriteria();
        assertThat(thuaTachCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void thuaTachCriteriaFluentMethodsCreatesFiltersTest() {
        var thuaTachCriteria = new ThuaTachCriteria();

        setAllFilters(thuaTachCriteria);

        assertThat(thuaTachCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void thuaTachCriteriaCopyCreatesNullFilterTest() {
        var thuaTachCriteria = new ThuaTachCriteria();
        var copy = thuaTachCriteria.copy();

        assertThat(thuaTachCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(thuaTachCriteria)
        );
    }

    @Test
    void thuaTachCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var thuaTachCriteria = new ThuaTachCriteria();
        setAllFilters(thuaTachCriteria);

        var copy = thuaTachCriteria.copy();

        assertThat(thuaTachCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(thuaTachCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var thuaTachCriteria = new ThuaTachCriteria();

        assertThat(thuaTachCriteria).hasToString("ThuaTachCriteria{}");
    }

    private static void setAllFilters(ThuaTachCriteria thuaTachCriteria) {
        thuaTachCriteria.id();
        thuaTachCriteria.idThuaTach();
        thuaTachCriteria.idTaiSan();
        thuaTachCriteria.thongTinThuaTach();
        thuaTachCriteria.trangThai();
        thuaTachCriteria.distinct();
    }

    private static Condition<ThuaTachCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdThuaTach()) &&
                condition.apply(criteria.getIdTaiSan()) &&
                condition.apply(criteria.getThongTinThuaTach()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<ThuaTachCriteria> copyFiltersAre(ThuaTachCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdThuaTach(), copy.getIdThuaTach()) &&
                condition.apply(criteria.getIdTaiSan(), copy.getIdTaiSan()) &&
                condition.apply(criteria.getThongTinThuaTach(), copy.getThongTinThuaTach()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
