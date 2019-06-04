package com.linklist;

public class ListTest {

    public static void main(String[] args) {

        //准备测试数据
        ListNode pre = null;
        for (int i =9;i>0;i--){
            ListNode temp = new ListNode(i);
            temp.next = pre;
            pre = temp;
        }
        ListNode cur = pre;
        System.out.println("正向遍历数据");
        while (cur !=null){
            System.out.print("->"+cur.val);
            cur =cur.next;
        }

        //反向链表
        ListNode node = SolutionOfReverseList.reverseList(pre);
        System.out.println();
        //打印反向链表
        System.out.println("反向遍历数据");
        ListNode tempNode = node;
        while (tempNode !=null){
            System.out.print("->"+tempNode.val);
            tempNode =tempNode.next;
        }
    }

}
