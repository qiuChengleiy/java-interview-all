package com.interview.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qcl
 * @Description
 * @Date 10:13 AM 5/29/2023
 */
public class OOMExample {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        try {
            while (true) {
                byte[] arr = new byte[1024 * 1024]; // 分配1MB的字节数组
                list.add(arr);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OOM error occurred!");
            // OOM error occurred!
        }
    }
}

