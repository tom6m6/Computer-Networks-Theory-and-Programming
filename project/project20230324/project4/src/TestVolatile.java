// if variable is not volatile, this example may not be terminated
// but this behaviour may differ on some machines
class TestVolatile extends Thread{
    //volatile
    // volatile boolean sayHello = true;
    volatile boolean sayHello = true;
    public void run() {
        long count=0;
        while (sayHello) {
            count++;
        }
        System.out.println("Thread terminated." + count);
    }
    public static void main(String[] args) throws InterruptedException {
        TestVolatile t = new TestVolatile();
        t.start();
        Thread.sleep(500);
        System.out.println("after main func sleeping...");
        t.sayHello = false;
        t.join();
        System.out.println("sayHello set to " + t.sayHello);
    }
}
