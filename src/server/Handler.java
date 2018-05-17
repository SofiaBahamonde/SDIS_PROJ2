package server;

import javax.net.ssl.SSLSocket;


import java.io.IOException;
import java.io.PrintStream;

public class Handler implements Runnable {

    SSLSocket socket;

    public Handler(SSLSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("WELCOME");
            ps.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}
