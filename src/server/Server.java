package server;

import javax.net.ssl.*;

import utils.Utils;

public class Server{


    public static void main(String[] args) throws Exception {

    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(Utils.PORT);
    
    ss.setNeedClientAuth(true);
    ss.setEnabledCipherSuites(ssf.getSupportedCipherSuites());


    
    while(true) {
      SSLSocket socket = (SSLSocket) ss.accept();
      new Thread( new Handler(socket)).start();
    }



    //s.close();


    }
}

