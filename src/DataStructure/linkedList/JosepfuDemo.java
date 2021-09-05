package DataStructure.linkedList;

public class JosepfuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addChild(5);
        circleSingleLinkedList.showChild();

        circleSingleLinkedList.countChild(1, 2, 5);
    }
}

//定义一个单向环形链表
class CircleSingleLinkedList{
    //创建一个first节点
    Child first = new Child(0);

    //添加Child节点，构建环形链表
    public void addChild(int nums){
        if(nums < 1){
            System.out.println("nums的值不正确，无法构建");
            return;
        }
        Child curChild = null;    //辅助变量，帮助构建环形链表
        for (int i = 1; i <= nums; i++){
            Child child = new Child(i);
            if(i == 1){
                first = child;
                first.setNext(first);
                curChild = first;
            }else{
                curChild.setNext(child);
                child.setNext(first);
                curChild = child;
            }
        }
    }

    //显示链表
    public void showChild(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }

        Child cur = first;
        while(true){
            System.out.printf("小孩编号为：%d\n", cur.getNo());
            if(cur.getNext() == first){ //已到最后一个节点
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * 根据用户的输入计算小孩的出圈顺序
     * @param startNo   表示从第几个小孩开始计数
     * @param countNum  第几个小孩出圈
     * @param num   初始时圈中一共有几个小孩
     */
    public void countChild(int startNo, int countNum, int num){
        if (first == null || startNo < 1 || countNum > num){
            System.out.println("参数输入有无，请重新输入！");
            return;
        }

        for (int i = 0; i < startNo - 1; i++){
            first = first.getNext();
        }
        //创建辅助指针，帮助小孩出圈
        Child helper = first;
        //helper指向链表最后一个节点
        while(true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //当小孩报数时，让helper和first都移动countNum-1个节点，即为要出圈的节点
        while(true){
            if (helper == first){
                break;
            }
            for (int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点出圈
            System.out.printf("出圈的小孩编号为：%d\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为:%d\n", first.getNo());
    }
}

//定义一个child类来表示节点
class Child{
    private int no; //编号
    private Child next; //指向下一个节点

    public Child(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Child{" +
                "no=" + no +
                '}';
    }
}
