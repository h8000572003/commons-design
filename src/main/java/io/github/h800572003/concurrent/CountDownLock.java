package io.github.h800572003.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLock implements ICountDownLock{

    private CountDownLatch latch;
    private Object lock=new Object();

    public CountDownLock(int size){
        this.latch= new CountDownLatch(size);
    }
    @Override
    public void countDown() {
            this.latch.countDown();
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (lock){
            this.latch.await();
        }
    }
    public void setSize(int size){
        synchronized (lock){
            this.latch= new CountDownLatch(size);
        }
    }

}
