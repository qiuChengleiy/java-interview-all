package com.interview.base;

import javassist.CannotCompileException;
import javassist.ClassPool;

/**
 * @Author qcl
 * @Description
 * @Date 10:23 AM 5/29/2023
 */
public class MetaspaceOOMExample {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();
            int count = 0;

            while (true) {
                String className = "Class" + count++;
                try {
                    classPool.makeClass(className).toClass();
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Metaspace OOM error occurred!" + e.getMessage());
        }
    }
}
