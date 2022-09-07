package io.github.h800572003.concurrent;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ForkOptions<T> {
    private final IQueue<T> queue;
    private final String name;
    private final Supplier<List<T>> supplier;
    private final Function<List<T>, Boolean> isContinueStrategy;
    private final Function<List<T>, Integer> workSizeStrategy;

    public ForkOptions(IQueue<T> queue,//
                       String name,//
                       Supplier<List<T>> supplier, //
                       Function<List<T>, Boolean> isContinueStrategy,//
                       Function<List<T>, Integer> workSizeStrategy) {//
        this.queue = queue;
        this.name = name;
        this.supplier = supplier;
        this.isContinueStrategy = isContinueStrategy;
        this.workSizeStrategy = workSizeStrategy;
    }

    public IQueue<T> getQueue() {
        return queue;
    }

    public String getName() {
        return name;
    }

    public Supplier<List<T>> getSupplier() {
        return supplier;
    }

    public Function<List<T>, Boolean> getIsContinueStrategy() {
        return isContinueStrategy;
    }

    public Function<List<T>, Integer> getWorkSizeStrategy() {
        return workSizeStrategy;
    }
}
