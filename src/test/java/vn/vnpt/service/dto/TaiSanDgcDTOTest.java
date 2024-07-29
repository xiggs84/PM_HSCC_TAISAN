package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDgcDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDgcDTO.class);
        TaiSanDgcDTO taiSanDgcDTO1 = new TaiSanDgcDTO();
        taiSanDgcDTO1.setId(1L);
        TaiSanDgcDTO taiSanDgcDTO2 = new TaiSanDgcDTO();
        assertThat(taiSanDgcDTO1).isNotEqualTo(taiSanDgcDTO2);
        taiSanDgcDTO2.setId(taiSanDgcDTO1.getId());
        assertThat(taiSanDgcDTO1).isEqualTo(taiSanDgcDTO2);
        taiSanDgcDTO2.setId(2L);
        assertThat(taiSanDgcDTO1).isNotEqualTo(taiSanDgcDTO2);
        taiSanDgcDTO1.setId(null);
        assertThat(taiSanDgcDTO1).isNotEqualTo(taiSanDgcDTO2);
    }
}
