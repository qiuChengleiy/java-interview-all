package com.interview.redis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Author qcl
 * @Description
 * @Date 10:15 AM 5/17/2023
 *
 * @请详细讲一下redis的非阻塞式IO和多路复用,并讲出它的实现原理
 *
 *
 * Redis使用非阻塞式I/O和多路复用技术来实现高性能的事件驱动模型。下面将详细介绍Redis的非阻塞式I/O和多路复用，并解释其实现原理：
 *
 * 1. 非阻塞式I/O：
 *    非阻塞式I/O是一种I/O操作的模式，在进行读取或写入操作时，不会阻塞线程等待操作完成。Redis使用非阻塞I/O来实现事件驱动模型，它的实现原理如下：
 *
 *    - 设置套接字为非阻塞模式：Redis在接受客户端连接或创建套接字时，将套接字设置为非阻塞模式。这样，在进行读取或写入操作时，不会阻塞线程，而是立即返回。
 *
 *    - 非阻塞的读取操作：当Redis执行读取操作时，它会检查套接字上是否有数据可读。如果没有数据可读，读取操作会立即返回，避免线程阻塞。Redis可以使用非阻塞的方式读取数据，以便处理其他事件。
 *
 *    - 非阻塞的写入操作：当Redis执行写入操作时，它会检查套接字是否可写。如果套接字不可写，写入操作会立即返回，避免线程阻塞。Redis可以使用非阻塞的方式写入数据，以便处理其他事件。
 *
 * 2. 多路复用：
 *    多路复用是一种I/O多路复用技术，用于同时监视多个套接字上的事件。Redis使用多路复用技术来管理多个套接字上的事件，以实现高效的事件驱动模型。下面是多路复用的实现原理：
 *
 *    - 注册事件和套接字：Redis将需要监听的套接字注册到多路复用器中，并指定感兴趣的事件类型，如可读、可写或异常。
 *
 *    - 多路复用器监听事件：多路复用器负责监听所有已注册套接字上的事件。它使用系统调用（如select、poll或epoll）来监视套接字上的事件状态。
 *
 *    - 事件就绪通知：当套接字上的事件状态发生变化时，多路复用器会通知Redis。这样，Redis可以及时处理事件。
 *
 *    - 事件处理：一旦事件就绪，Redis会调用相应的事件处理函数来处理事件。例如，如果有可读事件就绪，Redis会调用读取数据的函数来处理请求。
 *
 * 通过非阻塞式I/O和多路复用的组合，Redis实现了高效的事件驱动模型。非阻塞式I/O使得Redis能够在进行读取或写入操作时不会阻塞线程，提高了并发处理能力。多路复用技术
 *
 * 允许Redis同时监听多个套接字上的事件，提供高效的事件管理和触发能力。这种组合使得Redis能够处理大量并发请求，提供高性能和低延迟的服务。
 *
 *
 *
 */
public class Q3 {
    /**
     *  使用Java的Selector类实现了非阻塞式I/O和多路复用的原理：
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = clientChannel.read(buffer);
                    if (bytesRead > 0) {
                        buffer.flip();
                        byte[] data = new byte[buffer.remaining()];
                        buffer.get(data);
                        String message = new String(data);
                        System.out.println("Received message: " + message);
                    }
                    clientChannel.close();
                }
            }
        }
    }
}


