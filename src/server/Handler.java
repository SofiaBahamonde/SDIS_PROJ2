package server;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Handler implements Runnable {

    SSLSocket socket;

    public Handler(SSLSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            while (((line = in.readLine()) != null)) {
                System.out.println(line);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
