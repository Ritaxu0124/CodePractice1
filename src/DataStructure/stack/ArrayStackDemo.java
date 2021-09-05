package DataStructure.stack;

import java.util.Scanner;

/**
 * 数组实现栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
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

class ArrayStack {
    private int maxSize;    //定义栈的大小
    private int[] stack;    //用数组模拟栈
    private int top = -1;    //栈顶

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈，需要从栈顶开始显示
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空，无数据显示");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}