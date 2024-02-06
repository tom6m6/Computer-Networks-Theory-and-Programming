class MyThread implements Runnable {
    @Override
    public void run() {
        if (tid == 1) {
            synchronized (pm1) {
                System.out.println("thread" + tid + "正在占⽤ plusMinus1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread" + tid + "试图继续占⽤ plusMinus2");
                System.out.println("thread" + tid + "等待中...");
                synchronized (pm2) {
                    System.out.println("thread" + tid + "成功占⽤了 plusMinus2");
                }
            }
        }else if (tid == 2) {
            synchronized (pm2) {
                System.out.println("thread" + tid + "正在占⽤ plusMinus2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread" + tid + "试图继续占⽤ plusMinus3");
                System.out.println("thread" + tid + "等待中...");
                synchronized (pm3) {
                    System.out.println("thread" + tid + "成功占⽤了 plusMinus3");
                }
            }
        }else if(tid == 3){
            synchronized (pm3) {
                System.out.println("thread" + tid + "正在占⽤ plusMinus3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread" + tid + "试图继续占⽤ plusMinus1");
                System.out.println("thread" + tid + "等待中...");
                synchronized (pm1) {
                    System.out.println("thread" + tid + "成功占⽤了 plusMinus1");
                }
            }
        }
    }
    MyThread(PlusMinus _pm1,PlusMinus _pm2,PlusMinus _pm3,int _tid) {
        this.pm1 = _pm1;
        this.pm2 = _pm2;
        this.pm3 = _pm3;
        this.tid = _tid;
    }
    PlusMinus pm1;
    PlusMinus pm2;
    PlusMinus pm3;
    int tid;
}