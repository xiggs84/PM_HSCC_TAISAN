package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaiSanDuongSuAsserts.*;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaiSanDuongSuMapperTest {

    private TaiSanDuongSuMapper taiSanDuongSuMapper;

    @BeforeEach
    void setUp() {
        taiSanDuongSuMapper = new TaiSanDuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaiSanDuongSuSample1();
        var actual = taiSanDuongSuMapper.toEntity(taiSanDuongSuMapper.toDto(expected));
        assertTaiSanDuongSuAllPropertiesEquals(expected, actual);
    }
}
