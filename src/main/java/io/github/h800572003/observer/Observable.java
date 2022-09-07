package io.github.h800572003.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> implements IObservable<T> {

    private T data;
    private List<IObserver> observerList = new ArrayList<>();

    public Observable(T data){
        this.data=data;
    }

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(i->i.update(this));
    }

    @Override
    public void add(IObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(IObserver observer) {
        this.observerList.remove(observer);
    }
}
