package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TaisannhadatidAsserts.*;
import static vn.vnpt.domain.TaisannhadatidTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaisannhadatidMapperTest {

    private TaisannhadatidMapper taisannhadatidMapper;

    @BeforeEach
    void setUp() {
        taisannhadatidMapper = new TaisannhadatidMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTaisannhadatidSample1();
        var actual = taisannhadatidMapper.toEntity(taisannhadatidMapper.toDto(expected));
        assertTaisannhadatidAllPropertiesEquals(expected, actual);
    }
}
