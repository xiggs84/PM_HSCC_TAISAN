package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CauHinhThongTinLoaiTaiSanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CauHinhThongTinLoaiTaiSan getCauHinhThongTinLoaiTaiSanSample1() {
        return new CauHinhThongTinLoaiTaiSan()
            .id(1L)
            .idCauHinh(1L)
            .noiDung("noiDung1")
            .javascript("javascript1")
            .css("css1")
            .idLoaiTs(1L)
            .idDonVi(1L)
            .trangThai(1L)
            .xml("xml1");
    }

    public static CauHinhThongTinLoaiTaiSan getCauHinhThongTinLoaiTaiSanSample2() {
        return new CauHinhThongTinLoaiTaiSan()
            .id(2L)
            .idCauHinh(2L)
            .noiDung("noiDung2")
            .javascript("javascript2")
            .css("css2")
            .idLoaiTs(2L)
            .idDonVi(2L)
            .trangThai(2L)
            .xml("xml2");
    }

    public static CauHinhThongTinLoaiTaiSan getCauHinhThongTinLoaiTaiSanRandomSampleGenerator() {
        return new CauHinhThongTinLoaiTaiSan()
            .id(longCount.incrementAndGet())
            .idCauHinh(longCount.incrementAndGet())
            .noiDung(UUID.randomUUID().toString())
            .javascript(UUID.randomUUID().toString())
            .css(UUID.randomUUID().toString())
            .idLoaiTs(longCount.incrementAndGet())
            .idDonVi(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .xml(UUID.randomUUID().toString());
    }
}
