package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CauHinhThongTinLoaiTaiSan;
import vn.vnpt.service.dto.CauHinhThongTinLoaiTaiSanDTO;

/**
 * Mapper for the entity {@link CauHinhThongTinLoaiTaiSan} and its DTO {@link CauHinhThongTinLoaiTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface CauHinhThongTinLoaiTaiSanMapper extends EntityMapper<CauHinhThongTinLoaiTaiSanDTO, CauHinhThongTinLoaiTaiSan> {}
