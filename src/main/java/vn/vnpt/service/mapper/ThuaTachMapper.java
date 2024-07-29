package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.ThuaTach;
import vn.vnpt.service.dto.ThuaTachDTO;

/**
 * Mapper for the entity {@link ThuaTach} and its DTO {@link ThuaTachDTO}.
 */
@Mapper(componentModel = "spring")
public interface ThuaTachMapper extends EntityMapper<ThuaTachDTO, ThuaTach> {}
