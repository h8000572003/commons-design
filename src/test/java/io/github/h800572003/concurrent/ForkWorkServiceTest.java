package io.github.h800572003.concurrent;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class ForkWorkServiceTest {


    class MyIForkContext implements IForkWorkService.IForkOption<String> {
        private final List<Integer> tasks;
        private int index = 0;

        private IQueue<String> queues = new ListQueue<>();




        public MyIForkContext(Integer... args) {
            tasks = Lists.newArrayList(args);
        }


        @Override
        public String getName() {
            return "IFORKCONTEXT_";
        }

        @Override
        public List<String> getData() {
            if (index < tasks.size()) {
                List<String> collect = IntStream//
                        .range(0, tasks.get(index++))//
                        .mapToObj(Objects::toString)//
                        .collect(Collectors.toList());
                return collect;//

            } else {
                return new ArrayList<>();
            }
        }

        @Override
        public Boolean isContinue(List<String> data) {
            return data.size() > 1;
        }

        @Override
        public IQueue getQueue() {
            return queues;
        }

        @Override
        public int getWorkSize(List<String> data) {
            if (data.size() > 1000) {
                return 5;
            } else {
                return 3;
            }

        }


        @Override
        public void execute(String s) {
            log.info("s:{}", s);
        }

        @Override
        public void call(String src, Throwable throwable) {
            log.info("s:{} end", src);
        }
    }


    ForkWorkService service = new ForkWorkService();

    @Test
    void testExecute() {
        MyIForkContext context = Mockito.spy(new MyIForkContext(1001, 2, 3, 4, 5));

        //WHEN
        service.fork(context);


        //THEN
        Mockito.verify(context, Mockito.times(1015)).execute(Mockito.anyString());

    }
}