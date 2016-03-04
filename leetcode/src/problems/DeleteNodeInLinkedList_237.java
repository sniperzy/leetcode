package problems;

import utils.ListNodeUtils;
import entity.ListNode;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
 * the linked list should become 1 -> 2 -> 4 after calling your function.
 * 
 * @author zhaoye
 * @since 2016-3-4
 */
public class DeleteNodeInLinkedList_237 {
	public void deleteNode(ListNode node) {
		if(node == null){
			return;
		}
		
		if(node.next != null){
			node.val = node.next.val;
			node.next = node.next.next;
		}
    }
	
	public ListNode init(){
		ListNode node = ListNodeUtils.genListNode(new int[]{1,2,3,4});
		return node;
	}
	
	public static void main(String[] args) {
		DeleteNodeInLinkedList_237 exam = new DeleteNodeInLinkedList_237();
		ListNode node = exam.init();
		System.out.println(node);
		
		ListNode selectNode = ListNodeUtils.getListNodeByDepth(node, 3);
		System.out.println(selectNode);
		
		exam.deleteNode(selectNode);
		System.out.println(node);
	}
}
