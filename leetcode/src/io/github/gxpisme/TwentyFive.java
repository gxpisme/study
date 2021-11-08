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
     * ������ת
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        // �ȶ���newHead ���ص�ͷ
        ListNode newHead = new ListNode(head.val);

        // ����������
        while (head.next != null) {
            // ��ԭ����newHead��Ϊ�½ڵ��next���룬��new�Ľڵ����newHead��
            newHead = new ListNode(head.next.val, newHead);

            // ������ָ���𽥺���
            head = head.next;
        }
        return newHead;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        // ����ÿһ�ν���ƴ�ӡ�
        ListNode tempListNode = new ListNode();
        ListNode returnListNode = tempListNode;

        // һ������ & һ�������һ���ڵ�
        ListNode segment = new ListNode();
        ListNode segmentHead = segment;

        // �ڼ���
        Integer position = 0;
        while (head != null) {
            position++;

            // ����ǰ��ֵ����Ϊһ������ĺ�һ���ڵ�
            segment.next = new ListNode(head.val);
            // segment ������һ���ڵ�
            segment = segment.next;

            // ��position��k�ı�����������������з�ת
            if (position % k == 0) {
                while (tempListNode.next != null) {
                    tempListNode = tempListNode.next;
                }
                // �����������з�ת
                tempListNode.next = reverse(segmentHead.next);

                segment = new ListNode();
                segmentHead = segment;
            }

            // ����head������
            head = head.next;
        }

        // ����ģ����÷�ת
        while (tempListNode.next != null) {
            tempListNode = tempListNode.next;
        }
        tempListNode.next = segmentHead.next;

        return returnListNode.next;
    }
}
