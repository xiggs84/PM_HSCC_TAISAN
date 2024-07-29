package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.TaiSanDgc;
import vn.vnpt.service.dto.TaiSanDgcDTO;

/**
 * Mapper for the entity {@link TaiSanDgc} and its DTO {@link TaiSanDgcDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanDgcMapper extends EntityMapper<TaiSanDgcDTO, TaiSanDgc> {}
