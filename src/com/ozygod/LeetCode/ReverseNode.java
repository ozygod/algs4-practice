package com.ozygod.LeetCode;

public class ReverseNode {
    class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x;}
    }

    /**
     * 由于内部类可以访问外部类的成员变量，当外部类未被初始化时，是不能直接初始化使用内部类的
     * 因此要使用内部类，需要通过外部类提供初始化方法。
     * @param i
     * @return
     */
    public ListNode initNode(int i) {
        return new ListNode(i);
    }

    public static void main(String[] args) {
        ReverseNode test = new ReverseNode();
        ListNode head = test.initNode(5);
        for (int i = 4; i > 0; i--) {
            ListNode old = head;
            head = test.initNode(i);
            head.next = old;
        }
        System.out.print("初始内容：");
        showNode(head);
        System.out.println();
        ListNode result = reverseList(head);

        System.out.print("最终结果：");
        showNode(result);
    }

    public static ListNode reverseList(ListNode head) {
        if (head.next == null) return head;
        ListNode p = reverseList(head.next);
        System.out.print("p: ");
        showNode(p);
        System.out.print("head: ");
        showNode(head);
        head.next.next = head;
        head.next = null;
        System.out.print("返回的p: ");
        showNode(p);
        System.out.println();
        return p;
    }

    public static void showNode(ListNode node) {
        StringBuffer show = new StringBuffer();
        ReverseNode test = new ReverseNode();
        ListNode tmp = test.initNode(0);
        tmp.next = node;
        while (tmp != null) {
            if (tmp.val != 0) {
                show.append(tmp.val).append("-->");
            }
            tmp = tmp.next;
        }
        System.out.println(show.toString());
    }
}
