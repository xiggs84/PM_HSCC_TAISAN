package vn.vnpt.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;

/**
 * Mapper for the entity {@link TaiSan} and its DTO {@link TaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanMapper extends EntityMapper<TaiSanDTO, TaiSan> {
    @Mapping(target = "idTsGocs", source = "idTsGocs", qualifiedByName = "taiSanIdSet")
    @Mapping(target = "idLoaiTs", source = "idLoaiTs", qualifiedByName = "danhMucLoaiTaiSanId")
    @Mapping(target = "idTinhTrang", source = "idTinhTrang", qualifiedByName = "tinhTrangTaiSanId")
    @Mapping(target = "taiSans", source = "taiSans", qualifiedByName = "taiSanIdSet")
    TaiSanDTO toDto(TaiSan s);

    @Mapping(target = "removeIdTsGoc", ignore = true)
    @Mapping(target = "taiSans", ignore = true)
    @Mapping(target = "removeTaiSan", ignore = true)
    TaiSan toEntity(TaiSanDTO taiSanDTO);

    @Named("taiSanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TaiSanDTO toDtoTaiSanId(TaiSan taiSan);

    @Named("taiSanIdSet")
    default Set<TaiSanDTO> toDtoTaiSanIdSet(Set<TaiSan> taiSan) {
        return taiSan.stream().map(this::toDtoTaiSanId).collect(Collectors.toSet());
    }

    @Named("danhMucLoaiTaiSanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DanhMucLoaiTaiSanDTO toDtoDanhMucLoaiTaiSanId(DanhMucLoaiTaiSan danhMucLoaiTaiSan);

    @Named("tinhTrangTaiSanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TinhTrangTaiSanDTO toDtoTinhTrangTaiSanId(TinhTrangTaiSan tinhTrangTaiSan);
}
