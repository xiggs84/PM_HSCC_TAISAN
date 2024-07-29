package vn.vnpt.service.mapper;

import static vn.vnpt.domain.CauHinhThongTinLoaiTaiSanAsserts.*;
import static vn.vnpt.domain.CauHinhThongTinLoaiTaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CauHinhThongTinLoaiTaiSanMapperTest {

    private CauHinhThongTinLoaiTaiSanMapper cauHinhThongTinLoaiTaiSanMapper;

    @BeforeEach
    void setUp() {
        cauHinhThongTinLoaiTaiSanMapper = new CauHinhThongTinLoaiTaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCauHinhThongTinLoaiTaiSanSample1();
        var actual = cauHinhThongTinLoaiTaiSanMapper.toEntity(cauHinhThongTinLoaiTaiSanMapper.toDto(expected));
        assertCauHinhThongTinLoaiTaiSanAllPropertiesEquals(expected, actual);
    }
}
