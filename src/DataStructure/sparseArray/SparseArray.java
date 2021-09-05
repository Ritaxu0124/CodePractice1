package DataStructure.sparseArray;

public class SparseArray {
    public static void main(String[] args) {
        //创建原始二维数组 11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][3] = 2;
        //打印原始二维数组
        System.out.println("原始的二维数组为：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转化为稀疏数组
        //先遍历二维数组，得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零值存放在稀疏数组中
        int count = 0; //记录是第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //打印稀疏数组
        System.out.println();
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }

        //将稀疏数组转化为原始二维数组
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        System.out.println();
        System.out.println("原始的二维数组为：");
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2. 读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印还原后的二维数组
        System.out.println();
        System.out.println("原始二维数组为：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
