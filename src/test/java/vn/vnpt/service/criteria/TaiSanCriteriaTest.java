package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TaiSanCriteriaTest {

    @Test
    void newTaiSanCriteriaHasAllFiltersNullTest() {
        var taiSanCriteria = new TaiSanCriteria();
        assertThat(taiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void taiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var taiSanCriteria = new TaiSanCriteria();

        setAllFilters(taiSanCriteria);

        assertThat(taiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void taiSanCriteriaCopyCreatesNullFilterTest() {
        var taiSanCriteria = new TaiSanCriteria();
        var copy = taiSanCriteria.copy();

        assertThat(taiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanCriteria)
        );
    }

    @Test
    void taiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var taiSanCriteria = new TaiSanCriteria();
        setAllFilters(taiSanCriteria);

        var copy = taiSanCriteria.copy();

        assertThat(taiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(taiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var taiSanCriteria = new TaiSanCriteria();

        assertThat(taiSanCriteria).hasToString("TaiSanCriteria{}");
    }

    private static void setAllFilters(TaiSanCriteria taiSanCriteria) {
        taiSanCriteria.id();
        taiSanCriteria.idTaiSan();
        taiSanCriteria.tenTaiSan();
        taiSanCriteria.trangThai();
        taiSanCriteria.thongTinTs();
        taiSanCriteria.ghiChu();
        taiSanCriteria.ngayThaoTac();
        taiSanCriteria.nguoiThaoTac();
        taiSanCriteria.idDuongSu();
        taiSanCriteria.idTsGoc();
        taiSanCriteria.maTaiSan();
        taiSanCriteria.idLoaiNganChan();
        taiSanCriteria.ngayBdNganChan();
        taiSanCriteria.ngayKtNganChan();
        taiSanCriteria.idMaster();
        taiSanCriteria.strSearch();
        taiSanCriteria.idDonVi();
        taiSanCriteria.soHsCv();
        taiSanCriteria.soCc();
        taiSanCriteria.soVaoSo();
        taiSanCriteria.moTa();
        taiSanCriteria.loaiNganChan();
        taiSanCriteria.syncStatus();
        taiSanCriteria.idTsGocId();
        taiSanCriteria.idLoaiTsId();
        taiSanCriteria.idTinhTrangId();
        taiSanCriteria.taiSanId();
        taiSanCriteria.taiSanDuongSuId();
        taiSanCriteria.distinct();
    }

    private static Condition<TaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdTaiSan()) &&
                condition.apply(criteria.getTenTaiSan()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getThongTinTs()) &&
                condition.apply(criteria.getGhiChu()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDuongSu()) &&
                condition.apply(criteria.getIdTsGoc()) &&
                condition.apply(criteria.getMaTaiSan()) &&
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
                condition.apply(criteria.getSyncStatus()) &&
                condition.apply(criteria.getIdTsGocId()) &&
                condition.apply(criteria.getIdLoaiTsId()) &&
                condition.apply(criteria.getIdTinhTrangId()) &&
                condition.apply(criteria.getTaiSanId()) &&
                condition.apply(criteria.getTaiSanDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TaiSanCriteria> copyFiltersAre(TaiSanCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdTaiSan(), copy.getIdTaiSan()) &&
                condition.apply(criteria.getTenTaiSan(), copy.getTenTaiSan()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getThongTinTs(), copy.getThongTinTs()) &&
                condition.apply(criteria.getGhiChu(), copy.getGhiChu()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac(), copy.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDuongSu(), copy.getIdDuongSu()) &&
                condition.apply(criteria.getIdTsGoc(), copy.getIdTsGoc()) &&
                condition.apply(criteria.getMaTaiSan(), copy.getMaTaiSan()) &&
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
                condition.apply(criteria.getSyncStatus(), copy.getSyncStatus()) &&
                condition.apply(criteria.getIdTsGocId(), copy.getIdTsGocId()) &&
                condition.apply(criteria.getIdLoaiTsId(), copy.getIdLoaiTsId()) &&
                condition.apply(criteria.getIdTinhTrangId(), copy.getIdTinhTrangId()) &&
                condition.apply(criteria.getTaiSanId(), copy.getTaiSanId()) &&
                condition.apply(criteria.getTaiSanDuongSuId(), copy.getTaiSanDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
