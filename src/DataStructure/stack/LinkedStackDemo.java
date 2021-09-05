package DataStructure.stack;

import java.util.Scanner;

/**
 * 用链表模拟栈
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        String key = "";
        boolean loop = true;    //控制是否退出菜单
        Scanner sc = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit 表示退出程序");
            System.out.println("push: 表示添加数据到栈");
            System.out.println("pop: 表示从栈中取出数据");
            System.out.println("请输入指令：");
            key = sc.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入数据：");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序！");
    }
}

class LinkedStack{
    //链表不需要考虑是否为满
    private int size = 0;   //栈内元素个数
    private Person head = new Person(-1);

    //栈空
    public boolean isEmpty(){
        return head.getNext() == null;
    }

    //入栈
    public void push(int value){
        Person temp = head.getNext();
        Person newNode = new Person(value);
        head.setNext(newNode);
        newNode.setNext(temp);
        size++;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = head.getNext().getNo();
        Person temp = head.getNext();
        head.setNext(temp.getNext());
        size--;
        return value;
    }

    public void show(){
        if (isEmpty()){
            System.out.println("栈空，无数据");
            return;
        }
        Person temp = head.getNext();
        while(temp != null){
            System.out.printf("[%d]\n", temp.getNo());
            temp = temp.getNext();
        }
    }
}

class Person{
    private int no; //编号
    private Person next; //指向下一个节点

    public Person(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Person getNext() {
        return next;
    }

    public void setNext(Person next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Person{" +
                "no=" + no +
                '}';
    }
}
