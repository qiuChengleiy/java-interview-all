package com.interview.base;

/**
 * @Author qcl
 * @Description 锁升级过程
 * @Date 10:13 AM 5/18/2023
 */
public class LockLevel {
}

//锁升级是指在Java中，synchronized关键字在不同的情况下可以升级使用不同级别的锁，以提高性能和减少开销。Java中的锁升级主要涉及到偏向锁、轻量级锁和重量级锁这三个级别。下面详细介绍每个级别及其升级过程。
//
//        1. 偏向锁（Biased Locking）：
//        偏向锁是指当只有一个线程访问同步块时，为了减少同步开销，JVM会自动将对象的锁记录下来，标记为偏向锁状态。当其他线程访问该同步块时，不需要竞争锁，而是直接获取锁。
//
//        锁升级过程：
//        - 初始状态：对象没有锁标记。
//        - 偏向锁申请：当第一个线程访问同步块时，JVM将该线程ID记录在对象头部，并将对象的标记状态设置为偏向锁。
//        - 偏向锁撤销：当其他线程尝试获取锁时，发现对象的偏向锁被占用，会撤销偏向锁，升级为轻量级锁。
//
//        Java代码示例：

class BiasedLockExample {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            // 同步块
        }
    }
}


//           2. 轻量级锁（Lightweight Locking）：
//           轻量级锁是指当多个线程轻度竞争同步块时，JVM会将对象的锁记录在线程的栈帧中，而不是在对象头中。线程在进入同步块之前，通过CAS（比较并交换）操作尝试获取锁。如果CAS成功，则表示获取锁成功，进入同步块；如果CAS失败，表示存在竞争，升级为重量级锁。
//
//           锁升级过程：
//           - 初始状态：对象没有锁标记。
//           - 轻量级锁申请：第一个线程进入同步块时，JVM将锁的记录信息复制到线程的栈帧中，并将对象的标记状态设置为轻量级锁。
//           - 轻量级锁竞争：当其他线程尝试获取锁时，会使用CAS操作来竞争锁。如果CAS成功，表示获取锁成功，进入同步块；如果CAS失败，表示存在竞争，升级为重量级锁。

class LightweightLockExample {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            // 同步块
        }
    }
}

//           3. 重量级锁（Heavyweight Locking）：
//           重量级锁是指当多个线程激烈竞争同步块时，JVM会将对象的锁升级为重量级锁，使用操作系统提供的互斥量来实现锁机制。重量级锁涉及到线程的阻塞和唤醒操作，开销较大。
//
//           锁升级过程：
//           - 初始状态：对象没有锁标记。
//           - 重量级锁申请：当多个线程轮流竞争同步块时，锁会直接升级为重量级锁，通过操作系统提供的互斥量来实现锁机制。


class HeavyweightLockExample {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            // 同步块
        }
    }
}


//           需要注意的是，锁升级的过程是由JVM自动完成的，开发人员无需显式地控制锁升级。JVM会根据同步竞争的情况来自动选择合适的锁级别，以提供更好的性能和效率。



































