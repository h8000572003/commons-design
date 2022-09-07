package io.github.h800572003.concurrent;

import java.util.List;

/**
 * 分割功能
 */
public interface IForkWorkService {




    interface IForkOption<T> extends WorkExecutor<T>, WorkAdpaterCallBackend<T> {

        String getName();

        List<T> getData();

        Boolean isContinue(List<T> data);//

        IQueue<T> getQueue();

        int getWorkSize(List<T>data);


    }



    <T> void fork(IForkOption<T> context);
}
