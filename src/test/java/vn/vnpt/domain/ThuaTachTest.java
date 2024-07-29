package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ThuaTachTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThuaTachTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThuaTach.class);
        ThuaTach thuaTach1 = getThuaTachSample1();
        ThuaTach thuaTach2 = new ThuaTach();
        assertThat(thuaTach1).isNotEqualTo(thuaTach2);

        thuaTach2.setId(thuaTach1.getId());
        assertThat(thuaTach1).isEqualTo(thuaTach2);

        thuaTach2 = getThuaTachSample2();
        assertThat(thuaTach1).isNotEqualTo(thuaTach2);
    }
}
