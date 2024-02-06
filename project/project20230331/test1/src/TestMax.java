public class TestMax {
    public static void main(String[] args) throws InterruptedException {
        Res res = new Res();
        int threadNum = 3;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(new MyThread(i, res));
        }
        for (int i = 0; i < threadNum; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadNum; i++) {
            threads[i].join();
        }
        System.out.println(res.max_idx);
    }
}