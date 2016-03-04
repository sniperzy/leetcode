package entity;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" ").append(val);
		sb.append(" ").append((left != null) ? left.toString() : "null");
		sb.append(" ").append((right != null) ? right.toString() : "null");
		return sb.toString();
	}
	
	
	
}
