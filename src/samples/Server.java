package samples;

import java.io.*;
import java.util.*;
import res.io.*;

public class Server {
    public static void main(String args[]) throws IOException {
        SocketIOThread server = new SocketIOThread(new SocketIOServer(1337), new MessageListener() {
            @Override
            public void callback(Serializable msg) {
                System.out.println("Received:" + (String)msg);
            }
        });
        server.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            server.write(scanner.nextLine());
        }
    }
}
