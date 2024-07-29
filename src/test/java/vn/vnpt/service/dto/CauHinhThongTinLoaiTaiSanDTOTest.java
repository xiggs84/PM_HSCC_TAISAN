package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhThongTinLoaiTaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhThongTinLoaiTaiSanDTO.class);
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO1 = new CauHinhThongTinLoaiTaiSanDTO();
        cauHinhThongTinLoaiTaiSanDTO1.setId(1L);
        CauHinhThongTinLoaiTaiSanDTO cauHinhThongTinLoaiTaiSanDTO2 = new CauHinhThongTinLoaiTaiSanDTO();
        assertThat(cauHinhThongTinLoaiTaiSanDTO1).isNotEqualTo(cauHinhThongTinLoaiTaiSanDTO2);
        cauHinhThongTinLoaiTaiSanDTO2.setId(cauHinhThongTinLoaiTaiSanDTO1.getId());
        assertThat(cauHinhThongTinLoaiTaiSanDTO1).isEqualTo(cauHinhThongTinLoaiTaiSanDTO2);
        cauHinhThongTinLoaiTaiSanDTO2.setId(2L);
        assertThat(cauHinhThongTinLoaiTaiSanDTO1).isNotEqualTo(cauHinhThongTinLoaiTaiSanDTO2);
        cauHinhThongTinLoaiTaiSanDTO1.setId(null);
        assertThat(cauHinhThongTinLoaiTaiSanDTO1).isNotEqualTo(cauHinhThongTinLoaiTaiSanDTO2);
    }
}
