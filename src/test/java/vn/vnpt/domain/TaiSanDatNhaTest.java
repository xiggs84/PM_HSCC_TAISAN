package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaiSanDatNhaTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDatNhaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDatNha.class);
        TaiSanDatNha taiSanDatNha1 = getTaiSanDatNhaSample1();
        TaiSanDatNha taiSanDatNha2 = new TaiSanDatNha();
        assertThat(taiSanDatNha1).isNotEqualTo(taiSanDatNha2);

        taiSanDatNha2.setId(taiSanDatNha1.getId());
        assertThat(taiSanDatNha1).isEqualTo(taiSanDatNha2);

        taiSanDatNha2 = getTaiSanDatNhaSample2();
        assertThat(taiSanDatNha1).isNotEqualTo(taiSanDatNha2);
    }
}
