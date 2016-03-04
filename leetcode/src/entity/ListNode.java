package entity;

public class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(val);
		if(next != null){
			sb.append(", ").append(next);
		}
		return sb.toString();
	}
	
	
}
