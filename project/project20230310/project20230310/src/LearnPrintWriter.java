import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
public class LearnPrintWriter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int []a=new int[10];
        for(int i=0;i<10;i++){
            a[i]=sc.nextInt();
        }
        Arrays.sort(a);
        pw.println("After sort:");
        pw.flush();
        pw.println(Arrays.toString(a));
        pw.flush();
    }

}
