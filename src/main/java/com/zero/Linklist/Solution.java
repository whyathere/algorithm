package com.zero.Linklist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
     ListNode head = new ListNode(1);
     head.next = new ListNode(2);
     head.next.next = new ListNode(3);
        System.out.println(head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);
//        System.out.println(head.next.next);
        ListNode rev = reverseList(head);
        System.out.println(rev.val);
        System.out.println(rev.next.val);
        System.out.println(rev.next.next.val);
    }

    public  static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;   //temp 2 3 n
            curr.next = prev;   //curr 1 n
            prev = curr;        //
            curr = nextTemp;
        }
        /*
        * 1.
        *    temp
        *    prev
        *    curr 2 3 n
        * 2. temp 3 n
        *    prev 2 1 n
        *    curr 3 n
        * 3. temp n
        *    curr.nex 2 1 n
        *    prev = curr = 3 2 1 n
        *
        *
        *
        *
        * */
        return prev;
    }
}
