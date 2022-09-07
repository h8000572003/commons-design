package io.github.h800572003.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ForkWorkService implements IForkWorkService {


    @Override
    public <T> void fork(IForkOption<T> context) {//
        final List<T> tasks = new ArrayList<>();
        tasks.addAll(context.getData());
        if (tasks.isEmpty()) {
            log.info("no tasks..");
        } else {
            try (final WorkLatchService<T> workLatchService = new WorkLatchService<>(
                    context.getName(),//
                    context.getQueue(),
                    context.getWorkSize(tasks),//
                    context,
                    context
            )) {
                while (true){
                    try {
                        workLatchService.execute(tasks);
                    } finally {
                        tasks.clear();
                    }
                    tasks.addAll(context.getData());
                    if(!context.isContinue(tasks)){
                        log.info("break execute.");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new ForkException(e.getMessage(),e);
            }
        }

    }
}
