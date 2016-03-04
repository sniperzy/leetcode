package utils;

import entity.ListNode;

/**
 * 链表工具类
 * @author zhaoye
 * @since 2016-3-4
 */
public class ListNodeUtils {
	public static ListNode genListNode(int[] treeArray){
		ListNode node = null;
		ListNode next = null;
		ListNode cur = null;
		
		if(treeArray != null){
			for(int i=0;i<treeArray.length;i++){
				int val = treeArray[i];
				if(i == 0){
					node = new ListNode(val);	
					cur = node;
				}else{
					next = new ListNode(val);
					cur.next = next;
					cur = next;
				}
			}
		}
		
		return node;
	}
	
	public static void main(String[] args) {
		ListNode node = genListNode(new int[]{1,2,3,4,5,6});
		System.out.println(node);
	}
}
