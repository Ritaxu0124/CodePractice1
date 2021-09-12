package DataStructure.tree;

/**
 * @author Rita
 * 二叉树遍历（递归实现）
 * 韩顺平ver
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode hero1 = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "卢俊义");
        HeroNode hero3 = new HeroNode(3, "吴用");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero5 = new HeroNode(5, "关胜");

        binaryTree.setRoot(hero1);
        hero1.setLeft(hero2);
        hero1.setRight(hero3);
        hero3.setRight(hero4);
        hero3.setLeft(hero5);

        //测试遍历
        //前序遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();
        //中序遍历
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        //后序遍历
        System.out.println("后序遍历");
        binaryTree.postOrder();
    }

}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no){
        if (this.root != null){
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if (this.root != null){
            return this.root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    //后序遍历
    public HeroNode postOrderSearch(int no){
        if (this.root != null){
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //默认为空
    private HeroNode right; //默认为空

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        //输出父节点
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        //输出父节点
        System.out.println(this);

        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }

        //输出父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no 查找编号
     * @return 如果找到则返回node，如果没找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        //先判断当前父节点
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        //判断左节点是否为空，若不为空，则左递归前序查找
        //如果左递归前序查找找到节点，则返回
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //若左递归未找到，判断右节点是否为空，若不为空，则右递归前序查找
        //如果右递归前序查找找到节点，则返回
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     * @param no 查找编号
     * @return 如果找到则返回node，如果没找到返回null
     */
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        //先判断左节点是否为空，若不为空，则左递归前序查找
        //如果左递归前序查找找到节点，则返回
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //判断当前父节点
        if (this.no == no) {
            return this;
        }
        //判断右节点是否为空，若不为空，则右递归前序查找
        //如果右递归前序查找找到节点，则返回
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     * @param no 查找编号
     * @return 如果找到则返回node，如果没找到返回null
     */
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;
        //先判断左节点是否为空，若不为空，则左递归前序查找
        //如果左递归前序查找找到节点，则返回
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        //判断右节点是否为空，若不为空，则右递归前序查找
        //如果右递归前序查找找到节点，则返回
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }

        //判断当前父节点
        if (this.no == no) {
            resNode = this;
        }
        return resNode;
    }
}