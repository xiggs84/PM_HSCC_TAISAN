package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.Taisannhadatid;
import vn.vnpt.service.dto.TaisannhadatidDTO;

/**
 * Mapper for the entity {@link Taisannhadatid} and its DTO {@link TaisannhadatidDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaisannhadatidMapper extends EntityMapper<TaisannhadatidDTO, Taisannhadatid> {}
