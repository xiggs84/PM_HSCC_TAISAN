package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiTaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiTaiSanDTO.class);
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO1 = new DanhMucLoaiTaiSanDTO();
        danhMucLoaiTaiSanDTO1.setId(1L);
        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO2 = new DanhMucLoaiTaiSanDTO();
        assertThat(danhMucLoaiTaiSanDTO1).isNotEqualTo(danhMucLoaiTaiSanDTO2);
        danhMucLoaiTaiSanDTO2.setId(danhMucLoaiTaiSanDTO1.getId());
        assertThat(danhMucLoaiTaiSanDTO1).isEqualTo(danhMucLoaiTaiSanDTO2);
        danhMucLoaiTaiSanDTO2.setId(2L);
        assertThat(danhMucLoaiTaiSanDTO1).isNotEqualTo(danhMucLoaiTaiSanDTO2);
        danhMucLoaiTaiSanDTO1.setId(null);
        assertThat(danhMucLoaiTaiSanDTO1).isNotEqualTo(danhMucLoaiTaiSanDTO2);
    }
}
