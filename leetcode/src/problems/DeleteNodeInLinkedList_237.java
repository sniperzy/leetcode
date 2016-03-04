package problems;

import utils.ListNodeUtils;
import entity.ListNode;

public class DeleteNodeInLinkedList_237 {
	public void deleteNode(ListNode node) {
		ListNode next = node;
		ListNode cur = null;
        for(int i=0;i<2;i++){
        	cur = next;
        	next = getNextNode(next);
        	if(next == null){
        		break;
        	}
        }
        
        if(next != null){
        	cur.next = (next.next == null) ? null : next.next;
        }
    }
	
	public ListNode getNextNode(ListNode node){
		if(node == null){
			return null;
		}
		return node.next;
	}
	
	public ListNode init(){
		ListNode node = ListNodeUtils.genListNode(new int[]{1,2,3,4});
		return node;
	}
	
	public static void main(String[] args) {
		DeleteNodeInLinkedList_237 exam = new DeleteNodeInLinkedList_237();
		ListNode node = exam.init();
		System.out.println(node);
		exam.deleteNode(node);
		System.out.println(node);
	}
}
