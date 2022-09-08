package io.github.h800572003.concurrent;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class ForkWorkServiceTest {


    class MyIForkContext implements IForkWorkService.IForkOption<MyKey> {
        private final List<Integer> tasks;
        private int index = 0;

        private IQueue<MyKey> queues;


        public MyIForkContext(IQueue<MyKey> queues, Integer... args) {
            tasks = Lists.newArrayList(args);
            this.queues = queues;
        }


        @Override
        public String getName() {
            return "IFORKCONTEXT_";
        }

        @Override
        public List<MyKey> getData() {
            if (index < tasks.size()) {
                List<MyKey> collect = IntStream//
                        .range(0, tasks.get(index++))//
                        .mapToObj(MyKey::new)//
                        .collect(Collectors.toList());
                return collect;//

            } else {
                return new ArrayList<>();
            }
        }

        @Override
        public Boolean isContinue(List<MyKey> data) {
            return data.size() > 1;
        }

        @Override
        public IQueue getQueue() {
            return queues;
        }

        @Override
        public int getWorkSize(List<MyKey> data) {
            return 3;

        }


        @Override
        public void execute(MyKey s) {
            log.info("s:{}", s);
        }

        @Override
        public void call(MyKey src, Throwable throwable) {
            log.info("s:{} end", src);
        }
    }


    ForkWorkService service = new ForkWorkService();

    @Test
    void testExecute() {

        MyIForkContext context = Mockito.spy(new MyIForkContext(new ListQueue<>(), 1001, 2, 3, 4, 5));

        //WHEN
        service.fork(context);


        //THEN
        Mockito.verify(context, Mockito.times(1015)).execute(Mockito.any());

    }

    /**
     * 使用順序順序池
     */
    @Test
    void testExecute_orderQueue() {

        //使用key堵塞池
        MyIForkContext context = Mockito.spy(new MyIForkContext(new OrderQueue<>(), 1001, 2, 3, 4, 5));


        //WHEN
        service.fork(context);


        //THEN
        Mockito.verify(context, Mockito.times(1015)).execute(Mockito.any());

    }

    @Test
    void testExecute_blockQueue() {

        //使用key堵塞池
        MyIForkContext context = Mockito.spy(new MyIForkContext(new BlockQueue<>(1), 1001, 2, 3, 4, 5));


        //WHEN
        service.fork(context);


        //THEN
        Mockito.verify(context, Mockito.times(1015)).execute(Mockito.any());

    }

    @Data
    class MyKey implements IBlockKey {
        private int value;

        public MyKey(int value) {
            this.value = value;
        }

        @Override
        public String toKey() {
            return "value";
        }

        @Override
        public String toString() {
            return "MyKey{" +
                    "value=" + value +
                    ",toKey=" + this.toKey() +
                    '}';
        }
    }


}