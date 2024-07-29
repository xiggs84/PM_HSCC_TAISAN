package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TaisanSaiDgcTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TaisanSaiDgc getTaisanSaiDgcSample1() {
        return new TaisanSaiDgc().id(1L).idMaster(1L).thongTinTs("thongTinTs1").thongTinTsDung("thongTinTsDung1");
    }

    public static TaisanSaiDgc getTaisanSaiDgcSample2() {
        return new TaisanSaiDgc().id(2L).idMaster(2L).thongTinTs("thongTinTs2").thongTinTsDung("thongTinTsDung2");
    }

    public static TaisanSaiDgc getTaisanSaiDgcRandomSampleGenerator() {
        return new TaisanSaiDgc()
            .id(longCount.incrementAndGet())
            .idMaster(longCount.incrementAndGet())
            .thongTinTs(UUID.randomUUID().toString())
            .thongTinTsDung(UUID.randomUUID().toString());
    }
}
