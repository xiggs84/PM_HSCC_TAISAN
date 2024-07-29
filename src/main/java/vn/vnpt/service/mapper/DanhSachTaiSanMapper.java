package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.DanhSachTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;

/**
 * Mapper for the entity {@link DanhSachTaiSan} and its DTO {@link DanhSachTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhSachTaiSanMapper extends EntityMapper<DanhSachTaiSanDTO, DanhSachTaiSan> {
    @Mapping(target = "idLoaiTs", source = "idLoaiTs", qualifiedByName = "danhMucLoaiTaiSanId")
    DanhSachTaiSanDTO toDto(DanhSachTaiSan s);

    @Named("danhMucLoaiTaiSanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DanhMucLoaiTaiSanDTO toDtoDanhMucLoaiTaiSanId(DanhMucLoaiTaiSan danhMucLoaiTaiSan);
}
