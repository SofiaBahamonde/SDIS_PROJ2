package player;

import javax.net.ssl.*;

import ui.ServerUI;
import utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Client {

    static PrintStream out;
    static BufferedReader in;

    public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket socket = (SSLSocket) sf.createSocket(Utils.HOST, Utils.PORT);
    socket.setEnabledCipherSuites(sf.getSupportedCipherSuites());

    Client c = new Client();

    c.out = new PrintStream(socket.getOutputStream());
    c.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String request = null;
    while (((request = in.readLine()) != null)) {
        parseRequest(request);
    }

    in.close();
    out.close();
    socket.close();
  }

    private static void parseRequest(String request) {
        switch (request){
            case "USERNAME":
                String username = ServerUI.welcome();

                break;
            default:
                break;
        }
    }

    private static void sendResponse(String response){
        out.println(response);
        out.flush();
    }
}
