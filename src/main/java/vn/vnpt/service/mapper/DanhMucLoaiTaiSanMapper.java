package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;

/**
 * Mapper for the entity {@link DanhMucLoaiTaiSan} and its DTO {@link DanhMucLoaiTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucLoaiTaiSanMapper extends EntityMapper<DanhMucLoaiTaiSanDTO, DanhMucLoaiTaiSan> {}
