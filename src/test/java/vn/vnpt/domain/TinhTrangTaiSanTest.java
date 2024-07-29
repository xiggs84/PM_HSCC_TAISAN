package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.TinhTrangTaiSanTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TinhTrangTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhTrangTaiSan.class);
        TinhTrangTaiSan tinhTrangTaiSan1 = getTinhTrangTaiSanSample1();
        TinhTrangTaiSan tinhTrangTaiSan2 = new TinhTrangTaiSan();
        assertThat(tinhTrangTaiSan1).isNotEqualTo(tinhTrangTaiSan2);

        tinhTrangTaiSan2.setId(tinhTrangTaiSan1.getId());
        assertThat(tinhTrangTaiSan1).isEqualTo(tinhTrangTaiSan2);

        tinhTrangTaiSan2 = getTinhTrangTaiSanSample2();
        assertThat(tinhTrangTaiSan1).isNotEqualTo(tinhTrangTaiSan2);
    }

    @Test
    void taiSanTest() {
        TinhTrangTaiSan tinhTrangTaiSan = getTinhTrangTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        tinhTrangTaiSan.addTaiSan(taiSanBack);
        assertThat(tinhTrangTaiSan.getTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getIdTinhTrang()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.removeTaiSan(taiSanBack);
        assertThat(tinhTrangTaiSan.getTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getIdTinhTrang()).isNull();

        tinhTrangTaiSan.taiSans(new HashSet<>(Set.of(taiSanBack)));
        assertThat(tinhTrangTaiSan.getTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getIdTinhTrang()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.setTaiSans(new HashSet<>());
        assertThat(tinhTrangTaiSan.getTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getIdTinhTrang()).isNull();
    }
}
