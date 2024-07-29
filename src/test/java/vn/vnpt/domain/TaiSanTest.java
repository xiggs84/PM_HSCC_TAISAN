package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.TinhTrangTaiSanTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSan.class);
        TaiSan taiSan1 = getTaiSanSample1();
        TaiSan taiSan2 = new TaiSan();
        assertThat(taiSan1).isNotEqualTo(taiSan2);

        taiSan2.setId(taiSan1.getId());
        assertThat(taiSan1).isEqualTo(taiSan2);

        taiSan2 = getTaiSanSample2();
        assertThat(taiSan1).isNotEqualTo(taiSan2);
    }

    @Test
    void idTsGocTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        taiSan.addIdTsGoc(taiSanBack);
        assertThat(taiSan.getIdTsGocs()).containsOnly(taiSanBack);

        taiSan.removeIdTsGoc(taiSanBack);
        assertThat(taiSan.getIdTsGocs()).doesNotContain(taiSanBack);

        taiSan.idTsGocs(new HashSet<>(Set.of(taiSanBack)));
        assertThat(taiSan.getIdTsGocs()).containsOnly(taiSanBack);

        taiSan.setIdTsGocs(new HashSet<>());
        assertThat(taiSan.getIdTsGocs()).doesNotContain(taiSanBack);
    }

    @Test
    void idLoaiTsTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        DanhMucLoaiTaiSan danhMucLoaiTaiSanBack = getDanhMucLoaiTaiSanRandomSampleGenerator();

        taiSan.setIdLoaiTs(danhMucLoaiTaiSanBack);
        assertThat(taiSan.getIdLoaiTs()).isEqualTo(danhMucLoaiTaiSanBack);

        taiSan.idLoaiTs(null);
        assertThat(taiSan.getIdLoaiTs()).isNull();
    }

    @Test
    void idTinhTrangTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TinhTrangTaiSan tinhTrangTaiSanBack = getTinhTrangTaiSanRandomSampleGenerator();

        taiSan.setIdTinhTrang(tinhTrangTaiSanBack);
        assertThat(taiSan.getIdTinhTrang()).isEqualTo(tinhTrangTaiSanBack);

        taiSan.idTinhTrang(null);
        assertThat(taiSan.getIdTinhTrang()).isNull();
    }

    @Test
    void taiSanTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        taiSan.addTaiSan(taiSanBack);
        assertThat(taiSan.getTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getIdTsGocs()).containsOnly(taiSan);

        taiSan.removeTaiSan(taiSanBack);
        assertThat(taiSan.getTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getIdTsGocs()).doesNotContain(taiSan);

        taiSan.taiSans(new HashSet<>(Set.of(taiSanBack)));
        assertThat(taiSan.getTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getIdTsGocs()).containsOnly(taiSan);

        taiSan.setTaiSans(new HashSet<>());
        assertThat(taiSan.getTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getIdTsGocs()).doesNotContain(taiSan);
    }

    @Test
    void taiSanDuongSuTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TaiSanDuongSu taiSanDuongSuBack = getTaiSanDuongSuRandomSampleGenerator();

        taiSan.setTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(taiSan.getTaiSanDuongSu()).isEqualTo(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getIdTaiSan()).isEqualTo(taiSan);

        taiSan.taiSanDuongSu(null);
        assertThat(taiSan.getTaiSanDuongSu()).isNull();
        assertThat(taiSanDuongSuBack.getIdTaiSan()).isNull();
    }
}
