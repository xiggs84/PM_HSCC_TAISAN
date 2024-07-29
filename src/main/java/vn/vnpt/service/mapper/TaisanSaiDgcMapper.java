package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.TaisanSaiDgc;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;

/**
 * Mapper for the entity {@link TaisanSaiDgc} and its DTO {@link TaisanSaiDgcDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaisanSaiDgcMapper extends EntityMapper<TaisanSaiDgcDTO, TaisanSaiDgc> {}
