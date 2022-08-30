package io.github.h800572003.concurrent;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * 一般Queue
 * @param <T>
 */
public class ListQueue<T> implements IQueue<T> {
	private final LinkedList<T> items = Lists.newLinkedList();


	public ListQueue() {
		super();
	}

	@Override
	public void add(T item) {
		synchronized (this.items) {
			this.items.addLast(item);
			this.items.notifyAll();
		}
	}

	@Override
	public T take() throws InterruptedException {
		synchronized (this.items) {
			while (this.items.isEmpty()) {
				this.items.wait();
			}
			return this.items.removeFirst();
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (this.items) {
			return this.items.isEmpty();
		}
	}

	@Override
	public int size() {
		synchronized (this.items) {
			return this.items.size();
		}
	}

	@Override
	public void remove(T src) {
		items.remove(src);
	}
}
