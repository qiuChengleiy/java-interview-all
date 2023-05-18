package com.interview.base;

/**
 * @Author qcl
 * @Description
 * @Date 4:15 PM 5/16/2023
 *
 * @请谈一下对Java中synchronize的理解
 *
 * 在Java中，关键字"synchronize"用于实现多线程之间的同步。它可以应用于方法或代码块，并确保在同一时间只有一个线程可以访问被同步的代码段。下面是对Java中"synchronize"的深入理解：
 *
 * 1. 保证原子性：synchronized关键字用于对临界区代码进行加锁，确保多个线程无法同时执行被同步的代码块或方法。这样可以保证代码块或方法中的操作是原子的，
 * 即要么全部执行完毕，要么不执行，从而避免了竞态条件（race condition）的问题。
 *
 * 2. 内置锁（Intrinsic Lock）：在Java中，每个对象都有一个内置的锁，也称为监视器锁或互斥锁。当一个线程进入synchronized代码块时，它必须首先获得对象的内置锁，其他线程必须等待直到锁被释放。这样确保了同一时间只有一个线程能够执行被同步的代码块。
 *
 * 3. 互斥性和可见性：synchronized关键字不仅提供了互斥访问，还确保了可见性。当一个线程获取到锁时，它会清空工作内存中的变量副本，强制从主内存中重新加载变量的值。这样可以确保线程在访问变量时获取最新的值，而不是使用过期的副本。
 *
 * 4. 锁的粒度：
 *    - 对象锁：当使用synchronized修饰实例方法时，锁定的是当前对象实例。这意味着同一时间只有一个线程可以访问该实例的synchronized方法，不同的实例之间互不影响。
 *    - 类锁：当使用synchronized修饰静态方法时，锁定的是整个类对象。这意味着同一时间只有一个线程可以访问该类的任意一个synchronized静态方法，无论是不同实例还是相同实例。
 *
 * 5. 重入性：Java中的synchronized关键字支持重入性。也就是说，如果一个线程已经获得了一个对象的锁，它可以再次获取该对象的锁而不会被阻塞。这种机制允许线程在同步代码块内部调用其他同步方法，避免了自己阻塞自己的情况。
 *
 * 总之，Java中的synchronized关键字提供了一种简单而有效的方法来确保多线程之间的同步和线程安全性。它通过使用内置锁、互斥性和可见性保证了临界区代码的原子性执行，避免了数据竞争和数据不一致的问题。
 *
 */
public class SynchronizeQ {

    /**
     * 同步代码块（Synchronized Blocks）：除了使用synchronized修饰整个方法外，还可以使用synchronized修饰一段代码块，称为同步代码块。这样可以在方法中只对某一部分代码进行同步，而不是整个方法。
     */
    public void someMethod() {
        // 非同步代码
        synchronized (this) {
            // 需要同步的代码
        }
        // 非同步代码
    }

}




