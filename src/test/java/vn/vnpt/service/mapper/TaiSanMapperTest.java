package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaiSanAsserts.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaiSanMapperTest {

    private TaiSanMapper taiSanMapper;

    @BeforeEach
    void setUp() {
        taiSanMapper = new TaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaiSanSample1();
        var actual = taiSanMapper.toEntity(taiSanMapper.toDto(expected));
        assertTaiSanAllPropertiesEquals(expected, actual);
    }
}
