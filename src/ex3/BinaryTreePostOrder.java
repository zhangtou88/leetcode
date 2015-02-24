package ex3;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostOrder {
	static ArrayList<TreeNode> nodeStack=new ArrayList<TreeNode>();
	static ArrayList<Integer> stateStack=new ArrayList<Integer>();
	public static void pushNode(TreeNode e){
		nodeStack.add(e);
	}
	public static TreeNode popNode(){
		int num= nodeStack.size()-1;
		TreeNode temp=new TreeNode(0);
		if(num>=0){
			temp=nodeStack.get(nodeStack.size()-1);
			nodeStack.remove(nodeStack.size()-1);
		}
		return temp;
	}
	public static void pushState(int s){
		stateStack.add(s);
	}
	public static int popState(){
		int num=stateStack.size()-1;
		int temp= 0;
		if(num>=0){
			temp=stateStack.get(num);
			stateStack.remove(num);
		}
		return temp;
	}
	public static int stackTopState(){
		int num=stateStack.size()-1;
		if(num>=0)
			return stateStack.get(num);
		else
			return -1;
	}
	public static TreeNode stackTopNode(){
		int num=nodeStack.size()-1;
		if(num>=0)
			return nodeStack.get(nodeStack.size()-1);
		else
			return null;
	}
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<Integer>();
        if(root!=null){
        	pushNode(root);
        	pushState(1);
        }
        	
        while(nodeStack.size()!=0){
        	int currentNodeState=stackTopState();
        	TreeNode currentNode=stackTopNode();
        	if(currentNodeState==1){
        		popState();
        		pushState(0);//更改当前节点状态为已向下扩展
        		if(currentNode.right!=null){
        			pushNode(currentNode.right);
        			pushState(1);
        		}
        		if(currentNode.left!=null){
        			pushNode(currentNode.left);
        			pushState(1);
        		}
        	}else{
        		result.add(popNode().val);
        		popState();
        	}
        }
        return result;
    }
	public static void main(String[] args){
		BinaryTreePostOrder test=new BinaryTreePostOrder();
		TreeNode n=new TreeNode(1);
		ArrayList<Integer> a=(ArrayList<Integer>) test.postorderTraversal(n);
		for(int p:a)
			System.out.println(p);
		
	}
}
