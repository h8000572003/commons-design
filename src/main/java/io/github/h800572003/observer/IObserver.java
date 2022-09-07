package io.github.h800572003.observer;

/**
 * 觀察者
 * @param <T>
 */
public interface IObserver<T> {

    /**
     * 更新
     * @param IObservable
     */
    void update(IObservable<T> IObservable);
}
