package res.io;

import java.io.Serializable;

public abstract class SocketIO implements AutoCloseable {
    protected void closeQuietly(AutoCloseable obj) {
        try { obj.close(); }
        catch (Exception e) {}
    }
    public abstract Serializable read() throws Exception;
    public abstract void write(Serializable msg) throws Exception;
    public abstract int getPort();
    @Override
    public abstract void close();
}
