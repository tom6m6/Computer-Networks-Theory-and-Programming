public class TestPlus {
    public static void main(String[] args) throws InterruptedException {
        PlusMinus plusMinus = new PlusMinus();
        plusMinus.num = 0;
        int threadNum = 10;
        Thread[] plusThreads = new Thread[threadNum];
        for(int i=0;i<threadNum;i++){
            plusThreads[i] = new Plus(plusMinus);
        }
        for(int i=0;i<threadNum;i++){
            plusThreads[i].start();
        }
        for(int i=0;i<threadNum;i++){
            plusThreads[i].join();
        }
        System.out.println(plusMinus.printNum());
    }
}