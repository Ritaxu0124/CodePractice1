package DataStructure.recursion;

/**
 * 递归问题
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        System.out.println("1的阶乘为：" + factorial(1));;
    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println("n = " + n);
        }
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
