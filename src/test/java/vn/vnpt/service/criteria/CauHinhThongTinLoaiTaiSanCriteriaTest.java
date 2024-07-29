package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CauHinhThongTinLoaiTaiSanCriteriaTest {

    @Test
    void newCauHinhThongTinLoaiTaiSanCriteriaHasAllFiltersNullTest() {
        var cauHinhThongTinLoaiTaiSanCriteria = new CauHinhThongTinLoaiTaiSanCriteria();
        assertThat(cauHinhThongTinLoaiTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void cauHinhThongTinLoaiTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var cauHinhThongTinLoaiTaiSanCriteria = new CauHinhThongTinLoaiTaiSanCriteria();

        setAllFilters(cauHinhThongTinLoaiTaiSanCriteria);

        assertThat(cauHinhThongTinLoaiTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void cauHinhThongTinLoaiTaiSanCriteriaCopyCreatesNullFilterTest() {
        var cauHinhThongTinLoaiTaiSanCriteria = new CauHinhThongTinLoaiTaiSanCriteria();
        var copy = cauHinhThongTinLoaiTaiSanCriteria.copy();

        assertThat(cauHinhThongTinLoaiTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(cauHinhThongTinLoaiTaiSanCriteria)
        );
    }

    @Test
    void cauHinhThongTinLoaiTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var cauHinhThongTinLoaiTaiSanCriteria = new CauHinhThongTinLoaiTaiSanCriteria();
        setAllFilters(cauHinhThongTinLoaiTaiSanCriteria);

        var copy = cauHinhThongTinLoaiTaiSanCriteria.copy();

        assertThat(cauHinhThongTinLoaiTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(cauHinhThongTinLoaiTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var cauHinhThongTinLoaiTaiSanCriteria = new CauHinhThongTinLoaiTaiSanCriteria();

        assertThat(cauHinhThongTinLoaiTaiSanCriteria).hasToString("CauHinhThongTinLoaiTaiSanCriteria{}");
    }

    private static void setAllFilters(CauHinhThongTinLoaiTaiSanCriteria cauHinhThongTinLoaiTaiSanCriteria) {
        cauHinhThongTinLoaiTaiSanCriteria.id();
        cauHinhThongTinLoaiTaiSanCriteria.idCauHinh();
        cauHinhThongTinLoaiTaiSanCriteria.noiDung();
        cauHinhThongTinLoaiTaiSanCriteria.javascript();
        cauHinhThongTinLoaiTaiSanCriteria.css();
        cauHinhThongTinLoaiTaiSanCriteria.idLoaiTs();
        cauHinhThongTinLoaiTaiSanCriteria.idDonVi();
        cauHinhThongTinLoaiTaiSanCriteria.trangThai();
        cauHinhThongTinLoaiTaiSanCriteria.xml();
        cauHinhThongTinLoaiTaiSanCriteria.distinct();
    }

    private static Condition<CauHinhThongTinLoaiTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdCauHinh()) &&
                condition.apply(criteria.getNoiDung()) &&
                condition.apply(criteria.getJavascript()) &&
                condition.apply(criteria.getCss()) &&
                condition.apply(criteria.getIdLoaiTs()) &&
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getXml()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CauHinhThongTinLoaiTaiSanCriteria> copyFiltersAre(
        CauHinhThongTinLoaiTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdCauHinh(), copy.getIdCauHinh()) &&
                condition.apply(criteria.getNoiDung(), copy.getNoiDung()) &&
                condition.apply(criteria.getJavascript(), copy.getJavascript()) &&
                condition.apply(criteria.getCss(), copy.getCss()) &&
                condition.apply(criteria.getIdLoaiTs(), copy.getIdLoaiTs()) &&
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getXml(), copy.getXml()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
