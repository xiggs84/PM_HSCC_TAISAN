package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisanSaiDgcDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisanSaiDgcDTO.class);
        TaisanSaiDgcDTO taisanSaiDgcDTO1 = new TaisanSaiDgcDTO();
        taisanSaiDgcDTO1.setId(1L);
        TaisanSaiDgcDTO taisanSaiDgcDTO2 = new TaisanSaiDgcDTO();
        assertThat(taisanSaiDgcDTO1).isNotEqualTo(taisanSaiDgcDTO2);
        taisanSaiDgcDTO2.setId(taisanSaiDgcDTO1.getId());
        assertThat(taisanSaiDgcDTO1).isEqualTo(taisanSaiDgcDTO2);
        taisanSaiDgcDTO2.setId(2L);
        assertThat(taisanSaiDgcDTO1).isNotEqualTo(taisanSaiDgcDTO2);
        taisanSaiDgcDTO1.setId(null);
        assertThat(taisanSaiDgcDTO1).isNotEqualTo(taisanSaiDgcDTO2);
    }
}
