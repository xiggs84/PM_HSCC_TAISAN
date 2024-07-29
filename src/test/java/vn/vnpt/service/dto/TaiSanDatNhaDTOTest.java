package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDatNhaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDatNhaDTO.class);
        TaiSanDatNhaDTO taiSanDatNhaDTO1 = new TaiSanDatNhaDTO();
        taiSanDatNhaDTO1.setId(1L);
        TaiSanDatNhaDTO taiSanDatNhaDTO2 = new TaiSanDatNhaDTO();
        assertThat(taiSanDatNhaDTO1).isNotEqualTo(taiSanDatNhaDTO2);
        taiSanDatNhaDTO2.setId(taiSanDatNhaDTO1.getId());
        assertThat(taiSanDatNhaDTO1).isEqualTo(taiSanDatNhaDTO2);
        taiSanDatNhaDTO2.setId(2L);
        assertThat(taiSanDatNhaDTO1).isNotEqualTo(taiSanDatNhaDTO2);
        taiSanDatNhaDTO1.setId(null);
        assertThat(taiSanDatNhaDTO1).isNotEqualTo(taiSanDatNhaDTO2);
    }
}
