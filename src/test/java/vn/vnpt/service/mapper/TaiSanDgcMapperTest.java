package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaiSanDgcAsserts.*;
import static vn.vnpt.domain.TaiSanDgcTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaiSanDgcMapperTest {

    private TaiSanDgcMapper taiSanDgcMapper;

    @BeforeEach
    void setUp() {
        taiSanDgcMapper = new TaiSanDgcMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaiSanDgcSample1();
        var actual = taiSanDgcMapper.toEntity(taiSanDgcMapper.toDto(expected));
        assertTaiSanDgcAllPropertiesEquals(expected, actual);
    }
}
