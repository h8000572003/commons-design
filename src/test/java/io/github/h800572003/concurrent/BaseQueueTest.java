package io.github.h800572003.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BaseQueueTest {

    protected IQueue<String> queues;
    protected CountDownLatch countDownLatch;
    protected AtomicInteger integer = new AtomicInteger();

    protected  int size;


    void setUp(int size,IQueue<String> queues) {
        this.size=size;
        this.queues = queues;
        IntStream.range(0,
                        size).mapToObj(i -> String.format("%d", i))
                .forEach(queues::add);


    }
    void assertValue(int expected) throws InterruptedException {

        this.countDownLatch=new CountDownLatch(queues.size());

        new AtomicThread("work1", this.queues,countDownLatch,integer).start();
        new AtomicThread("work2", this.queues,countDownLatch,integer).start();
        new AtomicThread("work3", this.queues,countDownLatch,integer).start();
        new AtomicThread("work4", this.queues,countDownLatch,integer).start();


        this.countDownLatch.await();



        int i = integer.get();
        assertThat(i).isEqualTo(expected);
        log.info("i:{}",i);
    }

    @Slf4j
    static class AtomicThread extends Thread{
        private IQueue<String>queues;
        private CountDownLatch countDownLatch;

        protected AtomicInteger integer;
        public AtomicThread(String name,IQueue<String>queues,CountDownLatch countDownLatch,AtomicInteger integer) {
            super(name);
            this.queues=queues;
            this.countDownLatch=countDownLatch;
            this.integer=integer;
        }

        @Override
        public void run() {
            while (!queues.isEmpty()){
                String take = null;
                try {
                    take = queues.take();
                    this.integer.addAndGet(Integer.parseInt(take));
                    this. countDownLatch.countDown();
                } catch (InterruptedException e) {
                    log.info("error",e);
                }
                log.info("take:{}",take);
            }
        }
    }
}
