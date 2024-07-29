package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucLoaiTaiSanAsserts.*;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucLoaiTaiSanMapperTest {

    private DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper;

    @BeforeEach
    void setUp() {
        danhMucLoaiTaiSanMapper = new DanhMucLoaiTaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucLoaiTaiSanSample1();
        var actual = danhMucLoaiTaiSanMapper.toEntity(danhMucLoaiTaiSanMapper.toDto(expected));
        assertDanhMucLoaiTaiSanAllPropertiesEquals(expected, actual);
    }
}
