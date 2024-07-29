package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TaisannhadatidTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Taisannhadatid getTaisannhadatidSample1() {
        return new Taisannhadatid().id(1L).idTaiSan(1L).thongTinTs("thongTinTs1");
    }

    public static Taisannhadatid getTaisannhadatidSample2() {
        return new Taisannhadatid().id(2L).idTaiSan(2L).thongTinTs("thongTinTs2");
    }

    public static Taisannhadatid getTaisannhadatidRandomSampleGenerator() {
        return new Taisannhadatid()
            .id(longCount.incrementAndGet())
            .idTaiSan(longCount.incrementAndGet())
            .thongTinTs(UUID.randomUUID().toString());
    }
}
