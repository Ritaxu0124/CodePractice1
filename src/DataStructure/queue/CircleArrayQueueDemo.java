package DataStructure.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例");
        //队列大小为4，但是有效数据个数最大值为3，要空出一个位置作为约定
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = sc.next().charAt(0);//接收一个字符
            switch (key) {
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
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("头部数据是：%d\n", res);
                    } catch (Exception e) {
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

class CircleArrayQueue {
    private int maxSize;    //数组最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;  //数组头部
    private int rear;   //数组尾部
    private int[] arr;  //存放队列的数组

    //创建构造函数
    public CircleArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列中数据，并出列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获得数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //读取队列头部数据，不出列
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获得数据");
        }
        return arr[front];
    }

    //显示队列中所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法获得数据");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //获得当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}