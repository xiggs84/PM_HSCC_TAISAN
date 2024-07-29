package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TinhTrangTaiSanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TinhTrangTaiSan getTinhTrangTaiSanSample1() {
        return new TinhTrangTaiSan().id(1L).idTinhTrang(1L).dienGiai("dienGiai1").trangThai(1L);
    }

    public static TinhTrangTaiSan getTinhTrangTaiSanSample2() {
        return new TinhTrangTaiSan().id(2L).idTinhTrang(2L).dienGiai("dienGiai2").trangThai(2L);
    }

    public static TinhTrangTaiSan getTinhTrangTaiSanRandomSampleGenerator() {
        return new TinhTrangTaiSan()
            .id(longCount.incrementAndGet())
            .idTinhTrang(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
