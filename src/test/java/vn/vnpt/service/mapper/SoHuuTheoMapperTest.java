package vn.vnpt.service.mapper;

import static vn.vnpt.domain.SoHuuTheoAsserts.*;
import static vn.vnpt.domain.SoHuuTheoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoHuuTheoMapperTest {

    private SoHuuTheoMapper soHuuTheoMapper;

    @BeforeEach
    void setUp() {
        soHuuTheoMapper = new SoHuuTheoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSoHuuTheoSample1();
        var actual = soHuuTheoMapper.toEntity(soHuuTheoMapper.toDto(expected));
        assertSoHuuTheoAllPropertiesEquals(expected, actual);
    }
}
