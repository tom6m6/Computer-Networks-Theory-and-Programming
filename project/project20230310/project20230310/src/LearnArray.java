import java.util.Arrays;
public class LearnArray {
    public static void main(String[] args){
        int a[]=new int[]{18,62,68,82,65,9};
        Arrays.sort(a);
        System.out.println("after sort:");
        System.out.println(Arrays.toString(a));
        System.out.println("binarySearch result:");
        System.out.println(Arrays.binarySearch(a,68));

    }
}
