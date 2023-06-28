package com.interview.leetcode;

/**
 * @Author qcl
 * @Description  奇偶链表 力扣328题
 *
 * 给定单链表的头节点 head，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 *
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 *
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *
 *
 *
 * ```
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,3,5,2,4]
 * ```
 *
 * ```
 * 输入: head = [2,1,3,5,6,4,7]
 * 输出: [2,3,6,7,1,5,4]
 * ```
 *
 * @Date 9:54 AM 6/28/2023
 */
public class Q328 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }



    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddTail = oddHead;
        ListNode evenTail = evenHead;

        while (evenTail != null && evenTail.next != null) {
            oddTail.next = evenTail.next;
            oddTail = oddTail.next;
            evenTail.next = oddTail.next;
            evenTail = evenTail.next;
        }

        oddTail.next = evenHead;
        return oddHead;
    }

    public static void main(String[] args) {
        // Test Case 1: [1,2,3,4,5]
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode result1 = oddEvenList(head1);
        printList(result1); // Expected: [1,3,5,2,4]

        // Test Case 2: [2,1,3,5,6,4,7]
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(5);
        head2.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next = new ListNode(4);
        head2.next.next.next.next.next.next = new ListNode(7);
        ListNode result2 = oddEvenList(head2);
        printList(result2); // Expected: [2,3,6,7,1,5,4]
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
