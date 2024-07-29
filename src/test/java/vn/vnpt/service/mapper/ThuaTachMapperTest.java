package vn.vnpt.service.mapper;

import static vn.vnpt.domain.ThuaTachAsserts.*;
import static vn.vnpt.domain.ThuaTachTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThuaTachMapperTest {

    private ThuaTachMapper thuaTachMapper;

    @BeforeEach
    void setUp() {
        thuaTachMapper = new ThuaTachMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getThuaTachSample1();
        var actual = thuaTachMapper.toEntity(thuaTachMapper.toDto(expected));
        assertThuaTachAllPropertiesEquals(expected, actual);
    }
}
