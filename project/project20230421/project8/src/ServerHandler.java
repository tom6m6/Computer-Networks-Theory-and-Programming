import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
class ServerHandler extends Thread {
    private Socket socket;
    private final ServerReadHandler serverReadHandler;
    private final ServerWriteHandler serverWriteHandler;
    ServerHandler(Socket socket) throws IOException{
        this.socket = socket;
        this.serverReadHandler = new ServerReadHandler(socket.getInputStream());
        this.serverWriteHandler = new ServerWriteHandler(socket.getOutputStream());
    }
    @Override
    public void run() {
        super.run();
        serverWriteHandler.start();
        serverReadHandler.start();
    }
}

