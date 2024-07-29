package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CauHinhThongTinLoaiTaiSanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhThongTinLoaiTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhThongTinLoaiTaiSan.class);
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan1 = getCauHinhThongTinLoaiTaiSanSample1();
        CauHinhThongTinLoaiTaiSan cauHinhThongTinLoaiTaiSan2 = new CauHinhThongTinLoaiTaiSan();
        assertThat(cauHinhThongTinLoaiTaiSan1).isNotEqualTo(cauHinhThongTinLoaiTaiSan2);

        cauHinhThongTinLoaiTaiSan2.setId(cauHinhThongTinLoaiTaiSan1.getId());
        assertThat(cauHinhThongTinLoaiTaiSan1).isEqualTo(cauHinhThongTinLoaiTaiSan2);

        cauHinhThongTinLoaiTaiSan2 = getCauHinhThongTinLoaiTaiSanSample2();
        assertThat(cauHinhThongTinLoaiTaiSan1).isNotEqualTo(cauHinhThongTinLoaiTaiSan2);
    }
}
