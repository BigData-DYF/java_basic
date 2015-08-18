
package com.yanbit.thread;

import com.yanbit.anno.Unsafe;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanbit
 * 
 * 
 */
public class ThreadMutilStateSafeFine {
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

	// Thread1 -set number 0 | Thread2 get 0

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

	/**
	 * 
	 * synchronized two body
	 * 
	 * 
	 * @param value
	 * @return
	 */
	@Unsafe
	public Integer addOne(Integer value) {

		Integer param = null;

		synchronized (this) {
			if (value.equals(lastNumber.get())) {
				// if (false) {
				System.out.println(Thread.currentThread().getName() + ":" + "if value:" + lastNumber.get());
				param = lastValue.get();
				return param;
			}
		}

		if (param == null) {
			synchronized (this) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lastNumber.set(value);
				System.out.println(Thread.currentThread().getName() + ":" + "set number:" + value);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lastValue.set(value + 1);
				System.out.println(Thread.currentThread().getName() + ":" + "set value:" + value);
				param = value + 1;
				return param;
			}

		}
		return param;

	}

	public static void main(String[] args) {
		final Random r = new Random();
		final ThreadMutilStateSafeFine t = new ThreadMutilStateSafeFine();
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
