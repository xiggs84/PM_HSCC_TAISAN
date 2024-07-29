package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucLoaiTaiSanCriteriaTest {

    @Test
    void newDanhMucLoaiTaiSanCriteriaHasAllFiltersNullTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();
        assertThat(danhMucLoaiTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucLoaiTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();

        setAllFilters(danhMucLoaiTaiSanCriteria);

        assertThat(danhMucLoaiTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucLoaiTaiSanCriteriaCopyCreatesNullFilterTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();
        var copy = danhMucLoaiTaiSanCriteria.copy();

        assertThat(danhMucLoaiTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucLoaiTaiSanCriteria)
        );
    }

    @Test
    void danhMucLoaiTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();
        setAllFilters(danhMucLoaiTaiSanCriteria);

        var copy = danhMucLoaiTaiSanCriteria.copy();

        assertThat(danhMucLoaiTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucLoaiTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();

        assertThat(danhMucLoaiTaiSanCriteria).hasToString("DanhMucLoaiTaiSanCriteria{}");
    }

    private static void setAllFilters(DanhMucLoaiTaiSanCriteria danhMucLoaiTaiSanCriteria) {
        danhMucLoaiTaiSanCriteria.id();
        danhMucLoaiTaiSanCriteria.idLoaiTs();
        danhMucLoaiTaiSanCriteria.dienGiai();
        danhMucLoaiTaiSanCriteria.trangThai();
        danhMucLoaiTaiSanCriteria.searchStr();
        danhMucLoaiTaiSanCriteria.taiSanId();
        danhMucLoaiTaiSanCriteria.danhSachTaiSanId();
        danhMucLoaiTaiSanCriteria.distinct();
    }

    private static Condition<DanhMucLoaiTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdLoaiTs()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getSearchStr()) &&
                condition.apply(criteria.getTaiSanId()) &&
                condition.apply(criteria.getDanhSachTaiSanId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucLoaiTaiSanCriteria> copyFiltersAre(
        DanhMucLoaiTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdLoaiTs(), copy.getIdLoaiTs()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getSearchStr(), copy.getSearchStr()) &&
                condition.apply(criteria.getTaiSanId(), copy.getTaiSanId()) &&
                condition.apply(criteria.getDanhSachTaiSanId(), copy.getDanhSachTaiSanId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
