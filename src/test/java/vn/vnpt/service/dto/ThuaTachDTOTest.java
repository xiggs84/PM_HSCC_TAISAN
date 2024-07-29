package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThuaTachDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThuaTachDTO.class);
        ThuaTachDTO thuaTachDTO1 = new ThuaTachDTO();
        thuaTachDTO1.setId(1L);
        ThuaTachDTO thuaTachDTO2 = new ThuaTachDTO();
        assertThat(thuaTachDTO1).isNotEqualTo(thuaTachDTO2);
        thuaTachDTO2.setId(thuaTachDTO1.getId());
        assertThat(thuaTachDTO1).isEqualTo(thuaTachDTO2);
        thuaTachDTO2.setId(2L);
        assertThat(thuaTachDTO1).isNotEqualTo(thuaTachDTO2);
        thuaTachDTO1.setId(null);
        assertThat(thuaTachDTO1).isNotEqualTo(thuaTachDTO2);
    }
}
