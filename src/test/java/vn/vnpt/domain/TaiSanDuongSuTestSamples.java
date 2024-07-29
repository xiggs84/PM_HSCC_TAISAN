package vn.vnpt.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class TaiSanDuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TaiSanDuongSu getTaiSanDuongSuSample1() {
        return new TaiSanDuongSu().id(1L).trangThai(1L).idDuongSu(1L).idLoaiHopDong(1L).idChungThuc(1L);
    }

    public static TaiSanDuongSu getTaiSanDuongSuSample2() {
        return new TaiSanDuongSu().id(2L).trangThai(2L).idDuongSu(2L).idLoaiHopDong(2L).idChungThuc(2L);
    }

    public static TaiSanDuongSu getTaiSanDuongSuRandomSampleGenerator() {
        return new TaiSanDuongSu()
            .id(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .idDuongSu(longCount.incrementAndGet())
            .idLoaiHopDong(longCount.incrementAndGet())
            .idChungThuc(longCount.incrementAndGet());
    }
}
