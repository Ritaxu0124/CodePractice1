package DataStructure.stack;

/**
 * 计算器的实现
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "2-2*2-5";
        //创建两个栈，一个是数字栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //将每次扫描得到的字符保存到ch
        String keepNum = "";    //用于拼接多位数
        //开始循环扫描expression
        while (index < expression.length()) {
            ch = expression.charAt(index);
            //对ch进行判断
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    //若为空，则直接入栈
                    operStack.push(ch);
                } else {
                    // 若当前符号栈中有操作符，则对操作符优先级进行比较，
                    // 若当前操作符优先级小于等于栈中操作符，则将数字栈中的栈顶两个数取出
                    // 并取出运算符栈栈顶的符号进行运算，再将运算结果入栈，并将当前操作符入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将运算结果入栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //若当前操作符优先级大于栈中操作符，则直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                //单位数
//                numStack.push(ch - 48);
                //考虑多位数的情况
                //在处理数时，需要看expression表达式的index+1位是否还是数字，
                //如果是数字就继续扫描，如果是数字就入栈

                keepNum += ch;

                //如果ch已经是最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.charAt(index + 1))) {
                        //如果后一位是运算符，则将数字入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //一定记得将keepNum清空
                        keepNum = "";
                    }
                }

            }
            index++;
        }

        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            if(!operStack.isEmpty() && operStack.peek() == '-'){
                if(oper == '-'){
                    oper = '+';
                }else {
                    oper = '-';
                }
            }
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d", expression, res2);
    }
}

class ArrayStack2 {
    private int maxSize;    //定义栈的大小
    private int[] stack;    //用数组模拟栈
    private int top = -1;    //栈顶

    //构造器
    public ArrayStack2(int maxSize) {
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

    //显示栈顶的值，但不出栈
    public int peek() {
        return stack[top];
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

    //判断符号优先级，用数字表示优先级高低
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;  //注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}