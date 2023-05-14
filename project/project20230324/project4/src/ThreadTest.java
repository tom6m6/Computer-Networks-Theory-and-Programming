public class ThreadTest extends Thread {
    @Override
    public void run(){
        int seconds=0;
        while(seconds<10){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
                System.out.println("Thread has been interrupted.");
            }
            ++seconds;
            System.out.print("距离最初,时间过去了"+seconds+"秒 ");
            System.out.println(Thread.currentThread().getId());
        }

    }

    public static void main(String [] argv){
        ThreadTest mythread1 = new ThreadTest();
        ThreadTest mythread2 = new ThreadTest();
        mythread1.start();
        mythread2.start();
    }
}
