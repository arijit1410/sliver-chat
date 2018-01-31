package samples;

import java.io.*;
import java.util.*;
import res.io.*;

public class Client {
    public static void main(String args[]) throws IOException {
        SocketIOThread client = new SocketIOThread(new SocketIOClient("localhost", 1337), new MessageListener() {
            @Override
            public void callback(Serializable msg) {
                System.out.println("Received:" + (String)msg);
            }
        });
        client.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            client.write(scanner.nextLine());
        }
    }
}
