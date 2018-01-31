package res.io;

import java.io.Serializable;
import java.io.IOException;

public class SocketIOThread extends Thread {
    private final SocketIO io;
    private final MessageListener listener;
    
    public SocketIOThread(SocketIO io, MessageListener listener) throws IOException {
        this.io = io;
        this.listener = listener;
    }
    
    public int getPort() { 
        return io.getPort();
    }
    
    @Override
    public void run() {
        try {
            if (io instanceof SocketIOServer) ((SocketIOServer)io).init();
            while (true) {
                listener.callback(io.read());
            }
        }
        catch (Exception e) {
            System.err.println(e);
            io.close();
        }
    }
    
    public void write(Serializable msg) {
        try {
            io.write(msg);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
