package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaiSanDatNhaCriteriaTest {

    @Test
    void newTaiSanDatNhaCriteriaHasAllFiltersNullTest() {
        var taiSanDatNhaCriteria = new TaiSanDatNhaCriteria();
        assertThat(taiSanDatNhaCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taiSanDatNhaCriteriaFluentMethodsCreatesFiltersTest() {
        var taiSanDatNhaCriteria = new TaiSanDatNhaCriteria();

        setAllFilters(taiSanDatNhaCriteria);

        assertThat(taiSanDatNhaCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taiSanDatNhaCriteriaCopyCreatesNullFilterTest() {
        var taiSanDatNhaCriteria = new TaiSanDatNhaCriteria();
        var copy = taiSanDatNhaCriteria.copy();

        assertThat(taiSanDatNhaCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanDatNhaCriteria)
        );
    }

    @Test
    void taiSanDatNhaCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taiSanDatNhaCriteria = new TaiSanDatNhaCriteria();
        setAllFilters(taiSanDatNhaCriteria);

        var copy = taiSanDatNhaCriteria.copy();

        assertThat(taiSanDatNhaCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanDatNhaCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taiSanDatNhaCriteria = new TaiSanDatNhaCriteria();

        assertThat(taiSanDatNhaCriteria).hasToString("TaiSanDatNhaCriteria{}");
    }

    private static void setAllFilters(TaiSanDatNhaCriteria taiSanDatNhaCriteria) {
        taiSanDatNhaCriteria.id();
        taiSanDatNhaCriteria.idTaiSan();
        taiSanDatNhaCriteria.tenTaiSan();
        taiSanDatNhaCriteria.trangThai();
        taiSanDatNhaCriteria.thongTinTs();
        taiSanDatNhaCriteria.idLoaiTs();
        taiSanDatNhaCriteria.ghiChu();
        taiSanDatNhaCriteria.ngayThaoTac();
        taiSanDatNhaCriteria.nguoiThaoTac();
        taiSanDatNhaCriteria.idDuongSu();
        taiSanDatNhaCriteria.idTsGoc();
        taiSanDatNhaCriteria.maTaiSan();
        taiSanDatNhaCriteria.idTinhTrang();
        taiSanDatNhaCriteria.idLoaiNganChan();
        taiSanDatNhaCriteria.ngayBdNganChan();
        taiSanDatNhaCriteria.ngayKtNganChan();
        taiSanDatNhaCriteria.idMaster();
        taiSanDatNhaCriteria.strSearch();
        taiSanDatNhaCriteria.idDonVi();
        taiSanDatNhaCriteria.soHsCv();
        taiSanDatNhaCriteria.soCc();
        taiSanDatNhaCriteria.soVaoSo();
        taiSanDatNhaCriteria.moTa();
        taiSanDatNhaCriteria.loaiNganChan();
        taiSanDatNhaCriteria.distinct();
    }

    private static Condition<TaiSanDatNhaCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
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
                condition.apply(criteria.getLoaiNganChan()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaiSanDatNhaCriteria> copyFiltersAre(
        TaiSanDatNhaCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
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
                condition.apply(criteria.getLoaiNganChan(), copy.getLoaiNganChan()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
