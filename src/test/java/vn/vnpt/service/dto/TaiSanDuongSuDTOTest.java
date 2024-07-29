package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDuongSuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDuongSuDTO.class);
        TaiSanDuongSuDTO taiSanDuongSuDTO1 = new TaiSanDuongSuDTO();
        taiSanDuongSuDTO1.setId(1L);
        TaiSanDuongSuDTO taiSanDuongSuDTO2 = new TaiSanDuongSuDTO();
        assertThat(taiSanDuongSuDTO1).isNotEqualTo(taiSanDuongSuDTO2);
        taiSanDuongSuDTO2.setId(taiSanDuongSuDTO1.getId());
        assertThat(taiSanDuongSuDTO1).isEqualTo(taiSanDuongSuDTO2);
        taiSanDuongSuDTO2.setId(2L);
        assertThat(taiSanDuongSuDTO1).isNotEqualTo(taiSanDuongSuDTO2);
        taiSanDuongSuDTO1.setId(null);
        assertThat(taiSanDuongSuDTO1).isNotEqualTo(taiSanDuongSuDTO2);
    }
}
