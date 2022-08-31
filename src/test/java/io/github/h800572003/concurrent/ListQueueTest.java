package io.github.h800572003.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class ListQueueTest extends BaseQueueTest {

    @BeforeEach
    void init() throws InterruptedException {
        this.setUp(5,new ListQueue<>());
    }

    @Test
     void test() throws InterruptedException {
        assertValue(10);
    }

}
