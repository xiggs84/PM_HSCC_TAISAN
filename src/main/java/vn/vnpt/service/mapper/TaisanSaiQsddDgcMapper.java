package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.TaisanSaiQsddDgc;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;

/**
 * Mapper for the entity {@link TaisanSaiQsddDgc} and its DTO {@link TaisanSaiQsddDgcDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaisanSaiQsddDgcMapper extends EntityMapper<TaisanSaiQsddDgcDTO, TaisanSaiQsddDgc> {}
