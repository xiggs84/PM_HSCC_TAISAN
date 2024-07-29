package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.SoHuuTheoTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoHuuTheoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoHuuTheo.class);
        SoHuuTheo soHuuTheo1 = getSoHuuTheoSample1();
        SoHuuTheo soHuuTheo2 = new SoHuuTheo();
        assertThat(soHuuTheo1).isNotEqualTo(soHuuTheo2);

        soHuuTheo2.setId(soHuuTheo1.getId());
        assertThat(soHuuTheo1).isEqualTo(soHuuTheo2);

        soHuuTheo2 = getSoHuuTheoSample2();
        assertThat(soHuuTheo1).isNotEqualTo(soHuuTheo2);
    }
}
