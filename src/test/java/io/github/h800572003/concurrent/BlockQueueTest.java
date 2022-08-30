package io.github.h800572003.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockQueueTest extends BaseQueueTest {

    @BeforeEach
    void init(){
        this.setUp(5,new BlockQueue<>(1));
    }

    @Test
    void test() throws InterruptedException {
        assertValue(10);
    }

}