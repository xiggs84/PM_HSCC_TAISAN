package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TaiSanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TaiSan getTaiSanSample1() {
        return new TaiSan()
            .id(1L)
            .idTaiSan(1L)
            .tenTaiSan("tenTaiSan1")
            .trangThai(1L)
            .thongTinTs("thongTinTs1")
            .ghiChu("ghiChu1")
            .nguoiThaoTac(1L)
            .idDuongSu(1L)
            .idTsGoc(1L)
            .maTaiSan("maTaiSan1")
            .idLoaiNganChan(1L)
            .idMaster(1L)
            .strSearch("strSearch1")
            .idDonVi(1L)
            .soHsCv(1L)
            .soCc(1L)
            .soVaoSo(1L)
            .moTa("moTa1")
            .loaiNganChan(1L)
            .syncStatus(1L);
    }

    public static TaiSan getTaiSanSample2() {
        return new TaiSan()
            .id(2L)
            .idTaiSan(2L)
            .tenTaiSan("tenTaiSan2")
            .trangThai(2L)
            .thongTinTs("thongTinTs2")
            .ghiChu("ghiChu2")
            .nguoiThaoTac(2L)
            .idDuongSu(2L)
            .idTsGoc(2L)
            .maTaiSan("maTaiSan2")
            .idLoaiNganChan(2L)
            .idMaster(2L)
            .strSearch("strSearch2")
            .idDonVi(2L)
            .soHsCv(2L)
            .soCc(2L)
            .soVaoSo(2L)
            .moTa("moTa2")
            .loaiNganChan(2L)
            .syncStatus(2L);
    }

    public static TaiSan getTaiSanRandomSampleGenerator() {
        return new TaiSan()
            .id(longCount.incrementAndGet())
            .idTaiSan(longCount.incrementAndGet())
            .tenTaiSan(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet())
            .thongTinTs(UUID.randomUUID().toString())
            .ghiChu(UUID.randomUUID().toString())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idDuongSu(longCount.incrementAndGet())
            .idTsGoc(longCount.incrementAndGet())
            .maTaiSan(UUID.randomUUID().toString())
            .idLoaiNganChan(longCount.incrementAndGet())
            .idMaster(longCount.incrementAndGet())
            .strSearch(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .soHsCv(longCount.incrementAndGet())
            .soCc(longCount.incrementAndGet())
            .soVaoSo(longCount.incrementAndGet())
            .moTa(UUID.randomUUID().toString())
            .loaiNganChan(longCount.incrementAndGet())
            .syncStatus(longCount.incrementAndGet());
    }
}
