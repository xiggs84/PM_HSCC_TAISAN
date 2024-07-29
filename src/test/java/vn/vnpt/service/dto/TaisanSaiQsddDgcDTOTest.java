package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisanSaiQsddDgcDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisanSaiQsddDgcDTO.class);
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO1 = new TaisanSaiQsddDgcDTO();
        taisanSaiQsddDgcDTO1.setId(1L);
        TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO2 = new TaisanSaiQsddDgcDTO();
        assertThat(taisanSaiQsddDgcDTO1).isNotEqualTo(taisanSaiQsddDgcDTO2);
        taisanSaiQsddDgcDTO2.setId(taisanSaiQsddDgcDTO1.getId());
        assertThat(taisanSaiQsddDgcDTO1).isEqualTo(taisanSaiQsddDgcDTO2);
        taisanSaiQsddDgcDTO2.setId(2L);
        assertThat(taisanSaiQsddDgcDTO1).isNotEqualTo(taisanSaiQsddDgcDTO2);
        taisanSaiQsddDgcDTO1.setId(null);
        assertThat(taisanSaiQsddDgcDTO1).isNotEqualTo(taisanSaiQsddDgcDTO2);
    }
}
