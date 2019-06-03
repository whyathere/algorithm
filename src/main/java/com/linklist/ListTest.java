package com.linklist;

public class ListTest {

    public static void main(String[] args) {
        ListNode data = new ListNode(0);
//        data.next=new ListNode(1);
//        data.next.next=new ListNode(2);
//        data.next.next.next=new ListNode(3);
//        data.next.next.next.next=new ListNode(4);
//        data.next.next.next.next.next=new ListNode(5);

        ListNode pre = null;

        for (int i =1;i<10;i++){
            
        }

        ListNode node = SolutionOfReverseList.reverseList(data);

        ListNode tempNode = node;
        while (tempNode.val >0){
            System.out.println(tempNode.val);
            tempNode =tempNode.next;
        }
    }

}
