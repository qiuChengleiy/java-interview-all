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
 * Redis的线程模型采用的是单线程的事件循环模型，也称为I/O多路复用模型。下面将详细介绍Redis的线程模型和实现原理：
 *
 * 1. 单线程模型：
 *    Redis使用单线程来处理所有的客户端请求和数据库操作。这意味着Redis在任何给定的时间只能处理一个请求，但通过事件循环机制，它能够高效地处理大量并发请求。
 *
 * 2. 事件循环：
 *    Redis使用事件循环来实现高并发和低延迟的性能。事件循环是通过I/O多路复用技术实现的，通常使用select、poll或epoll等系统调用。Redis通过监听套接字上的事件，如可读、可写或异常事件，来感知和处理客户端请求。
 *
 * 3. 非阻塞I/O：
 *    Redis使用非阻塞I/O来实现事件循环。在接收到客户端请求时，Redis不会阻塞等待请求完成，而是立即返回到事件循环并继续处理其他请求。当请求的I/O操作完成时，Redis会通过回调函数来处理响应数据。
 *
 * 4. 文件事件：
 *    Redis使用文件事件来表示与客户端或其他网络连接相关的事件。每当有新的客户端连接或数据到达时，Redis会生成相应的文件事件并将其加入到事件队列中。事件循环会从队列中获取文件事件并处理它们。
 *
 * 5. 时间事件：
 *    Redis还支持时间事件，用于执行定时任务。时间事件可以是一次性的，也可以是周期性的。通过时间事件，Redis可以执行诸如过期键的清理、统计信息的更新等后台任务。
 *
 * 6. 非阻塞操作：
 *    Redis的数据存储是基于内存的，因此读写操作通常是非阻塞的。这使得Redis能够在快速的内存访问下提供高性能的读写操作。
 *
 * 7. 多个数据库：
 *    Redis支持多个数据库，每个数据库都有自己的键值空间。在单线程模型下，Redis使用字典来存储数据库，通过索引来快速访问不同的数据库。
 *
 * 通过以上的线程模型，Redis能够高效地处理大量的并发请求，而无需为每个请求创建线程或进程。单线程模型可以避免线程切换和同步开销，提供较低的延迟和较高的吞吐量。同时，Redis通过使用非阻塞I/O和事件驱动的方式，实现了高效的事件处理和资源利用。

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























