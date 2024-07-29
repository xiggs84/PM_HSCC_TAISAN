package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaisanSaiDgcTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisanSaiDgcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisanSaiDgc.class);
        TaisanSaiDgc taisanSaiDgc1 = getTaisanSaiDgcSample1();
        TaisanSaiDgc taisanSaiDgc2 = new TaisanSaiDgc();
        assertThat(taisanSaiDgc1).isNotEqualTo(taisanSaiDgc2);

        taisanSaiDgc2.setId(taisanSaiDgc1.getId());
        assertThat(taisanSaiDgc1).isEqualTo(taisanSaiDgc2);

        taisanSaiDgc2 = getTaisanSaiDgcSample2();
        assertThat(taisanSaiDgc1).isNotEqualTo(taisanSaiDgc2);
    }
}
