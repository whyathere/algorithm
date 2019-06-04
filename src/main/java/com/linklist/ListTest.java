package com.linklist;

public class ListTest {

    public static void main(String[] args) {

        //封装测试参数
        ListNode pre = null;
        for (int i =9;i>0;i--){
            ListNode temp = new ListNode(i);
            temp.next = pre;
            pre = temp;
        }

        //反向链表
        ListNode node = SolutionOfReverseList.reverseList(pre);

        //打印反向链表
        ListNode tempNode = node;
        while (tempNode !=null){
            System.out.println(tempNode.val);
            tempNode =tempNode.next;
        }
    }

}
