package DataStructure.linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "孙二娘", "母老虎");
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();

//        list.add(hero1);
//        list.add(hero2);
//        list.add(hero6);
//        list.add(hero3);
//        list.add(hero4);

        list1.addByOrder(hero1);
        list1.addByOrder(hero4);
        list2.addByOrder(hero6);
        list2.addByOrder(hero3);
        list2.addByOrder(hero2);
        //显示初始链表：
        System.out.println("初始链表list1为：");
        list1.list();
        System.out.println("初始链表list2为：");
        list2.list();
//        System.out.printf("链表长度为：%d\n", getLength(list.getHead()));
//        System.out.println("倒数第一个节点为：" + findLastIndexNode(list.getHead(), 1));
//        System.out.println("反转之后的链表为：");
//        reverseList(list.getHead());
//        list.list();

//        System.out.println("对链表进行逆序打印得到：");
//        reversePrint(list1.getHead());
//        System.out.println("原始链表不变：");
//        list1.list();

//        HeroNode newHero = new HeroNode(2, "关胜", "大刀");
//        list.update(newHero);
//        System.out.println("修改后的链表为：");
//        list.list();
//
//        list.del(1);
//        System.out.println("删除后的链表为：");
//        list.list();
//
//        list.del(6);
//        System.out.println("删除后的链表为：");
//        list.list();
//
//        System.out.printf("链表长度为：%d\n", getLength(list.getHead()));

        //合并链表
        System.out.println("合并后的链表为：");
        SingleLinkedList list3 = new SingleLinkedList();
        mergeList(list1.getHead(), list2.getHead(), list3.getHead());
        list3.list();



    }

    //获取单链表的节点个数（不讲头节点统计在内）

    /**
     * @param head 链表头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //找到链表中的倒数第k个节点
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回nulll
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }

        //获得链表中的节点个数
        int size = getLength(head);
        //对index进行校验
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        //遍历至size-index位置，则为要找的节点
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //反转链表
    public static void reverseList(HeroNode head) {
        //若原始链表为空或者只有一个节点，返回原始链表
        if (head.next == null || head.next.next == null){
            return;
        }

        //定义辅助指针
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");

        while (cur != null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }

        head.next = reverseHead.next;
    }

    //逆序打印单链表
    //方式1：先反转链表再打印（会破坏原先链表的结构）
    //方式2：使用栈，将链表的各个节点入栈，再利用栈先进后出的特点，实现逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return; //若链表为空，则无法打印
        }

        //创建一个栈，将各个节点入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        //将栈中的节点出栈并打印
        while (!stack.isEmpty()){
            System.out.println(stack.pop());    //先进后出
        }
    }

    //合并两个有序链表，合并之后仍然是有序的
    public static void mergeList(HeroNode head1, HeroNode head2, HeroNode head3){
        if(head1.next == null){
            head3.next = head2.next;
        }
        if(head2.next == null){
            head3.next = head1.next;
        }

        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode cur3 = head3;
        while(cur1 != null && cur2 != null){
            if(cur1.no < cur2.no){
                cur3.next = cur1;
                cur1 = cur1.next;
            }else{
                cur3.next = cur2;
                cur2 = cur2.next;
            }
            cur3 = cur3.next;
        }
        cur3.next = cur1 != null ? cur1 : cur2;

    }
}

//定义SingleLinkedList管理英雄
class SingleLinkedList {
    //初始化头节点
    private HeroNode head = new HeroNode(0, " ", " ");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //增加节点到单向链表
    //1.找到当前链表的最后一个节点
    //2.将这个节点的next指向新节点
    public void add(HeroNode node) {
        //因为头节点不能动，所以需要临时节点来遍历
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //当退出循环时，已到达最后一个节点的位置
        temp.next = node;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode node) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，所以我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        //利用flag判断该编号是否存在，若存在则无法添加
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no > node.no) { //位置找到，插入到temp的后面
                break;
            } else if (temp.next.no == node.no) {//说明该编号已存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            System.out.printf("当前编号%d已存在", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改
    //思路：根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newNode) {
        if (head.next == null) {
            System.out.println("链表为空，不能修改");
            return;
        }
        HeroNode temp = head.next;
        //表示是否找到该节点
        boolean flag = false;
        while (temp != null) {
            if (temp.no == newNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到对应节点
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.printf("链表中不存在编号为%d的节点，无法修改\n", newNode.no);
        }
    }

    //删除节点
    //head不能动，所以需要利用辅助节点temp找到待删除节点的前一个节点
    //需要将temp.next.no与delNode.no进行比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;   //用于判断是否找到对应节点
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到节点%d，无法删除\n", no);
        }
    }

    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，重写toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}