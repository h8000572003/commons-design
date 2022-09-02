package io.github.h800572003.concurrent;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ListQueueTest {

    private IQueue<String> blockQueue = new ListQueue<>();
    Thread addThread = new Thread();


    private List<String> lines;

    int size = 10;

    @BeforeEach
    void init() throws InterruptedException {

        this.lines = IntStream.range(0,
                10).mapToObj(i -> String.format("%d", i)).collect(Collectors.toList());
        this.addThread = new Thread(() -> this.lines
                .forEach(blockQueue::add));
        this.addThread.start();

    }

    /**
     * GIVE 0~9檔案,block size 3
     * WHEN add
     * THEN give 0~9檔案
     *
     * @throws InterruptedException
     */
    @Test
    void test() throws InterruptedException {

        final List<String> getDatas = Lists.newCopyOnWriteArrayList();//tak
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    try {
                        String take = blockQueue.take();
                        log.info("task:{}", take);
                        getDatas.add(take);
                        TimeUnit.SECONDS.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        log.info("InterruptedException");
                    }
                }
            }
        });
        thread.start();
        thread.join();
        log.info("end ");

        assertThat(getDatas).contains(lines.toArray(new String[]{}));
        assertThat(getDatas).hasSize(size);
    }

}
