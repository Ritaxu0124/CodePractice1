package DataStructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner sc = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while(loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = sc.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int n = sc.nextInt();
                    arrayQueue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("头部数据是：%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

class ArrayQueue {
    private int maxSize;    //数组最大容量
    private int front;  //数组头部
    private int rear;   //数组尾部
    private int[] arr;  //存放队列的数组

    //创建构造函数
    public ArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加数据");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    // 获取队列中数据，并出列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获得数据");
        }
        front++;
        return arr[front];
    }

    //读取队列头部数据，不出列
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获得数据");
        }
        return arr[front + 1];
    }

    //显示队列中所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法获得数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }
}