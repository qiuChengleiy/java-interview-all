package com.interview.redis;

/**
 * @Author qcl
 * @Description
 * @Date 2:41 PM 5/16/2023
 *
 *  @介绍一下redis的架构?
 *
 * @Redis是一种开源的内存数据存储系统，常用于缓存和消息队列以及NoSQL数据库。它具有高性能、灵活性、可扩展性、可靠性等特点。
 * @Redis的架构采用了单线程的模型，但其单线程并不是指处理请求的线程，而是指所有请求都在同一个线程中执行。Redis采用了多路复用机制，
 * @即通过I/O多路复用技术，将多个客户端的I/O请求复用到单个线程中，同时采用了单线程阻塞式I/O模型，保证了数据的一致性。
 *
 *  @Redis的架构主要包括以下组成部分：
 *  @1.客户端(Client)：向Redis发送命令请求，并接收Redis返回的结果。
 *  @2.网络层(Network)：接收和发送客户端的请求和响应。
 *  @3.I/O复用器(I/OMultiplexer)：处理客户端的I/O请求的多路复用器。
 *  @4.命令请求处理器(Command Request Processor)：处理客户端的命令请求。
 *  @5.数据库(Database)：存储数据的实际结构。
 *  @6.持久化(Persistence)：将内存中的数据持久化到磁盘中。
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Q1 {
}
