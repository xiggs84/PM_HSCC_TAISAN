package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaisanSaiQsddDgcAsserts.*;
import static vn.vnpt.domain.TaisanSaiQsddDgcTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaisanSaiQsddDgcMapperTest {

    private TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper;

    @BeforeEach
    void setUp() {
        taisanSaiQsddDgcMapper = new TaisanSaiQsddDgcMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaisanSaiQsddDgcSample1();
        var actual = taisanSaiQsddDgcMapper.toEntity(taisanSaiQsddDgcMapper.toDto(expected));
        assertTaisanSaiQsddDgcAllPropertiesEquals(expected, actual);
    }
}
