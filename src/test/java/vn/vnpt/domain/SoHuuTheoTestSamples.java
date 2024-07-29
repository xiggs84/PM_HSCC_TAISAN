package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SoHuuTheoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SoHuuTheo getSoHuuTheoSample1() {
        return new SoHuuTheo().id(1L).idSoHuu(1L).dienGiai("dienGiai1").tenGcn("tenGcn1");
    }

    public static SoHuuTheo getSoHuuTheoSample2() {
        return new SoHuuTheo().id(2L).idSoHuu(2L).dienGiai("dienGiai2").tenGcn("tenGcn2");
    }

    public static SoHuuTheo getSoHuuTheoRandomSampleGenerator() {
        return new SoHuuTheo()
            .id(longCount.incrementAndGet())
            .idSoHuu(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .tenGcn(UUID.randomUUID().toString());
    }
}
