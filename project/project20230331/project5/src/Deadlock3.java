public class Deadlock3 {
    public static void main(String[] args) throws InterruptedException {
        PlusMinus plusMinus1 = new PlusMinus();
        plusMinus1.num = 1000;
        PlusMinus plusMinus2 = new PlusMinus();
        plusMinus2.num = 1000;
        PlusMinus plusMinus3 = new PlusMinus();
        plusMinus3.num = 1000;
        MyThread thread1 = new MyThread(plusMinus1, plusMinus2,plusMinus3, 1);
        MyThread thread2 = new MyThread(plusMinus1, plusMinus2,plusMinus3, 2);
        MyThread thread3 = new MyThread(plusMinus1, plusMinus2,plusMinus3, 3);
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        Thread t3 = new Thread(thread3);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
