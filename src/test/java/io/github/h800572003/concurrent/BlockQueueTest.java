package io.github.h800572003.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockQueueTest extends BaseQueueTest {

    @BeforeEach
    void init() throws InterruptedException {
        this.setUp(5,new BlockQueue<>(1));
    }

    @Test
    void test() throws InterruptedException {
        assertValue(10);
    }

}