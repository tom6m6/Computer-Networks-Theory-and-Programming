import java.util.LinkedList;
import java.util.Queue;
public class Test {
    public static void main(String[] args) {
        ProductFactory pf = new ProductFactory();
        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    try {
                        String s = pf.getProduct();
                        System.out.println("t1 get product: " + s);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    try {
                        String s = "product";
                        pf.addProduct(s);
                        System.out.println("t2 add product: " + s);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t3 = new Thread() {
            public void run() {
                while (true) {
                    try {
                        String s = pf.getProduct();
                        System.out.println("t3 get product: " + s);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }
}
