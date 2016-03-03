package problems;

/**
 * 计算二叉树最大深度
 * @author zhaoye
 */
public class MaximumDepthOfBinaryTree_104 {
	public int maxDepth(TreeNode root) {
		if(root == null){
			return 0;
		}
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        return (leftDepth > rightDepth) ? leftDepth + 1 : rightDepth + 1;
    }
	
	public TreeNode init(){
		TreeNode root = new TreeNode(0);
		
		TreeNode r1 = new TreeNode(1);
		TreeNode r2 = new TreeNode(2);
		TreeNode r3 = new TreeNode(3);
		TreeNode r4 = new TreeNode(4);
		TreeNode r5 = new TreeNode(5);
		
		root.left = r1;
		root.right = r2;
		r1.left = r3;
		r3.right = r4;
		r2.right = r5;
		
		return root;
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		MaximumDepthOfBinaryTree_104 exam = new MaximumDepthOfBinaryTree_104();
		System.out.println(exam.maxDepth(exam.init()));
	}
}
