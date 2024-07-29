package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;
import static vn.vnpt.domain.DanhSachTaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiTaiSan.class);
        DanhMucLoaiTaiSan danhMucLoaiTaiSan1 = getDanhMucLoaiTaiSanSample1();
        DanhMucLoaiTaiSan danhMucLoaiTaiSan2 = new DanhMucLoaiTaiSan();
        assertThat(danhMucLoaiTaiSan1).isNotEqualTo(danhMucLoaiTaiSan2);

        danhMucLoaiTaiSan2.setId(danhMucLoaiTaiSan1.getId());
        assertThat(danhMucLoaiTaiSan1).isEqualTo(danhMucLoaiTaiSan2);

        danhMucLoaiTaiSan2 = getDanhMucLoaiTaiSanSample2();
        assertThat(danhMucLoaiTaiSan1).isNotEqualTo(danhMucLoaiTaiSan2);
    }

    @Test
    void taiSanTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        danhMucLoaiTaiSan.addTaiSan(taiSanBack);
        assertThat(danhMucLoaiTaiSan.getTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getIdLoaiTs()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeTaiSan(taiSanBack);
        assertThat(danhMucLoaiTaiSan.getTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getIdLoaiTs()).isNull();

        danhMucLoaiTaiSan.taiSans(new HashSet<>(Set.of(taiSanBack)));
        assertThat(danhMucLoaiTaiSan.getTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getIdLoaiTs()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setTaiSans(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getIdLoaiTs()).isNull();
    }

    @Test
    void danhSachTaiSanTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        DanhSachTaiSan danhSachTaiSanBack = getDanhSachTaiSanRandomSampleGenerator();

        danhMucLoaiTaiSan.addDanhSachTaiSan(danhSachTaiSanBack);
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).containsOnly(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getIdLoaiTs()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeDanhSachTaiSan(danhSachTaiSanBack);
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).doesNotContain(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getIdLoaiTs()).isNull();

        danhMucLoaiTaiSan.danhSachTaiSans(new HashSet<>(Set.of(danhSachTaiSanBack)));
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).containsOnly(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getIdLoaiTs()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setDanhSachTaiSans(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).doesNotContain(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getIdLoaiTs()).isNull();
    }
}
