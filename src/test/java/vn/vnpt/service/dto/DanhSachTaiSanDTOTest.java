package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachTaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachTaiSanDTO.class);
        DanhSachTaiSanDTO danhSachTaiSanDTO1 = new DanhSachTaiSanDTO();
        danhSachTaiSanDTO1.setId(1L);
        DanhSachTaiSanDTO danhSachTaiSanDTO2 = new DanhSachTaiSanDTO();
        assertThat(danhSachTaiSanDTO1).isNotEqualTo(danhSachTaiSanDTO2);
        danhSachTaiSanDTO2.setId(danhSachTaiSanDTO1.getId());
        assertThat(danhSachTaiSanDTO1).isEqualTo(danhSachTaiSanDTO2);
        danhSachTaiSanDTO2.setId(2L);
        assertThat(danhSachTaiSanDTO1).isNotEqualTo(danhSachTaiSanDTO2);
        danhSachTaiSanDTO1.setId(null);
        assertThat(danhSachTaiSanDTO1).isNotEqualTo(danhSachTaiSanDTO2);
    }
}
