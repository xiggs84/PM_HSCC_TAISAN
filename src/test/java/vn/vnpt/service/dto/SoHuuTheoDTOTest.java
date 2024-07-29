package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoHuuTheoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoHuuTheoDTO.class);
        SoHuuTheoDTO soHuuTheoDTO1 = new SoHuuTheoDTO();
        soHuuTheoDTO1.setId(1L);
        SoHuuTheoDTO soHuuTheoDTO2 = new SoHuuTheoDTO();
        assertThat(soHuuTheoDTO1).isNotEqualTo(soHuuTheoDTO2);
        soHuuTheoDTO2.setId(soHuuTheoDTO1.getId());
        assertThat(soHuuTheoDTO1).isEqualTo(soHuuTheoDTO2);
        soHuuTheoDTO2.setId(2L);
        assertThat(soHuuTheoDTO1).isNotEqualTo(soHuuTheoDTO2);
        soHuuTheoDTO1.setId(null);
        assertThat(soHuuTheoDTO1).isNotEqualTo(soHuuTheoDTO2);
    }
}
