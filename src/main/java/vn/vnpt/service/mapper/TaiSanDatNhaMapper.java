package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.TaiSanDatNha;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;

/**
 * Mapper for the entity {@link TaiSanDatNha} and its DTO {@link TaiSanDatNhaDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanDatNhaMapper extends EntityMapper<TaiSanDatNhaDTO, TaiSanDatNha> {}
