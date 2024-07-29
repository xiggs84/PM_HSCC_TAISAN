package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisannhadatidDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisannhadatidDTO.class);
        TaisannhadatidDTO taisannhadatidDTO1 = new TaisannhadatidDTO();
        taisannhadatidDTO1.setId(1L);
        TaisannhadatidDTO taisannhadatidDTO2 = new TaisannhadatidDTO();
        assertThat(taisannhadatidDTO1).isNotEqualTo(taisannhadatidDTO2);
        taisannhadatidDTO2.setId(taisannhadatidDTO1.getId());
        assertThat(taisannhadatidDTO1).isEqualTo(taisannhadatidDTO2);
        taisannhadatidDTO2.setId(2L);
        assertThat(taisannhadatidDTO1).isNotEqualTo(taisannhadatidDTO2);
        taisannhadatidDTO1.setId(null);
        assertThat(taisannhadatidDTO1).isNotEqualTo(taisannhadatidDTO2);
    }
}
