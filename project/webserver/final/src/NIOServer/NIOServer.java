package NIOServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class NIOServer implements Runnable{
    static ServerSocketChannel serverSocketChannel;
    static Selector selector;
    static final int PORT = 8081;
    static final String HOST = "127.0.0.1";
    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(new NIOServer());
        thread.start();
    }
    @Override
    public void run() {
        try {
            createServer();
            while(serverSocketChannel.isOpen()) {
                selector.select();
                // SelectionKey 将 Selector 与 SelectableChannel 关联起来
                Set<SelectionKey> sets = selector.selectedKeys();
                Iterator<SelectionKey> iterator = sets.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    doHandleInteresting(selectionKey);
                    iterator.remove();
                }
            }
        }  catch (Exception e) {
        }
    }
    private void doHandleInteresting(SelectionKey selectionKey) throws Exception {
        if (selectionKey.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            Request request = new Request(socketChannel);
            request.handle();
            Response response = new Response(request, socketChannel, serverSocketChannel);
            response.handle();
            socketChannel.close();
        }
    }
    static void createServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(HOST, PORT));
        serverSocketChannel.configureBlocking(false);
        createSelector();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    static void createSelector() throws IOException {
        selector=Selector.open();
    }
}