import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String test1 = sc.next();
        System.out.println("next result:"+test1);
        String test2 = sc.nextLine();
        System.out.println("next result:"+test2);
    }
}
