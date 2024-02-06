public class TestInteract {
    public static void main(String[] args) {
        PlusMinusOne pmo = new PlusMinusOne();
        pmo.num = 50;
        Object lock = new Object();

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        while (pmo.num == 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        pmo.minusOne();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        };
        t1.start();
        Thread t3 = new Thread() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        while (pmo.num == 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        pmo.minusOne();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        };
        t3.start();
        Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    synchronized (lock){
                        pmo.plusOne();
                        lock.notifyAll();

                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();
    }
}
