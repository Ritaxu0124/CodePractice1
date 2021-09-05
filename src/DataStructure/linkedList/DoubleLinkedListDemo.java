package DataStructure.linkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero6 = new HeroNode2(6, "孙二娘", "母老虎");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.add(hero6);

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero6);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.list();

        //测试删除功能
        doubleLinkedList.del(3);
        System.out.println("删除节点后的链表为：");
        doubleLinkedList.list();

        doubleLinkedList.del(8);
        System.out.println("删除节点后的链表为：");
        doubleLinkedList.list();

        //测试修改功能
        HeroNode2 newHero4 = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHero4);
        System.out.println("修改后的链表为：");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //初始化头节点
    private HeroNode2 head = new HeroNode2(0, " ", " ");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //增加节点到单向链表
    //1.找到当前链表的最后一个节点
    //2.将这个节点的next指向新节点，将新节点的pre指向最后一个节点
    public void add(HeroNode2 node) {
        //因为头节点不能动，所以需要临时节点来遍历
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //当退出循环时，已到达最后一个节点的位置
        temp.next = node;
        node.pre = temp;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode2 node) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //我们找的temp是位于添加位置的前一个节点
        HeroNode2 temp = head;
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
            if (temp.next != null) {
                temp.next.pre = node;
                node.pre = temp;
            }
//            temp.next.pre = node;
            node.next = temp.next;
//            node.pre = temp;
            temp.next = node;

        }
    }


    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改
    //双向链表的节点内容修改和单向链表一样
    public void update(HeroNode2 newNode) {
        if (head.next == null) {
            System.out.println("链表为空，不能修改");
            return;
        }
        HeroNode2 temp = head.next;
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
    //双向链表可以实现自我删除，即不需要找到前一个节点，可以直接找到这个节点进行删除
    public void del(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;   //用于判断是否找到对应节点
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //判断待删除直接是否为最后一个节点，如果是则不要执行下面这段话否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("未找到节点%d，无法删除\n", no);
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no, String name, String nickName) {
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