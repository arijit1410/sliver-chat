package res.io;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SocketIOServer extends SocketIO {
    
    private final ServerSocket serversocket;
    private Socket socket;
    private ObjectInputStream istream;
    private ObjectOutputStream ostream;
    
    public SocketIOServer() throws IOException {
       this(0);
    }
    
    public SocketIOServer(int port) throws IOException {
	serversocket = new ServerSocket(port);
    }
    
    public void init() throws IOException {
        socket = serversocket.accept();
        ostream = new ObjectOutputStream(socket.getOutputStream());
	istream = new ObjectInputStream(socket.getInputStream());
    }
    
    @Override
    public Serializable read() throws IOException, ClassNotFoundException {
        return (Serializable) istream.readObject();
    }
    
    @Override
    public void write(Serializable obj) throws IOException {
        ostream.writeObject(obj);
    }
    
    public int getPort() {
        return serversocket.getLocalPort();
    }
    
    @Override
    public void close() {
        closeQuietly(istream);
        closeQuietly(ostream);
        closeQuietly(socket);
        closeQuietly(serversocket);
    }
    
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
