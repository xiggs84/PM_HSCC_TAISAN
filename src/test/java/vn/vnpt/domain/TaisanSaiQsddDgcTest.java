package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaisanSaiQsddDgcTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisanSaiQsddDgcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisanSaiQsddDgc.class);
        TaisanSaiQsddDgc taisanSaiQsddDgc1 = getTaisanSaiQsddDgcSample1();
        TaisanSaiQsddDgc taisanSaiQsddDgc2 = new TaisanSaiQsddDgc();
        assertThat(taisanSaiQsddDgc1).isNotEqualTo(taisanSaiQsddDgc2);

        taisanSaiQsddDgc2.setId(taisanSaiQsddDgc1.getId());
        assertThat(taisanSaiQsddDgc1).isEqualTo(taisanSaiQsddDgc2);

        taisanSaiQsddDgc2 = getTaisanSaiQsddDgcSample2();
        assertThat(taisanSaiQsddDgc1).isNotEqualTo(taisanSaiQsddDgc2);
    }
}
