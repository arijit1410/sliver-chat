package res.io;

import java.io.Serializable;

public interface MessageListener {
    void callback(Serializable msg);
}
