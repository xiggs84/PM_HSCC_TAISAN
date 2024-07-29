package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaiSanDgcTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDgcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDgc.class);
        TaiSanDgc taiSanDgc1 = getTaiSanDgcSample1();
        TaiSanDgc taiSanDgc2 = new TaiSanDgc();
        assertThat(taiSanDgc1).isNotEqualTo(taiSanDgc2);

        taiSanDgc2.setId(taiSanDgc1.getId());
        assertThat(taiSanDgc1).isEqualTo(taiSanDgc2);

        taiSanDgc2 = getTaiSanDgcSample2();
        assertThat(taiSanDgc1).isNotEqualTo(taiSanDgc2);
    }
}
