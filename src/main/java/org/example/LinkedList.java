package org.example;
import java.util.*;

public class LinkedList {

    static class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    //1. Reverse a Linked List
    public ListNode reverseList(ListNode head){

        //Approach 1 : Using Extra Space
        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp = head;

        while(temp != null){
            list.add(temp.val);
            temp = temp.next;
        }

        Collections.reverse(list);

        temp = head;
        int i = 0;

        while(temp != null){
            temp.val = list.get(i);
            i++;
            temp = temp.next;
        }
        return head;
        //TC: O(N)
        //SC: O(N)

        //Approach 2: Using 3 pointers, without any extra space
        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return head;
        //TC: O(N)
        //SC: O(1)
    }

    //2. Linked List Cycle

    public boolean hasCycle(ListNode head){

        //Approach 1: Using Extra Space
        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = head;

        while(temp != null){
            if(set.contains(temp)){
                return true;
            }
            set.add(temp);
            temp = temp.next;
        }
        return false;
        //TC: O(N)
        //SC: O(N)

        //Approach 2: Hare and Tortoise
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
        //TC: O(N)
        //SC: O(1)
    }

    //3. Merge Two Sorted Lists

    public ListNode mergeTwoLists(ListNode list1, ListNode list2){

        ListNode answer = new ListNode(-1);
        ListNode temp = answer;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                temp.next = list1;
                list1 = list1.next;
                temp = temp.next;
            }
            else{
                temp.next = list2;
                list2 = list2.next;
                temp = temp.next;
            }
        }

        while(list1 != null){
            temp.next = list1;
            list1 = list1.next;
            temp = temp.next;
        }
        while(list2 != null){
            temp.next = list2;
            list2 = list2.next;
            temp = temp.next;
        }
        return answer.next;
        //TC: O(N)
        //SC: O(1)
    }

    //4. Merge k Sorted Lists

    public ListNode mergeKLists(ListNode[] lists){

        ListNode answer = new ListNode(-1);
        ListNode temp = answer;

        List<Integer> list = new ArrayList<>();

        for(ListNode node : lists){
            while(node != null){
                list.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(list);
        for(int k : list){
            temp.next = new ListNode(k);
            temp = temp.next;
        }
        return answer.next;
        //TC: O(N)
        //SC: O(N)
    }

    //5. Remove Nth Node From End of List

    public ListNode removeNthFromEnd(ListNode head, int n){

        //Approach 1: Two Pass Solution
        ListNode temp = new ListNode(-1);
        temp.next = head;

        ListNode node = head;
        int length = 0;
        while(node != null){
            length++;
            node = node.next;
        }

        int pos = length - n;
        node = temp;
        for(int i = 0; i < pos; i++){
            node = node.next;
        }
        if(node.next != null) {
            node.next = node.next.next;
        }
        return temp.next;
        //TC: O(N+N)
        //SC: O(1)

        //Approach 2: Single Pass Solution
        ListNode answer = new ListNode(-1);
        answer.next = head;

        ListNode first = answer;
        ListNode second = answer;

        for(int i = 0; i < n; i++){
            second = second.next;
        }
        while(second.next != null){
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;

        return answer.next;
        //TC: O(N)
        //SC: O(1)
    }

    //6. Reorder List

    public void reorderList(ListNode head){

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode current = slow;
        ListNode prev = null;
        ListNode next = null;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        ListNode first = head;
        ListNode second = prev;

        while(first.next != null && second.next != null){
            ListNode t1 = first.next;
            ListNode t2 = second.next;

            first.next = second;
            second.next = t1;

            first = t1;
            second = t2;
        }
    }
}
