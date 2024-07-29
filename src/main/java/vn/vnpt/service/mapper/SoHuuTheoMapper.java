package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.SoHuuTheo;
import vn.vnpt.service.dto.SoHuuTheoDTO;

/**
 * Mapper for the entity {@link SoHuuTheo} and its DTO {@link SoHuuTheoDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoHuuTheoMapper extends EntityMapper<SoHuuTheoDTO, SoHuuTheo> {}
