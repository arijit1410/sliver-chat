package res.io;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class SocketIOClient extends SocketIO {
    private final Socket socket;
    private final ObjectInputStream istream;
    private final ObjectOutputStream ostream;

    public SocketIOClient(String host, int port) throws IOException {
    	socket = new Socket(host, port);
    	istream = new ObjectInputStream(socket.getInputStream());
    	ostream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public Serializable read() throws IOException, ClassNotFoundException {
    	return (Serializable) istream.readObject();
    }

    @Override
    public void write(Serializable obj) throws IOException {
    	ostream.writeObject(obj);
    }
    
    public int getPort() { return socket.getPort(); }

    @Override
    public void close() {
    	closeQuietly(istream);
        closeQuietly(ostream);
        closeQuietly(socket);
    }
    
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
