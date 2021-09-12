package DataStructure.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Rita
 * 二叉树遍历（递归）
 * LeetCode Version
 */
public class BinaryTreeDemo2 {
    public static void main(String[] args) {
        LinkedList<Integer> linkedlist = new LinkedList<>(Arrays.asList(3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode node = createBinaryTree(linkedlist);
        System.out.println("前序遍历");
        preOrderTraveral(node);
        System.out.println();
        System.out.println("中序遍历");
        infixOrderTraveral(node);
        System.out.println();
        System.out.println("后序遍历");
        postOrderTraveral(node);
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode root = null;
        if (list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null){
            root = new TreeNode(data);
            root.left = createBinaryTree(list);
            root.right = createBinaryTree(list);
        }
        return root;
    }

    //前序遍历
    public static void preOrderTraveral(TreeNode root){
        if (root != null){
            System.out.print(root.data + " ");
            preOrderTraveral(root.left);
            preOrderTraveral(root.right);
        }
    }

    //中序遍历
    public static void infixOrderTraveral(TreeNode root){
        if (root != null){
            infixOrderTraveral(root.left);
            System.out.print(root.data + " ");
            infixOrderTraveral(root.right);
        }
    }

    //中序遍历
    public static void postOrderTraveral(TreeNode root){
        if (root != null){
            postOrderTraveral(root.left);
            postOrderTraveral(root.right);
            System.out.print(root.data + " ");
        }
    }
}

class TreeNode{
    public Object data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Object data) {
        this.data = data;
    }

}

