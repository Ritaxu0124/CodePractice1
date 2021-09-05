package DataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器实现
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将中缀表达式转化为后缀表达式
        //1. 1+((12+3)×4)-5 => 转成  1 12 3 + 4 * + 5 -
        //因为直接对string进行操作不方便，所以将中缀表达式转化为List
        //1+((12+3)×4)-5 => ArrayList:[1,+,(,(,12,+,3,),*,4,),-,5,]
        //2. 将中缀表达式List转化为对应的后缀表达式List
        //ArrayList:[1,+,(,(,12,+,3,),×,4,),-,5] => ArrayList:[1,12,3,+,4,*,+,5,–]
        String infixExpression = "1+((12+3)*4)-5";
        List<String> infixList = toInfixExpressionList(infixExpression);
        System.out.println("中缀表达式对应数组为：" + infixList);
        List<String> suffixList = parseSuffixExpreesionList(infixList);
        System.out.println("后缀表达式对应数组为：" + suffixList);
        int result = calculate(suffixList);
        System.out.println("计算结果为：" + result);




        //先定义一个逆波兰表达式（后缀表达式）
        //(12+4)*5-6 => 12 4 + 5 * 6 -
        //1+((12+3)×4)-5 => 转成  1 12 3 + 4 * + 5 -
        String suffiExpression = "1 12 3 + 4 * + 5 -";
        //思路
        //1、先将后缀表达式放入到ArrayList中
        //2、将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        List<String> rpnList = getListString(suffiExpression);
        System.out.println(rpnList);
        int result2 = calculate(rpnList);
        System.out.println("1+((12+3)×4)-5=" + result2);

    }

    //将中缀表达式转化为list
    //s=1+((2+3)×4)-5
    public static List<String> toInfixExpressionList(String str) {
        //定义一个List用来存放中缀表达式的内容
        List<String> ls = new ArrayList<>();
        int i = 0;
        String s = "";  //用于多位数的拼接
        char c; //每遍历一个字符就放到c里面
        do {
            //如果不是数字的话
            if ((c = str.charAt(i)) < '0' || (c = str.charAt(i)) > '9') {
                ls.add("" + c);
                i++;
            } else {  //如果是一个数，则需要考虑多位数
                s = ""; //先将s置为空
                while (i < str.length() && (c = str.charAt(i)) >= '0' && (c = str.charAt(i)) <= '9') {
                    s += c;
                    i++;
                }
                ls.add(s);
            }
        } while (i < str.length());
        return ls;
    }

    //将中缀表达式List转化为对应的后缀表达式List
    //ArrayList:[1,+,(,(,2,+,3,),×,4,),-,5] => ArrayList:[1,2,3,+,4,×,+,5,–]
    //具体步骤：
    /*1)初始化两个栈：运算符栈s1和储存中间结果的栈s2；
      2)从左至右扫描中缀表达式；
      3)遇到操作数时，将其压s2；
      4)遇到运算符时，比较其与s1栈顶运算符的优先级：
        (1)如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
        (2)否则，若优先级比栈顶运算符的高，也将运算符压入s1；
        (3)否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
      5)遇到括号时：
        (1)如果是左括号“(”，则直接压入s1
        (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
      6)重复步骤2至5，直到表达式的最右边
      7)将s1中剩余的运算符依次弹出并压入s2
      8)依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //初始化两个栈，存放运算符和中间结果
        Stack<String> s1 = new Stack<>();//存储运算符
//        Stack<String> s2 = new Stack<>();
        //由于s2中的元素不出栈，且最后需要对其进行逆序打印，所以用List代替Stack
        List<String> s2 = new ArrayList<>();//存储中间结果
        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() > 0 && getPriority(s1.peek()) >= getPriority(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //对运算符优先级进行比较
    public static int getPriority(String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = 1;
                break;
            case "-":
                res = 1;
                break;
            case "*":
                res = 2;
                break;
            case "/":
                res = 2;
                break;
            default:
                break;
        }
        return res;
    }

    //将后缀表达式转换为list
    public static List<String> getListString(String expression) {
        String[] str = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String str1 : str) {
            list.add(str1);
        }
        return list;
    }

    //计算思路
    //1)从左至右扫描，将3和4压入堆栈；
    //2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
    //3)将5入栈；
    //4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
    //5)将6入栈；
    //6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    public static int calculate(List<String> list) {
        //先定义一个栈存放数字
        Stack<String> stack = new Stack<>();
        for (String str : list) {
            //使用正则表达式来判断是否是数字
            if (str.matches("\\d+")) {   //匹配的是多位数
                //入栈
                stack.push(str);
            } else {  //如果是运算符
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (str.equals("+")) {
                    res = num2 + num1;
                } else if (str.equals("-")) {
                    res = num2 - num1;
                } else if (str.equals("*")) {
                    res = num2 * num1;
                } else if (str.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("表达式有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
