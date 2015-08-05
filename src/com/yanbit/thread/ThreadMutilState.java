
package com.yanbit.thread;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import com.yanbit.anno.Unsafe;

/**
 * @author yanbit
 * 
 * 
 */
public class ThreadMutilState {
	// demo1
	// Thread-0:set number:0
	// Thread-0:set value:0
	// Thread-0 return value:1
	// --------------------------
	// Thread-1:set number:4
	// Thread-1:set value:4
	// Thread-1 return value:5
	// --------------------------
	// Thread-0:set number:0
	// Thread-1:if value:0
	// Thread-1 return value:5
	// --------------------------

	// demo2
	// --------------------------
	// Thread-1:set number:3
	// Thread-0:set number:0
	// Thread-1:set value:3
	// Thread-1 return value:4
	// --------------------------
	// Thread-1:if value:0
	// Thread-1 return value:4
	// --------------------------
	// Thread-0:set value:0
	// Thread-0 return value:1
	// --------------------------

	// thread1-set-number-3 | thread2-set-number->0 | thread1-set-number-value-4 
	// value: number 0
	// value: value 4
	// value: get number 0 return 4
	private AtomicReference<Integer> lastNumber = new AtomicReference<Integer>();
	private AtomicReference<Integer> lastValue = new AtomicReference<Integer>();

	@Unsafe
	public Integer addOne(Integer value) {
		if (value.equals(lastNumber.get())) {
			// if (false) {
			System.out.println(Thread.currentThread().getName() + ":" + "if value:" + lastNumber.get());
			return lastValue.get();
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastNumber.set(value);
			System.out.println(Thread.currentThread().getName() + ":" + "set number:" + value);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastValue.set(value + 1);
			System.out.println(Thread.currentThread().getName() + ":" + "set value:" + value);
			return value + 1;
		}
	}

	public static void main(String[] args) {
		final Random r = new Random();
		final ThreadMutilState t = new ThreadMutilState();
		Thread one = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " return value:" + t.addOne(r.nextInt(1)));
					System.out.println("--------------------------");
				}
			}
		};

		Thread two = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " return value:" + t.addOne(r.nextInt(5)));
					System.out.println("--------------------------");
				}

			}
		};
		one.start();
		two.start();
	}
}
