package io.github.h800572003.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.util.List;

/**
 * 門閂工人服務
 *
 * @param <T> 鎖定物件
 * @author andy tsai
 */
@Slf4j
public class WorkLatchService<T> implements Closeable, IWorkService<T> {

    private ICountDownLock countDownLatch;
    protected final WorkExecutor<T> workExecutor;
    protected final WorkAdpaterCallBackend<T> workAdpaterCallBackend;
    protected final String prefName;// 執行序名稱
    protected final int workSize;

    protected IWorkPool workPool;

    IQueue<T> queue;

    public static <T> WorkLatchService<T> newService(String prefName, IQueue<T> queue, int workSize,
                                                     WorkExecutor<T> workListener, WorkAdpaterCallBackend<T> workAdpaterCallBackend) {
        return new WorkLatchService<>(prefName, queue, workSize, workListener, workAdpaterCallBackend);
    }

    protected WorkLatchService(String prefName, IQueue<T> queue, int workSize, WorkExecutor<T> workListener,
                               WorkAdpaterCallBackend<T> workAdpaterCallBackend) {
        this.prefName = prefName;
        this.workExecutor = workListener;
        this.workAdpaterCallBackend = workAdpaterCallBackend;
        this.workSize = workSize;
        this.queue = queue;
        this.countDownLatch = new CountDownLock(1);


    }

    @Override
    public void close() {
        this.workPool.close();
        log.debug("close down");
    }

    /**
     * 堵住，執行清單完成，才繼續
     */
    @Override
    public void execute(List<? extends T> items) throws InterruptedException {

        new Thread(() -> items.forEach(queue::add)).start();//加入

        this.countDownLatch.setSize(items.size());
        this.workPool = this.createPool(queue);
        this.workPool.start();
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            log.info("get InterruptedException");
            this.workPool.close();//回收工人池
            throw e;
        }
        log.debug("item down done");

    }

    /**
     * 工人池工廠
     *
     * @param queue
     * @return 工人池
     */
    protected IWorkPool createPool(IQueue<T> queue) {
        return new CountDownLatchWorkPool<>(//
                this.prefName, //
                this.workSize, //
                this.workExecutor, //
                this.countDownLatch, //
                queue, //
                this.workAdpaterCallBackend);

    }


}
