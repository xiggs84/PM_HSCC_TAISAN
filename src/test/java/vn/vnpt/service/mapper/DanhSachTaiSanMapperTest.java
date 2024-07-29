package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhSachTaiSanAsserts.*;
import static vn.vnpt.domain.DanhSachTaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhSachTaiSanMapperTest {

    private DanhSachTaiSanMapper danhSachTaiSanMapper;

    @BeforeEach
    void setUp() {
        danhSachTaiSanMapper = new DanhSachTaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhSachTaiSanSample1();
        var actual = danhSachTaiSanMapper.toEntity(danhSachTaiSanMapper.toDto(expected));
        assertDanhSachTaiSanAllPropertiesEquals(expected, actual);
    }
}
