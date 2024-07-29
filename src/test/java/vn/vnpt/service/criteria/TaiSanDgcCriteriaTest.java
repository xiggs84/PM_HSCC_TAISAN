package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaiSanDgcCriteriaTest {

    @Test
    void newTaiSanDgcCriteriaHasAllFiltersNullTest() {
        var taiSanDgcCriteria = new TaiSanDgcCriteria();
        assertThat(taiSanDgcCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taiSanDgcCriteriaFluentMethodsCreatesFiltersTest() {
        var taiSanDgcCriteria = new TaiSanDgcCriteria();

        setAllFilters(taiSanDgcCriteria);

        assertThat(taiSanDgcCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taiSanDgcCriteriaCopyCreatesNullFilterTest() {
        var taiSanDgcCriteria = new TaiSanDgcCriteria();
        var copy = taiSanDgcCriteria.copy();

        assertThat(taiSanDgcCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanDgcCriteria)
        );
    }

    @Test
    void taiSanDgcCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taiSanDgcCriteria = new TaiSanDgcCriteria();
        setAllFilters(taiSanDgcCriteria);

        var copy = taiSanDgcCriteria.copy();

        assertThat(taiSanDgcCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanDgcCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taiSanDgcCriteria = new TaiSanDgcCriteria();

        assertThat(taiSanDgcCriteria).hasToString("TaiSanDgcCriteria{}");
    }

    private static void setAllFilters(TaiSanDgcCriteria taiSanDgcCriteria) {
        taiSanDgcCriteria.id();
        taiSanDgcCriteria.idTaiSan();
        taiSanDgcCriteria.tenTaiSan();
        taiSanDgcCriteria.trangThai();
        taiSanDgcCriteria.thongTinTs();
        taiSanDgcCriteria.idLoaiTs();
        taiSanDgcCriteria.ghiChu();
        taiSanDgcCriteria.ngayThaoTac();
        taiSanDgcCriteria.nguoiThaoTac();
        taiSanDgcCriteria.idDuongSu();
        taiSanDgcCriteria.idTsGoc();
        taiSanDgcCriteria.maTaiSan();
        taiSanDgcCriteria.idTinhTrang();
        taiSanDgcCriteria.idLoaiNganChan();
        taiSanDgcCriteria.ngayBdNganChan();
        taiSanDgcCriteria.ngayKtNganChan();
        taiSanDgcCriteria.idMaster();
        taiSanDgcCriteria.strSearch();
        taiSanDgcCriteria.idDonVi();
        taiSanDgcCriteria.soHsCv();
        taiSanDgcCriteria.soCc();
        taiSanDgcCriteria.soVaoSo();
        taiSanDgcCriteria.moTa();
        taiSanDgcCriteria.distinct();
    }

    private static Condition<TaiSanDgcCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdTaiSan()) &&
                condition.apply(criteria.getTenTaiSan()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getThongTinTs()) &&
                condition.apply(criteria.getIdLoaiTs()) &&
                condition.apply(criteria.getGhiChu()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDuongSu()) &&
                condition.apply(criteria.getIdTsGoc()) &&
                condition.apply(criteria.getMaTaiSan()) &&
                condition.apply(criteria.getIdTinhTrang()) &&
                condition.apply(criteria.getIdLoaiNganChan()) &&
                condition.apply(criteria.getNgayBdNganChan()) &&
                condition.apply(criteria.getNgayKtNganChan()) &&
                condition.apply(criteria.getIdMaster()) &&
                condition.apply(criteria.getStrSearch()) &&
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getSoHsCv()) &&
                condition.apply(criteria.getSoCc()) &&
                condition.apply(criteria.getSoVaoSo()) &&
                condition.apply(criteria.getMoTa()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaiSanDgcCriteria> copyFiltersAre(TaiSanDgcCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdTaiSan(), copy.getIdTaiSan()) &&
                condition.apply(criteria.getTenTaiSan(), copy.getTenTaiSan()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getThongTinTs(), copy.getThongTinTs()) &&
                condition.apply(criteria.getIdLoaiTs(), copy.getIdLoaiTs()) &&
                condition.apply(criteria.getGhiChu(), copy.getGhiChu()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac(), copy.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDuongSu(), copy.getIdDuongSu()) &&
                condition.apply(criteria.getIdTsGoc(), copy.getIdTsGoc()) &&
                condition.apply(criteria.getMaTaiSan(), copy.getMaTaiSan()) &&
                condition.apply(criteria.getIdTinhTrang(), copy.getIdTinhTrang()) &&
                condition.apply(criteria.getIdLoaiNganChan(), copy.getIdLoaiNganChan()) &&
                condition.apply(criteria.getNgayBdNganChan(), copy.getNgayBdNganChan()) &&
                condition.apply(criteria.getNgayKtNganChan(), copy.getNgayKtNganChan()) &&
                condition.apply(criteria.getIdMaster(), copy.getIdMaster()) &&
                condition.apply(criteria.getStrSearch(), copy.getStrSearch()) &&
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getSoHsCv(), copy.getSoHsCv()) &&
                condition.apply(criteria.getSoCc(), copy.getSoCc()) &&
                condition.apply(criteria.getSoVaoSo(), copy.getSoVaoSo()) &&
                condition.apply(criteria.getMoTa(), copy.getMoTa()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
