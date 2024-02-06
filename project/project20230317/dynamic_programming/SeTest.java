package dynamic_programming;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SeTest {

    public static void printChar(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++)
                System.out.print('*');
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[10];
        PrintWriter printWriter = new PrintWriter(System.out);
        for(int i=0;i<10;i++)
            arr[i] = scanner.nextInt();
        Arrays.sort(arr);
        for(int num : arr)
            printWriter.println(num);
        printWriter.flush();
    }
}
