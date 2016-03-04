package problems;

import entity.ListNode;

/**
 * 连续链表改为先奇后偶
 * @author zhaoye
 */
public class OddEvenLinkedList_328 {
	public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null ){
			return head;
		}
		
		ListNode odd = head;
		ListNode even = head.next;
		ListNode newNode = even;
		
		while(odd.next != null ){
			odd.next = even.next;
			if(odd.next != null){
				odd = odd.next;
				even.next = odd.next;
				even = odd.next;
			}
		}
		
		odd.next = newNode;
		
		return head;
	}
	
	public ListNode init(){
		ListNode node = new ListNode(1);
		ListNode curNode = node;
		for(int i=2;i<9;i++){
			curNode = setNextNode(curNode, i);
		}
		
		return node;
	}
	
	public ListNode setNextNode(ListNode node, int i){
		ListNode curNode = new ListNode(i);
		node.next = curNode;
		return curNode;
	}
	
	public static void main(String[] args) {
		OddEvenLinkedList_328 exam = new OddEvenLinkedList_328();
		ListNode node = exam.init();
		exam.oddEvenList(node);
	}
}
