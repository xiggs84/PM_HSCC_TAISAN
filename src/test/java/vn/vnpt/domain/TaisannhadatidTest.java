package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaisannhadatidTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisannhadatidTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taisannhadatid.class);
        Taisannhadatid taisannhadatid1 = getTaisannhadatidSample1();
        Taisannhadatid taisannhadatid2 = new Taisannhadatid();
        assertThat(taisannhadatid1).isNotEqualTo(taisannhadatid2);

        taisannhadatid2.setId(taisannhadatid1.getId());
        assertThat(taisannhadatid1).isEqualTo(taisannhadatid2);

        taisannhadatid2 = getTaisannhadatidSample2();
        assertThat(taisannhadatid1).isNotEqualTo(taisannhadatid2);
    }
}
