package io.github.h800572003.concurrent;

public interface ICountDownLock {



    /**
     * 下數
     */
    void countDown();

    /**
     * 等待
     * @throws InterruptedException
     */
    void await()throws InterruptedException;

    void setSize(int size);
}
