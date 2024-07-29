package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;

/**
 * Mapper for the entity {@link TinhTrangTaiSan} and its DTO {@link TinhTrangTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface TinhTrangTaiSanMapper extends EntityMapper<TinhTrangTaiSanDTO, TinhTrangTaiSan> {}
