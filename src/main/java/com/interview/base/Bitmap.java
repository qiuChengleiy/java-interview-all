package com.interview.base;

/**
 * @Author qcl
 * @Description
 * @Date 10:06 AM 5/24/2023
 */
public class Bitmap {
    private int[] bitmap;

    public Bitmap(int size) {
        bitmap = new int[size];
    }

    public void setBit(int index) {
        int arrayIndex = index / 32;
        int bitOffset = index % 32;
        bitmap[arrayIndex] |= (1 << bitOffset);
    }

    public boolean getBit(int index) {
        int arrayIndex = index / 32;
        int bitOffset = index % 32;
        return (bitmap[arrayIndex] & (1 << bitOffset)) != 0;
    }

    public void expandBitmap(int newSize) {
        if (newSize <= bitmap.length * 32) {
            return;
        }

        int[] newBitmap = new int[newSize / 32 + 1];
        System.arraycopy(bitmap, 0, newBitmap, 0, bitmap.length);
        bitmap = newBitmap;
    }

    public static void main(String[] args) {
        int size = 64;
        Bitmap bitmap = new Bitmap(size);

        bitmap.setBit(5);
        bitmap.setBit(10);
        bitmap.setBit(30);
        bitmap.setBit(300);

        System.out.println("Bit 5: " + bitmap.getBit(5));
        System.out.println("Bit 10: " + bitmap.getBit(10));
        System.out.println("Bit 30: " + bitmap.getBit(30));
        System.out.println("Bit 20: " + bitmap.getBit(20));
        System.out.println("Bit 300: " + bitmap.getBit(300));

        int newSize = 128;
        bitmap.expandBitmap(newSize);

        System.out.println("Bit 120: " + bitmap.getBit(120));
    }
}

