package problems;

import entity.TreeNode;

public class SameTree_100 {
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
        	return true;
        }else if(p == null && q != null || p != null && q == null || p.val != q.val){
        	return false;
        }else{
        	return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
	
	public static void main(String[] args) {
		SameTree_100 exam = new SameTree_100();
//		TreeNode p = new TreeNode(1);
		TreeNode p = null;
		TreeNode q = new TreeNode(1);
		
		System.out.println(exam.isSameTree(p, q));
	}
}
