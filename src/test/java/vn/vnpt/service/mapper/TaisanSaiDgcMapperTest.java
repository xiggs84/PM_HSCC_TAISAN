package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaisanSaiDgcAsserts.*;
import static vn.vnpt.domain.TaisanSaiDgcTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaisanSaiDgcMapperTest {

    private TaisanSaiDgcMapper taisanSaiDgcMapper;

    @BeforeEach
    void setUp() {
        taisanSaiDgcMapper = new TaisanSaiDgcMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaisanSaiDgcSample1();
        var actual = taisanSaiDgcMapper.toEntity(taisanSaiDgcMapper.toDto(expected));
        assertTaisanSaiDgcAllPropertiesEquals(expected, actual);
    }
}
