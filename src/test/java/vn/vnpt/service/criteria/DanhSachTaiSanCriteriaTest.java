package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhSachTaiSanCriteriaTest {

    @Test
    void newDanhSachTaiSanCriteriaHasAllFiltersNullTest() {
        var danhSachTaiSanCriteria = new DanhSachTaiSanCriteria();
        assertThat(danhSachTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhSachTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var danhSachTaiSanCriteria = new DanhSachTaiSanCriteria();

        setAllFilters(danhSachTaiSanCriteria);

        assertThat(danhSachTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhSachTaiSanCriteriaCopyCreatesNullFilterTest() {
        var danhSachTaiSanCriteria = new DanhSachTaiSanCriteria();
        var copy = danhSachTaiSanCriteria.copy();

        assertThat(danhSachTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhSachTaiSanCriteria)
        );
    }

    @Test
    void danhSachTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhSachTaiSanCriteria = new DanhSachTaiSanCriteria();
        setAllFilters(danhSachTaiSanCriteria);

        var copy = danhSachTaiSanCriteria.copy();

        assertThat(danhSachTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhSachTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhSachTaiSanCriteria = new DanhSachTaiSanCriteria();

        assertThat(danhSachTaiSanCriteria).hasToString("DanhSachTaiSanCriteria{}");
    }

    private static void setAllFilters(DanhSachTaiSanCriteria danhSachTaiSanCriteria) {
        danhSachTaiSanCriteria.id();
        danhSachTaiSanCriteria.idTaiSan();
        danhSachTaiSanCriteria.tenTaiSan();
        danhSachTaiSanCriteria.trangThai();
        danhSachTaiSanCriteria.ghiChu();
        danhSachTaiSanCriteria.ngayThaoTac();
        danhSachTaiSanCriteria.nguoiThaoTac();
        danhSachTaiSanCriteria.idDuongSu();
        danhSachTaiSanCriteria.idTsGoc();
        danhSachTaiSanCriteria.maTaiSan();
        danhSachTaiSanCriteria.idTinhTrang();
        danhSachTaiSanCriteria.idLoaiNganChan();
        danhSachTaiSanCriteria.ngayBdNganChan();
        danhSachTaiSanCriteria.ngayKtNganChan();
        danhSachTaiSanCriteria.idMaster();
        danhSachTaiSanCriteria.strSearch();
        danhSachTaiSanCriteria.idDonVi();
        danhSachTaiSanCriteria.soHsCv();
        danhSachTaiSanCriteria.soCc();
        danhSachTaiSanCriteria.soVaoSo();
        danhSachTaiSanCriteria.moTa();
        danhSachTaiSanCriteria.loaiNganChan();
        danhSachTaiSanCriteria.maXa();
        danhSachTaiSanCriteria.idLoaiTsId();
        danhSachTaiSanCriteria.distinct();
    }

    private static Condition<DanhSachTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdTaiSan()) &&
                condition.apply(criteria.getTenTaiSan()) &&
                condition.apply(criteria.getTrangThai()) &&
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
                condition.apply(criteria.getMaXa()) &&
                condition.apply(criteria.getIdLoaiTsId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhSachTaiSanCriteria> copyFiltersAre(
        DanhSachTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdTaiSan(), copy.getIdTaiSan()) &&
                condition.apply(criteria.getTenTaiSan(), copy.getTenTaiSan()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
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
                condition.apply(criteria.getMaXa(), copy.getMaXa()) &&
                condition.apply(criteria.getIdLoaiTsId(), copy.getIdLoaiTsId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
