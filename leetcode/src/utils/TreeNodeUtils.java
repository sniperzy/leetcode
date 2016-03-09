package utils;

import java.util.ArrayList;
import java.util.List;

import entity.TreeNode;

/**
 * 二叉树工具类
 * @author zhaoye 2016-3-3
 * @since 2016-3-3
 */
public class TreeNodeUtils {
	public static TreeNode genTreeNode(String nodeArrayStr){
		
		if(StringUtils.isNotBlank(nodeArrayStr)){
			List<TreeNode> childrens = new ArrayList<TreeNode>();
			List<String> nodes = new ArrayList<String>();
			
			String[] nodeArray = nodeArrayStr.split(",");
			for(String i : nodeArray){
				nodes.add(i);
			}
			
			//取出根节点
			TreeNode root = new TreeNode(StringUtils.getObjectInt(nodes.get(0)));
			nodes.remove(0);
			childrens.add(root);
			TreeNode cur = root;
			
			while(childrens.size() > 0){
				cur = childrens.get(0);
				childrens.remove(0);
				
				if(cur == null ){
					continue;
				}
				
				if(nodes.size() > 0){
					if(!"null".equals(nodes.get(0))){
						cur.left = new TreeNode(StringUtils.getObjectInt(nodes.get(0)));
						childrens.add(cur.left);
					}
					nodes.remove(0);
				}
				if(nodes.size() > 0){
					if(!"null".equals(nodes.get(0))){
						cur.right = new TreeNode(StringUtils.getObjectInt(nodes.get(0)));
						childrens.add(cur.right);
					}
					nodes.remove(0);
				}
			}
			
			return root;
		}else{
			return null;
		}
	}
	
	public static String getTreeNodeStr(TreeNode node){
		List<TreeNode> childrens = new ArrayList<TreeNode>();
		StringBuffer sb = new StringBuffer();
		TreeNode cur;
		
		childrens.add(node);
		while(childrens.size() > 0){
			cur = childrens.get(0);
			
			if(cur != null){
				sb.append(cur.val).append(", ");
				childrens.add(cur.left);
				childrens.add(cur.right);
			}else{
				sb.append("null, ");
			}
			childrens.remove(0);
		}
		
		return StringUtils.stripEnd(sb.toString(), "nul, ");
	}
	
	public static void main(String[] args) {
		TreeNode node = genTreeNode("5,4,7,3,null,2,null,-1,null,9");
		System.out.println(getTreeNodeStr(node));
	}
}
