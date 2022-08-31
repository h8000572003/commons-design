package io.github.h800572003.concurrent;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 賭塞隊伍
 * @param <T>
 */
@Slf4j
public class BlockQueue<T> implements IQueue<T> {
	private final int blockSize;// 最大比數
	private final LinkedList<T> items = Lists.newLinkedList();

	/**
	 * 
	 * @param blockSize
	 *            堵塞筆數
	 */
	public BlockQueue(int blockSize) {
		super();
		this.blockSize = blockSize;
	}

	@Override
	public void add(T item) {
		synchronized (this.items) {
			log.debug("item size:{}",this.items.size());
			while (this.items.size() > this.blockSize) {
				log.debug("add wait");
				try {
					this.items.wait();
				} catch (final InterruptedException e) {
					break;
				}
			}
			log.debug("add {}",item);
			this.items.addLast(item);
			this.items.notifyAll();
		}
	}

	@Override
	public T take() throws InterruptedException {
		synchronized (this.items) {
			while (this.items.isEmpty()) {
				log.debug("take wait");
				this.items.wait();
			}
			T t = this.items.removeFirst();
			log.debug("takse:{} size:{}",t,this.items.size());
			items.notifyAll();
			return  t;
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
		this.remove(src);

	}
}
