package problems;

import entity.TreeNode;

/**
 * 翻转二叉树.
 * 
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 
 * to
 * 
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 
 * @author zhaoye
 */
public class InvertBinaryTree_226 {
	public TreeNode invertTree(TreeNode root) {
		if(root == null){
			return null;
		}
		TreeNode tmpNode = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(tmpNode);
		
		return root;
	}
	
	public TreeNode init(){
		TreeNode root = new TreeNode(4);
		
		TreeNode r1 = new TreeNode(2);
		TreeNode r2 = new TreeNode(1);
		TreeNode r3 = new TreeNode(3);
		TreeNode r4 = new TreeNode(7);
		TreeNode r5 = new TreeNode(6);
		TreeNode r6 = new TreeNode(9);
		
		root.left = r1;
		root.right = r4;
		r1.left = r2;
		r1.right = r3;
		r4.left = r5;
		r4.right = r6;
		
		System.out.println(root);
		return root;
	}

	public static void main(String[] args) {
		InvertBinaryTree_226 exam = new InvertBinaryTree_226();
		TreeNode node = exam.invertTree(exam.init());
		System.out.println(node);
	}
}
