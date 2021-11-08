package io.github.gxpisme;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * <p>
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * <p>
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * <p>
 * Example 3:
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * <p>
 * Example 4:
 * Input: head = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class TwentyFive {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        ListNode resHead = reverseKGroup(head, 3);

        while (resHead != null) {
            System.out.println(resHead.val);
            resHead = resHead.next;
        }
    }

    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 单链表反转
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        // 先定义newHead 返回的头
        ListNode newHead = new ListNode(head.val);

        // 遍历单链表
        while (head.next != null) {
            // 将原来的newHead作为新节点的next传入，新new的节点就是newHead。
            newHead = new ListNode(head.next.val, newHead);

            // 单链表指针逐渐后移
            head = head.next;
        }
        return newHead;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        // 遍历每一段进行拼接。
        ListNode tempListNode = new ListNode();
        ListNode returnListNode = tempListNode;

        // 一段链表 & 一段链表第一个节点
        ListNode segment = new ListNode();
        ListNode segmentHead = segment;

        // 第几个
        Integer position = 0;
        while (head != null) {
            position++;

            // 将当前的值，做为一段链表的后一个节点
            segment.next = new ListNode(head.val);
            // segment 移向下一个节点
            segment = segment.next;

            // 若position是k的倍数，则对这段链表进行翻转
            if (position % k == 0) {
                while (tempListNode.next != null) {
                    tempListNode = tempListNode.next;
                }
                // 对这段链表进行翻转
                tempListNode.next = reverse(segmentHead.next);

                segment = new ListNode();
                segmentHead = segment;
            }

            // 遍历head单链表
            head = head.next;
        }

        // 多余的，不用翻转
        while (tempListNode.next != null) {
            tempListNode = tempListNode.next;
        }
        tempListNode.next = segmentHead.next;

        return returnListNode.next;
    }
}
