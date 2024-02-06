import java.util.LinkedList;
import java.util.Queue;

class ProductFactory {
    Queue<String> productQueue = new LinkedList<>();

    public synchronized void addProduct(String s) {
        productQueue.add(s);
        this.notify();
    }

    public synchronized String getProduct() throws InterruptedException {
        while (productQueue.isEmpty()) {
// 释放this锁
            this.wait();
// 重新获取this锁
        }
        return productQueue.remove();
    }
}