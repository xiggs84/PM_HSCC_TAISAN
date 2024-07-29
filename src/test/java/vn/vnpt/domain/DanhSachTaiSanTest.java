package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;
import static vn.vnpt.domain.DanhSachTaiSanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachTaiSan.class);
        DanhSachTaiSan danhSachTaiSan1 = getDanhSachTaiSanSample1();
        DanhSachTaiSan danhSachTaiSan2 = new DanhSachTaiSan();
        assertThat(danhSachTaiSan1).isNotEqualTo(danhSachTaiSan2);

        danhSachTaiSan2.setId(danhSachTaiSan1.getId());
        assertThat(danhSachTaiSan1).isEqualTo(danhSachTaiSan2);

        danhSachTaiSan2 = getDanhSachTaiSanSample2();
        assertThat(danhSachTaiSan1).isNotEqualTo(danhSachTaiSan2);
    }

    @Test
    void idLoaiTsTest() {
        DanhSachTaiSan danhSachTaiSan = getDanhSachTaiSanRandomSampleGenerator();
        DanhMucLoaiTaiSan danhMucLoaiTaiSanBack = getDanhMucLoaiTaiSanRandomSampleGenerator();

        danhSachTaiSan.setIdLoaiTs(danhMucLoaiTaiSanBack);
        assertThat(danhSachTaiSan.getIdLoaiTs()).isEqualTo(danhMucLoaiTaiSanBack);

        danhSachTaiSan.idLoaiTs(null);
        assertThat(danhSachTaiSan.getIdLoaiTs()).isNull();
    }
}
