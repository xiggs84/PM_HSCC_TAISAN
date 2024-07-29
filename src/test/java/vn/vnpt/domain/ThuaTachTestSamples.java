package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ThuaTachTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ThuaTach getThuaTachSample1() {
        return new ThuaTach().id(1L).idThuaTach(1L).idTaiSan(1L).thongTinThuaTach("thongTinThuaTach1").trangThai(1L);
    }

    public static ThuaTach getThuaTachSample2() {
        return new ThuaTach().id(2L).idThuaTach(2L).idTaiSan(2L).thongTinThuaTach("thongTinThuaTach2").trangThai(2L);
    }

    public static ThuaTach getThuaTachRandomSampleGenerator() {
        return new ThuaTach()
            .id(longCount.incrementAndGet())
            .idThuaTach(longCount.incrementAndGet())
            .idTaiSan(longCount.incrementAndGet())
            .thongTinThuaTach(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
