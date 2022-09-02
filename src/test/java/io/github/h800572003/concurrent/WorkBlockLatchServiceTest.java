package io.github.h800572003.concurrent;

import io.github.h800572003.concurrent.WorkBlockLatchServiceTest.BlockItem;
import io.github.h800572003.exception.ApBusinessException;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class WorkBlockLatchServiceTest implements WorkExecutor<BlockItem>, WorkAdpaterCallBackend<BlockItem> {
    Integer value = 0;
    static Object MUTE = new Object();

    @EqualsAndHashCode
    @ToString
    static class BlockItem implements IBlockKey {

        int workExe = 1;
        String key = "1";

        public BlockItem(int workExe, String key) {
            super();
            this.workExe = workExe;
            this.key = key;
        }

        public BlockItem(int workExe) {
            this(workExe, workExe + "");
        }

        @Override
        public String toKey() {
            return this.key + "";
        }

    }

    @BeforeEach
    void before() {
        value = 0;
    }

    /**
     * GIVE 10次 1~10筆任務
     * THEN execute
     * 執行次數總計 10 * 10
     */
    @Test
    void testExecuteWhenＥxecute() {
        int loopTime = execute(3);
        log.info("value:{}", value);

        //THEN
        assertThat(value).isEqualTo(loopTime * 10);
    }

    private int execute(int workSize) {
        int loopTime = 10;// loop 次數
        ListQueue<BlockItem> blockQueue = new ListQueue<>();
        try (WorkLatchService<BlockItem> newService = WorkLatchService.newService(//
                "WORK_", //
                blockQueue,//
                workSize, ///
                this, //
                this)) {//

            //WHEN
            execute(loopTime, newService);
        }
        return loopTime;
    }

    @Test
    void giveInteruptWhenExecuteThenClosePoool() throws InterruptedException {

        Thread thread = new Thread(() -> execute(3));
        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("call interrupt");
        thread.interrupt();
        thread.join();
        log.info("end");
        assertThat(value).isGreaterThan(0);
        assertThat(value).isLessThan(100);
//        TimeUnit.SECONDS.sleep(3);
    }

    private static void execute(int i1, WorkLatchService<BlockItem> newService) {
        try {
            for (int i = 0; i < i1; i++) {
                final List<BlockItem> items = IntStream//
                        .range(0, 10)//
                        .mapToObj(BlockItem::new)//
                        .collect(Collectors.toList());//
                newService.execute(items);
                log.info("task" + i);
            }
        } catch (InterruptedException e) {
            log.info("任務中斷");
        }
    }


    @Override
    public void execute(BlockItem t) {
        synchronized (MUTE) {
            log.info("start exeucte item:{}", t);
            value++;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new ApBusinessException("該作業中斷{0}",t,e);
            }
        }


    }

    @Override
    public void call(BlockItem src, Throwable throwable) {
        log.info("done exeucte item:{}", src,throwable);

    }

}
