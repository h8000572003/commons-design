package io.github.h800572003.observer;

/**
 * 被觀察者
 */
public interface IObservable<T> {
    /**
     * 取得資料
     * @return
     */
    T getData();


    /**
     * 加入
     * @param observer
     */
    void add(IObserver<T>observer);

    /**
     * 移除觀察者
     * @param observer
     */
    void remove(IObserver<T>observer);

    /**
     * 通知所有觀察者
     */
    void notifyObservers();
}
