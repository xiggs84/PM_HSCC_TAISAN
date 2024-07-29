package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaiSanDatNhaAsserts.*;
import static vn.vnpt.domain.TaiSanDatNhaTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaiSanDatNhaMapperTest {

    private TaiSanDatNhaMapper taiSanDatNhaMapper;

    @BeforeEach
    void setUp() {
        taiSanDatNhaMapper = new TaiSanDatNhaMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaiSanDatNhaSample1();
        var actual = taiSanDatNhaMapper.toEntity(taiSanDatNhaMapper.toDto(expected));
        assertTaiSanDatNhaAllPropertiesEquals(expected, actual);
    }
}
