package vn.vnpt.service.mapper;

import static vn.vnpt.domain.TinhTrangTaiSanAsserts.*;
import static vn.vnpt.domain.TinhTrangTaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TinhTrangTaiSanMapperTest {

    private TinhTrangTaiSanMapper tinhTrangTaiSanMapper;

    @BeforeEach
    void setUp() {
        tinhTrangTaiSanMapper = new TinhTrangTaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTinhTrangTaiSanSample1();
        var actual = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanMapper.toDto(expected));
        assertTinhTrangTaiSanAllPropertiesEquals(expected, actual);
    }
}
