package DataStructure.hashtab;

import java.util.HashMap;
import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab tab = new HashTab(7);
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = sc.next();
            switch (key) {
                case "add":
                    System.out.println("请输入雇员id：");
                    int id = sc.nextInt();
                    System.out.println("请输入雇员名字");
                    String name = sc.next();
                    Emp e = new Emp(id, name);
                    tab.add(e);
                    break;
                case "list":
                    tab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员id:");
                    id = sc.nextInt();
                    tab.findById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的雇员id:");
                    id = sc.nextInt();
                    tab.delEmp(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建雇员类
class Emp {
    int id;
    String name;
    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建HashTab管理多条链表
class HashTab {
    EmpLinkedList[] empLinkedLists;
    int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //要记得初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //根据员工的id计算出应该将员工添加到哪个链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedLists[empLinkedListNo].add(emp);
    }

    //遍历所有的链表，即遍历hashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //根据雇员id查找位置
    public void findById(int id) {
        int EmpLinkedListNo = hashFun(id);
        Emp e = empLinkedLists[EmpLinkedListNo].findById(id);
        if (e == null) {
            System.out.println("找不到该雇员的信息");
        } else {
            System.out.printf("id为%d的雇员在第%d条链表\n", id, EmpLinkedListNo + 1);
        }
    }

    //根据雇员id删除雇员
    public void delEmp(int id){
        int EmpLinkedListNo = hashFun(id);
        boolean flag = empLinkedLists[EmpLinkedListNo].delEmp(id);
        if (flag){
            System.out.println("删除成功");
        }else {
            System.out.println("该雇员不存在");
        }
    }

    public int hashFun(int id) {
        return id % size;
    }
}

//创建雇员链表
class EmpLinkedList {
    //头指针指向第一个Emp
    Emp head;

    //添加雇员到链表
    //说明
    //1. 假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
    //因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果链表为空，则添加的为头节点
        if (head == null) {
            head = emp;
            return;
        }
        //若链表不为空，则定义一个辅助指针，指向链表最后
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        //如果链表为空
        if (head == null) {
            System.out.println("第" + (no + 1) + "个链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp, 如果没有找到，就返回null
    public Emp findById(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        while (curEmp != null) {
            if (curEmp.id == id) {   //说明找到该雇员
                return curEmp;
            }
            curEmp = curEmp.next;
        }
        return null;
    }

    //根据id删除雇员
    public boolean delEmp(int id){
        if(head == null){
            return false;
        }
        if (head.id == id){
            head = head.next;
            return true;
        }
        Emp curEmp = head;
        while (curEmp.next != null){
            if (curEmp.next.id == id){
                curEmp.next = curEmp.next.next;
                return false;
            }
        }
        return false;
    }
}