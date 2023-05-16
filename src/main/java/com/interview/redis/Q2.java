package com.interview.redis;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author qcl
 * @Description
 * @Date 3:32 PM 5/16/2023
 *
 * @请详细讲一下redis的线程模型并讲出实现原理??
 *
 * @Redis是一个高性能的键值存储系统，其线程模型采用单线程的方式。这意味着Redis在任何时候都只会使用单个线程来处理客户端请求和执行命令，而不会使用多个线程。
 *
 *  @Redis之所以采用单线程模型，是因为它主要是通过内存访问来提供高性能的数据存储和处理。内存访问通常是非阻塞的，而磁盘或网络访问则可能是阻塞的。因此，通过使用单线程，Redis能够充分利用内存访问的高效性能，而不受阻塞式操作的影响。
 *
 *  @Redis的实现原理可以简单描述如下：
 *
 *  @1.事件驱动：Redis使用事件驱动的方式来处理客户端请求。它通过一个事件处理器（eventloop）来监听和处理各种事件，包括客户端连接、读写事件、定时器事件等。当一个事件发生时，事件处理器会调用相应的回调函数来处理该事件。
 *
 *  @2.非阻塞IO：Redis使用非阻塞IO来处理客户端请求。它通过设置套接字为非阻塞模式，并使用异步IO操作（如epoll、kqueue等）来实现事件的监听和处理。这样，当一个客户端请求到达时，Redis可以立即处理该请求而无需等待，从而提高了系统的并发性能。
 *
 *  @3.单线程执行：Redis在处理客户端请求时，采用单线程的方式执行命令。这意味着Redis不会并行处理多个请求，而是按照顺序逐个执行。这样做的好处是避免了多线程之间的竞争和同步开销，简化了代码的实现和维护。
 *
 *  @4.非阻塞命令执行：Redis的大部分命令都是非阻塞的。当执行一个非阻塞命令时，Redis会立即返回结果给客户端，并继续处理其他请求。实际的命令执行是由一个任务队列（taskqueue）来处理的，单线程会不断从队列中取出任务并执行。这种方式使得Redis能够同时处理多个客户端请求，并且在一定程度上避免了阻塞问题。
 *
 *  @需要注意的是，尽管Redis的线程模型是单线程的，但它在底层的实现中使用了一些多线程的技术，例如IO多路复用和线程池等，以提高系统的并发性能和可扩展性。这些技术使得Redis能够有效地处理大量的并发请求，并且在高负载情况下保持较低的延迟。
 *
 *
 */
public class Q2 {
    public static void main(String[] args) throws InterruptedException {
        RedisTaskQueue taskQueue = new RedisTaskQueue();
        RedisEventLoop eventLoop = new RedisEventLoop(taskQueue);
        RedisClient client = new RedisClient(taskQueue);

        // 启动事件处理器线程
        Thread eventLoopThread = new Thread(eventLoop);
        eventLoopThread.start();

        // 模拟客户端发送命令
        client.sendCommand("SET key1 value1");
        client.sendCommand("GET key1");
        client.sendCommand("DEL key1");

        // 等待所有命令执行完成
        eventLoopThread.join();

//        Executing Redis command: SET key1 value1
//        Executing Redis command: GET key1
//        Executing Redis command: DEL key1
    }
}


// 模拟Redis命令的执行任务
class RedisCommandTask {
    private String command;

    public RedisCommandTask(String command) {
        this.command = command;
    }

    public void execute() {
        // 模拟命令的执行
        System.out.println("Executing Redis command: " + command);
    }
}

// 模拟Redis的任务队列
class RedisTaskQueue {
    private Queue<RedisCommandTask> queue = new LinkedList<>();

    public synchronized void enqueue(RedisCommandTask task) {
        queue.add(task);
        notify(); // 通知等待中的线程有新任务
    }

    public synchronized RedisCommandTask dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // 如果队列为空，则等待新任务的到来
        }
        return queue.poll();
    }
}



// 模拟Redis的事件处理器
class RedisEventLoop implements Runnable {
    private RedisTaskQueue taskQueue;

    public RedisEventLoop(RedisTaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RedisCommandTask task = taskQueue.dequeue();
                task.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

// 模拟Redis的客户端
class RedisClient {
    private RedisTaskQueue taskQueue;

    public RedisClient(RedisTaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void sendCommand(String command) {
        RedisCommandTask task = new RedisCommandTask(command);
        taskQueue.enqueue(task);
    }
}



//继续上述代码的解释：
//
//在示例中，使用`synchronized`关键字实现了任务队列的线程安全性。`enqueue()`方法用于将任务添加到队列中，并使用`notify()`通知等待中的线程有新任务可执行。`dequeue()`方法则在队列为空时调用`wait()`进入等待状态，直到有新任务到来时被唤醒。
//
//事件处理器线程在`start()`方法中通过循环不断从任务队列中取出任务并执行。如果队列为空，线程会调用`wait()`方法进入等待状态，直到有新任务到来。这样保证了事件处理器在没有任务时不会空转消耗CPU资源。
//
//主程序中创建了一个`RedisClient`实例，并通过`sendCommand()`方法模拟了客户端发送命令的过程。每个命令都被封装成`RedisCommandTask`对象，并通过任务队列传递给事件处理器线程执行。这种方式模拟了Redis的非阻塞命令执行，即命令被添加到任务队列后，客户端可以继续发送其他命令而无需等待命令执行完成。
//
//通过这个简单的示例，我们可以看到单线程模型的特点：Redis使用单线程顺序执行命令，通过任务队列和事件处理器实现命令的异步执行。这种模型的优势在于避免了多线程之间的竞争和同步开销，简化了代码实现，并且能够充分利用内存访问的高效性能。























